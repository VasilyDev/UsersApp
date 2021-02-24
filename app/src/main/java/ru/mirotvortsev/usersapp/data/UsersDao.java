package ru.mirotvortsev.usersapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import ru.mirotvortsev.usersapp.pojo.User;

//Создаём интерфейс DAO для работы с таблицей БД
@Dao
public interface UsersDao {
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<User>users);

    @Query("DELETE FROM users")
    void deleteAllUsers();
}
