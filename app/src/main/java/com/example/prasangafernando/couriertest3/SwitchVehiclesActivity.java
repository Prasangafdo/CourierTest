package com.example.prasangafernando.couriertest3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SwitchVehiclesActivity extends AppCompatActivity {
    EditText SVehicleID, SCourierID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_vehicles);

        SVehicleID =(EditText) findViewById(R.id.et_SvehicleID);
        SCourierID = (EditText) findViewById(R.id.et_SCourierID);
    }

    public void onSwitchVehicle(View view){

        String str_VID = SVehicleID.getText().toString();
        String  str_CID =SCourierID.getText().toString();
        String type = "switchVehicle";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,str_VID , str_CID);
    }
}
