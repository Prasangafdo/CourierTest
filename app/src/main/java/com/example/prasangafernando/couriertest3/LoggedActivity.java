package com.example.prasangafernando.couriertest3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoggedActivity extends AppCompatActivity {
    Button sendLocation;
    Button scanbtn;
    Button btnShiftVehicle;
    Button btnCompleteDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        sendLocation = (Button) findViewById(R.id.btnSendLocation);

        sendLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedActivity.this, SendData.class);
                startActivity(intent);
            }

        });


        scanbtn = (Button) findViewById(R.id.btnShiftPackages);


        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedActivity.this, ShiftPackagesActivity.class);
                startActivity(intent);
            }
        });

        btnShiftVehicle = (Button) findViewById(R.id.btnShiftVehicle);

        btnShiftVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedActivity.this, SwitchVehiclesActivity.class);
                startActivity(intent);
            }

        });

        btnCompleteDelivery = (Button) findViewById(R.id.btnCompleteDelivery);

        btnCompleteDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedActivity.this, CompleteDeliveryActivity.class);
                startActivity(intent);
            }

        });


    }
}

