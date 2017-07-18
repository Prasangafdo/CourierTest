package com.example.prasangafernando.couriertest3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.prasangafernando.couriertest3.R.id.et_latitude;

public class SendData extends AppCompatActivity {
    EditText name, surname, vID, SVehicleID, SCourierID;
    Button btnShowLocation;
    GPSTracker gps;
    Button scanbtn;
    TextView result;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        name = (EditText)findViewById(et_latitude);
        surname = (EditText) findViewById(R.id.et_longitude);
        vID = (EditText) findViewById(R.id.et_vehicleID);
        SVehicleID =(EditText) findViewById(R.id.et_SvehicleID);
        SCourierID = (EditText) findViewById(R.id.et_SCourierID);



        gps = new GPSTracker(SendData.this);

        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Toast.makeText(getApplicationContext(),
                    "Your Location is:\nLat: "+latitude+"\nLong: "+longitude, Toast.LENGTH_LONG).show();

            name.setText(String.valueOf(latitude));
            surname.setText(String.valueOf(longitude));

        }else{
            gps.showSettingsAlert();
        }

        //Location code starts here
        //Recorrects the location when the button is pressed.
        btnShowLocation = (Button) findViewById(R.id.btn_getLocation);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                gps = new GPSTracker(SendData.this);

                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(),
                            "Your Location is:\nLat: "+latitude+"\nLong: "+longitude, Toast.LENGTH_LONG).show();

                    name.setText(String.valueOf(latitude));
                    surname.setText(String.valueOf(longitude));

                }else{
                    gps.showSettingsAlert();
                }
            }
        });

    }
    public void onReg(View view){
        String  str_VID = vID.getText().toString();
        String str_lat =name.getText().toString();
        String  str_long =surname.getText().toString();
        String type = "insert";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,str_VID , str_lat, str_long);
    }



   /* public void onsendLocation(View view){
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_lat, str_long);//need to pass the location
    }*/



}
