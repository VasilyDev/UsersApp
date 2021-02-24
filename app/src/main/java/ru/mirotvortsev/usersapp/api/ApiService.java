package ru.mirotvortsev.usersapp.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.mirotvortsev.usersapp.pojo.UsersResponse;

//здесь будем указывать все запросы на данный сайт
public interface ApiService {
    @GET("testTask.json")
        //для того что бы слушать результат запроса необходимо использовать object Observable
    Observable<UsersResponse> getUsers();
}
