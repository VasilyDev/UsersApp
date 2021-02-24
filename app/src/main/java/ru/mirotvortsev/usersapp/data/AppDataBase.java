package ru.mirotvortsev.usersapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.gson.internal.$Gson$Preconditions;

import ru.mirotvortsev.usersapp.pojo.User;

//Создаём базу данных
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DB_NAME = "users.db";
    private static AppDataBase dataBase;
    private static final Object LOCK = new Object();

    public static AppDataBase getInstance(Context context) {
        synchronized (LOCK) {
            if (dataBase == null) {
                dataBase = Room.databaseBuilder(context, AppDataBase.class, DB_NAME).build();
            }
            return dataBase;
        }
    }




    public abstract UsersDao usersDao();
}