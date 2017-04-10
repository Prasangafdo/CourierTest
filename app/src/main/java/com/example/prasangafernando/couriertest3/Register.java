package com.example.prasangafernando.couriertest3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.prasangafernando.couriertest3.R.id.et_latitude;

public class Register extends AppCompatActivity {
    EditText name, surname;
    Button btnShowLocation;
    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText)findViewById(et_latitude);
        surname = (EditText) findViewById(R.id.et_longitude);


        //Location code starts here
        btnShowLocation = (Button) findViewById(R.id.btn_getLocation);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(Register.this);

                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(),
                            "Your Location is:\nLat: "+latitude+"\nLong: "+longitude, Toast.LENGTH_LONG).show();
                   //String et_latitude = et_latitude
                   /* name.setText((int) latitude);
                    surname.setText((int) longitude);*/
                   name.setText(String.valueOf(latitude));
                    surname.setText(String.valueOf(longitude));

                }else{
                    gps.showSettingsAlert();
                }
            }
        });

    }
    public void onReg(View view){
        String str_lat =name.getText().toString();
        String  str_long =surname.getText().toString();
        String type = "insert";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_lat, str_long);
    }
   /* public void onsendLocation(View view){
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_lat, str_long);//need to pass the location
    }*/



}
