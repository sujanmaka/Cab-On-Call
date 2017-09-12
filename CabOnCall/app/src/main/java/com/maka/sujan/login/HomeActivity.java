package com.maka.sujan.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.maka.sujan.login.Login.Main2ActivityP;
import com.maka.sujan.login.LoginD.MainActivityD;

public class HomeActivity extends AppCompatActivity {

    private Button btnPassenger;
    private Button btnDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPassenger = (Button) findViewById(R.id.btnPassenger);
        btnDriver = (Button) findViewById(R.id.btnDriver);

        btnPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(HomeActivity.this, Main2ActivityP.class);
                startActivity(intent);

            }
        });

        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(HomeActivity.this, MainActivityD.class);
                startActivity(intent);

            }
        });
    }
}
