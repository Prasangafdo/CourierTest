package com.example.prasangafernando.couriertest3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.prasangafernando.couriertest3.R.id.et_latitude;

public class SendData extends AppCompatActivity {
    TextView vID;
    TextView SCourierID;
    Button btnShowLocation;
    GPSTracker gps;
    TextView tvlatitude;
    TextView tvlongitude;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tvlatitude = (TextView)findViewById(et_latitude);
        tvlongitude = (TextView) findViewById(R.id.et_longitude);
        vID = (TextView) findViewById(R.id.et_vehicleID);
       // SVehicleID =(EditText) findViewById(R.id.et_SvehicleID);
        SCourierID = (TextView) findViewById(R.id.et_SCourierID);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);//Getting saved data
        String data = prefs.getString("VID", "VehicleID not found");
         vID.setText(data);//Setting vehicle ID automatically

        gps = new GPSTracker(SendData.this);

        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Toast.makeText(getApplicationContext(),
                    "Your Location is:\nLat: "+latitude+"\nLong: "+longitude, Toast.LENGTH_LONG).show();

            tvlatitude.setText(String.valueOf(latitude));
            tvlongitude.setText(String.valueOf(longitude));

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

                    tvlatitude.setText(String.valueOf(latitude));
                    tvlongitude.setText(String.valueOf(longitude));

                }else{
                    gps.showSettingsAlert();
                }
            }
        });

    }
    public void onReg(View view){

        String  str_VID = vID.getText().toString();
        String str_lat =tvlatitude.getText().toString();
        String  str_long =tvlongitude.getText().toString();
        String type = "insert";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,str_VID , str_lat, str_long);
    }

}
