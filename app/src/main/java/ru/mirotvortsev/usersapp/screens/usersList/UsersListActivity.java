package ru.mirotvortsev.usersapp.screens.usersList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import java.util.ArrayList;
import java.util.List;
import ru.mirotvortsev.usersapp.R;
import ru.mirotvortsev.usersapp.adapters.UsersAdapters;
import ru.mirotvortsev.usersapp.pojo.User;
import ru.mirotvortsev.usersapp.screens.UserViewModel;
import ru.mirotvortsev.usersapp.screens.aboutProgram.AboutProgramActivity;
import ru.mirotvortsev.usersapp.screens.userCard.UserCardActivity;


public class UsersListActivity extends AppCompatActivity implements RecyclerViewClickInterface {
    private static final String TAG = "onItemClick";
    private List<User> users = new ArrayList<>();
    private RecyclerView recyclerView;
    private UsersAdapters adapter;
    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment = null;
    private RecyclerViewClickInterface recyclerViewClickInterface;
    int position;
    private UserViewModel viewModel;


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new UsersAdapters(users, this);
        adapter.setUsers(new ArrayList<User>());



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUsers(users);
            }
        });

        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null) {
                    Toast.makeText(UsersListActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    viewModel.clearErrors();
                }
            }
        });

        viewModel.loadData();

        chipNavigationBar = findViewById(R.id.chipNavigation);
        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.about:
                        Intent intent1 = new Intent(UsersListActivity.this, AboutProgramActivity.class);
                        startActivity(intent1);
                        break;
                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: clicked!");
        Toast.makeText(this, "You choose the user by the number = " + position, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, UserCardActivity.class);
//        intent.putExtra("fname", users.get(position).getFName());
//        intent.putExtra("lname", users.get(position).getLName());
//        intent.putExtra("foto", users.get(position).getAvatrUrl());
//        intent.putExtra("email", users.get(position).getEmail());
        startActivity(intent);
    }
}



