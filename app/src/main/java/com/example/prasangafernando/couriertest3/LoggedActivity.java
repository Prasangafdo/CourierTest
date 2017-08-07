package com.example.prasangafernando.couriertest3;

import android.content.Context;
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
    Context context;
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

                GetID getVehicleID = new GetID(LoggedActivity.this);//Getting vehicleID ID from the database
                String newType = "getVehicleID";
                getVehicleID.execute(newType);

            }

        });


        scanbtn = (Button) findViewById(R.id.btnShiftPackages);


        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedActivity.this, ShiftPackagesActivity.class);
                startActivity(intent);

                GetID getCourierID = new GetID(LoggedActivity.this);//Getting courier ID from the database
                String newType = "getUserID";
                getCourierID.execute(newType);
            }
        });

        btnShiftVehicle = (Button) findViewById(R.id.btnShiftVehicle);

        btnShiftVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedActivity.this, SwitchVehiclesActivity.class);
                startActivity(intent);

                GetID getCourierID = new GetID(LoggedActivity.this);//Getting courier ID from the database
                String newType = "getUserID";
                getCourierID.execute(newType);
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

