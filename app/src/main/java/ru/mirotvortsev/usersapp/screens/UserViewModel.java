package ru.mirotvortsev.usersapp.screens;

import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.mirotvortsev.usersapp.api.ApiFactory;
import ru.mirotvortsev.usersapp.api.ApiService;
import ru.mirotvortsev.usersapp.data.AppDataBase;
import ru.mirotvortsev.usersapp.pojo.User;
import ru.mirotvortsev.usersapp.pojo.UsersResponse;


public class UserViewModel extends AndroidViewModel {

    private static AppDataBase db;
    private LiveData<List<User>> users;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<Throwable> errors;


    public UserViewModel(@NonNull Application application) {
        super(application);
        db = AppDataBase.getInstance(application);
        users = db.usersDao().getAllUsers();
        errors = new MutableLiveData<>();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public void clearErrors() {
        errors.setValue(null);
    }

    @SuppressWarnings("unchecked")
    private void insertUsers(List<User> users) {
        new InsertUsersTask().execute(users);
    }

    //делаем новый поток
    private static class InsertUsersTask extends AsyncTask<List<User>, Void, Void> {
        @SafeVarargs
        @Override
        protected final Void doInBackground(List<User>... lists) {
            if (lists != null && lists.length > 0) {
                db.usersDao().insertUsers(lists[0]);
            }
            return null;
        }
    }

    private void deleteAllUsers() {
        new DeleteAllUsersTask().execute();
    }

    private static class DeleteAllUsersTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            db.usersDao().deleteAllUsers();
            return null;
        }
    }

    public void loadData() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = ApiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = apiService.getUsers()
                //выбираем в каком потоке будем показывать| все обращения к базе данных или скачивание из интернета делается в shedulers
                .subscribeOn(Schedulers.io())
                //принимать данные мы будем в главном потоке
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UsersResponse>() {
                    @Override
                    public void accept(UsersResponse usersResponse) throws Exception {
                        deleteAllUsers();
                        insertUsers(usersResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        errors.setValue(throwable);
                    }
                });
        compositeDisposable.add(disposable);

    }


//    public void savingToDB() {
//
//        Cursor cursor = db.query( null);
//        while (cursor.moveToNext()){
//            int id = cursor.getInt(cursor.getColumnIndex(id));
//        }
//
//    }

    //этот метод вызывается при уничтожении viewModel
    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}
