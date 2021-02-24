package ru.mirotvortsev.usersapp.screens.aboutProgram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import ru.mirotvortsev.usersapp.R;
import ru.mirotvortsev.usersapp.screens.usersList.UsersListActivity;

public class AboutProgramActivity extends AppCompatActivity {

    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_program);

        chipNavigationBar = findViewById(R.id.chipNavigation);
        chipNavigationBar.setItemSelected(R.id.about, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        Intent intent = new Intent(AboutProgramActivity.this, UsersListActivity.class);
                        startActivity(intent);
                        break;
                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }

            }
        });

    }
}