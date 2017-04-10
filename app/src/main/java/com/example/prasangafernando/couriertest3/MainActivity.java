package com.example.prasangafernando.couriertest3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText UsernameEt, PasswordEt;
    Button btnShowLocation;
    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);

        //Location code starts from here.

    }

public void onLogin(View view){
    String username =UsernameEt.getText().toString();
    String password =PasswordEt.getText().toString();
    String type = "Login";

    BackgroundWorker backgroundWorker = new BackgroundWorker(this);
    backgroundWorker.execute(type, username, password);
}
    public void openReg(View view){
        startActivity(new Intent(this, Register.class));
    }
}//only one brace
