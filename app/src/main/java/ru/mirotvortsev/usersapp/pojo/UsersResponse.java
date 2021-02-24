package ru.mirotvortsev.usersapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {
    @SerializedName("response")
    @Expose
    private List<User> response = null;

    public List<User> getResponse() {
        return response;
    }

    public void setResponse(List<User> response) {
        this.response = response;
    }
}
