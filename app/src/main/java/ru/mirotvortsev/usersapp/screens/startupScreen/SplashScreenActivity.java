package ru.mirotvortsev.usersapp.screens.startupScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

import ru.mirotvortsev.usersapp.R;
import ru.mirotvortsev.usersapp.screens.usersList.UsersListActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Timer timer;
    private int i = 0;
    private String TAG = "SplashScreenActivity";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar2);

        // internet url "https://gifer.com/ru/MdV"
        Glide
                .with(this)
                .load(R.drawable.loading)
                .into(imageView);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (i < 100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                } else {
                    timer.cancel();

                    Intent intent = new Intent(SplashScreenActivity.this, UsersListActivity.class);
                    startActivity(intent);
                    finish();
                    Log.d(TAG, "Hello! Splash screen is done!");
                }
            }
        }, 0, 50);

    }





}
