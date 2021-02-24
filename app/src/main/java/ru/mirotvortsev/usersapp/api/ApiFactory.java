package ru.mirotvortsev.usersapp.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mirotvortsev.usersapp.pojo.UsersResponse;
//Что такое паттерн синглтон

//Мы создали класс АпиФактори что бы создать здесь объект ретрофит
public class ApiFactory {

    private static ApiFactory apiFactory;
    private static Retrofit retrofit;
    private static String BASE_URL = "http://gitlab.65apps.com/65gb/static/raw/master/";

    //для того чтобы невозможно было создать объект этого класса, создаём приватный конструктор
    private ApiFactory() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static ApiFactory getInstance() {
        if (apiFactory == null) {
            apiFactory = new ApiFactory();
        }
        return apiFactory;
    }

    public static ApiService getApiService(){
        return retrofit.create(ApiService.class);
    }

}
