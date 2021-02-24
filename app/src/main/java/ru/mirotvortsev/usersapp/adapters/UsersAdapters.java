package ru.mirotvortsev.usersapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.ArrayList;
import java.util.List;

import ru.mirotvortsev.usersapp.R;
import ru.mirotvortsev.usersapp.pojo.User;
import ru.mirotvortsev.usersapp.screens.userCard.UserCardActivity;
import ru.mirotvortsev.usersapp.screens.usersList.RecyclerViewClickInterface;
import ru.mirotvortsev.usersapp.screens.usersList.UsersListActivity;

// делаем адаптер который у нас всё отображает
public class UsersAdapters extends RecyclerView.Adapter<UsersAdapters.UsersViewHolder> {


    private RecyclerViewClickInterface recyclerViewClickInterface;
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        //что бы после добавления сотрудников у нас обновился список.
        notifyDataSetChanged();

    }


    public UsersAdapters(List<User> users, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.users = users;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_item, viewGroup, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int i) {
        User user = users.get(i);
        usersViewHolder.textViewName.setText(user.getFName());
        usersViewHolder.textViewLastName.setText(user.getLName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewLastName;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (recyclerViewClickInterface != null) {
                        recyclerViewClickInterface.onItemClick(getAdapterPosition());
                    }
                }
            });

        }
    }
}



