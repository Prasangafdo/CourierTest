package com.example.prasangafernando.couriertest3;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;

public class ShiftPackagesActivity extends AppCompatActivity {

    Button scanbtn;
    TextView result;
    TextView TPcourierID;
    EditText et_TPvehicleID;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_packages);

        scanbtn = (Button) findViewById(R.id.btn_scan);
        result = (TextView) findViewById(R.id.result);
        TPcourierID =(TextView) findViewById(R.id.et_TPcourierID);
        et_TPvehicleID = (EditText) findViewById(R.id.et_TPvehicleID);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShiftPackagesActivity.this, ScanActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);//Getting saved data
        String data = prefs.getString("courierID", "Courier ID not found");
        TPcourierID.setText(data);
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

   public void onTransferParcel(View view){

        String str1 = TPcourierID.getText().toString();
        String  str2 =et_TPvehicleID.getText().toString();
        String type = "TransferParcel";
        String ParcelID = result.getText().toString();

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,str1 , str2, ParcelID);
    }
}
