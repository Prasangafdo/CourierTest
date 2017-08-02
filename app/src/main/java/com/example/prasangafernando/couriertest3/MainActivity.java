package com.example.prasangafernando.couriertest3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText UsernameEt, PasswordEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);

    }

public void onLogin(View view){
    String username =UsernameEt.getText().toString();
    String password =PasswordEt.getText().toString();
    String type = "Login";

    BackgroundWorker backgroundWorker = new BackgroundWorker(this);
    backgroundWorker.execute(type, username, password);
}
    public void openReg(View view){
        startActivity(new Intent(this, SendData.class));
    }
}
