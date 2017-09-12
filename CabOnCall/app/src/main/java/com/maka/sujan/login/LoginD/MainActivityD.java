package com.maka.sujan.login.LoginD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maka.sujan.login.LocationD.Main2ActivityD;
import com.maka.sujan.login.LocationD.ViewRatingActivityD;
import com.maka.sujan.login.R;

import java.util.HashMap;

public class MainActivityD extends Activity {

    private TextView txtName;
    private TextView txtPhone;
    private Button btnLogout;
    private Button btnLocation;
    private Button btnViewRating;
    public static String phone;

    private SQLiteHandlerD db;
    private SessionManagerD session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_d);

        txtName = (TextView) findViewById(R.id.name);
        txtPhone = (TextView) findViewById(R.id.phone);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnViewRating = (Button) findViewById(R.id.btnViewRating);

        // SqLite database handler
        db = new SQLiteHandlerD(getApplicationContext());

        // session manager
        session = new SessionManagerD(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        phone = user.get("phone");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtPhone.setText(phone);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityD.this, Main2ActivityD.class);
                startActivity(intent);
            }
        });

        btnViewRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityD.this, ViewRatingActivityD.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivityD.this, LoginActivityD.class);
        startActivity(intent);
        finish();
    }



}

