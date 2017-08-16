package com.example.prasangafernando.couriertest3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by prasanga on 3/17/17.
 */
public class GetID extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    GetID(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String ... params) {
        String type = params[0];
        String userID_url = "https://rapiddelivery.000webhostapp.com/MobilePhp/userid.php";
        String vehicleID_url = "https://rapiddelivery.000webhostapp.com/MobilePhp/vehicleid.php";

         if (type.equals("getUserID")) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.context);//Getting saved data
            String data = prefs.getString("username", "Username not found");
            String username = data;

            try {
                URL url = new URL(userID_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream , "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");//Passing the username to retrieve courier ID
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result= "";
                String line= "";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }
                SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this.context);//Save
                SharedPreferences.Editor editor = prefs1.edit();
                editor.putString("courierID",result); //saving courier ID
                editor.apply();

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else  if (type.equals("getVehicleID")) {
             SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.context);//Getting saved data
             String data = prefs.getString("username", "Username not found");
             String username = data;

             try {
                 URL url = new URL(vehicleID_url);
                 HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                 httpURLConnection.setRequestMethod("POST");
                 httpURLConnection.setDoOutput(true);
                 httpURLConnection.setDoInput(true);
                 OutputStream outputStream = httpURLConnection.getOutputStream();
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream , "UTF-8"));
                 String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8"); //Passing the username to retrieve vehicle ID
                 bufferedWriter.write(post_data);
                 bufferedWriter.flush();
                 bufferedWriter.close();
                 outputStream.close();
                 InputStream inputStream = httpURLConnection.getInputStream();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                 String result= "";
                 String line= "";
                 while ((line = bufferedReader.readLine())!=null){
                     result+=line;
                 }
                 /////////////

                 ////////////
                 SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this.context);//Save
                 SharedPreferences.Editor editor = prefs1.edit();
                 editor.putString("VID",result); //saving vehicle ID
                 editor.apply();

                 bufferedReader.close();
                 inputStream.close();
                 httpURLConnection.disconnect();
                 return result;
             } catch (MalformedURLException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }

        return null;
    }

    @Override
    protected void onPreExecute() {
     // alertDialog = new AlertDialog.Builder(context).create();
     //  alertDialog.setTitle("Login Status");//Edit this

    }

    @Override
    public void onPostExecute(String result) {
       /*   if(SQLresult.equals("Login failed...")){ //If failed
              alertDialog.setMessage("Login Failed");
              alertDialog.show();
              */



        //Intent intent = new Intent(context, LoggedActivity.class);
       // alertDialog.setMessage("Login success");

      //  alertDialog.setMessage(result);
      //  alertDialog.show();
      //  context.startActivity(intent);
     //   ((Activity)context).finish();//Need to save the data and do the querying
       }
     //  else{
              //If success

   // }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}


