package com.example.prasangafernando.couriertest3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SwitchVehiclesActivity extends AppCompatActivity {
    EditText SVehicleID;
    TextView SCourierID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_vehicles);

        SVehicleID =(EditText) findViewById(R.id.et_SvehicleID);
        SCourierID = (TextView) findViewById(R.id.et_SCourierID);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);//Getting saved data
        String data = prefs.getString("courierID", "Courier ID not found");
        SCourierID.setText(data);
    }

    public void onSwitchVehicle(View view) {

        String str_VID = SVehicleID.getText().toString();
        String str_CID = SCourierID.getText().toString();
        String type = "switchVehicle";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_VID, str_CID);
    }

}
