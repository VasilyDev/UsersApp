package ru.mirotvortsev.usersapp.screens.userCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import ru.mirotvortsev.usersapp.R;
import ru.mirotvortsev.usersapp.pojo.User;

public class UserCardActivity extends AppCompatActivity {

    private TextView fname;
    private TextView lname;
    private ImageView foto;
    private TextView email;
    private static final String TAG = "UserCardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_card);
        TextView fname = findViewById(R.id.fname);
        TextView lname = findViewById(R.id.lname);
        ImageView foto = findViewById(R.id.foto);

        String name = "Username not set";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString(name);
        }
        fname.setText(name);

        Intent intent = getIntent();
//        fname.setText(intent.getStringExtra("fname"));
//        lname.setText(intent.getStringExtra("lname"));
//        foto.setImageURI(Uri.parse(intent.getStringExtra("foto")));
//        email.setText(intent.getStringExtra("email"));


    }
}