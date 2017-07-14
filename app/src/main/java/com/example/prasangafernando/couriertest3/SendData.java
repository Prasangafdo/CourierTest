package com.example.prasangafernando.couriertest3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import static com.example.prasangafernando.couriertest3.R.id.et_latitude;

public class SendData extends AppCompatActivity {
    EditText name, surname, vID;
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

        //Qr
        scanbtn = (Button) findViewById(R.id.scanbtn);
        result = (TextView) findViewById(R.id.result);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendData.this, ScanActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
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

    public void buttonClick(View view){
        Intent mintent = new Intent(SendData.this, SecondActivity.class);
        startActivity(mintent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if(data != null){
                final Barcode barcode = data.getParcelableExtra("barcode");
                result.post(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(barcode.displayValue);
                    }
                });
            }
        }
    }
   /* public void onsendLocation(View view){
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_lat, str_long);//need to pass the location
    }*/



}
