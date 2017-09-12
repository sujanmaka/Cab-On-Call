package com.maka.sujan.login.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


import com.maka.sujan.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DriverActivity extends AppCompatActivity {
    private String JSON_STRING;
    private String JSON_STRINGR;
    private TextView nameDriver;
    private TextView phoneDriver;
    public static String markerID;
    private Button rateNow;
    private RatingBar ratingBar;
    private TextView noRating;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_p);
        nameDriver = (TextView) findViewById(R.id.nameDriver);
        phoneDriver = (TextView) findViewById(R.id.phoneDriver);
        rateNow = (Button) findViewById(R.id.rateNow);
        noRating = (TextView) findViewById(R.id.noRating);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        markerID = extras.getString("id");


       // Toast.makeText(getApplicationContext(), "The value of marker " + markerID, Toast.LENGTH_LONG).show();
       /*   nameDriver.setText("Sujan");
        phoneDriver.setText("9849127164");
*/
         getJSON();
         getJSONR();


         rateNow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(DriverActivity.this, RateNow.class);
                 startActivity(intent);
             }
         });




    }





    private void showDriverInfo(){
        JSONObject jsonObject = null;



        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY_DRIVER);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);


                String id = jo.getString(Config.TAG_ID_DRIVER);
                String name = jo.getString(Config.TAG_NAME);
                String  phone = jo.getString(Config.TAG_PHONE);

             // Toast.makeText(this, "the name is " + id + name + phone , Toast.LENGTH_SHORT).show();

                //MIDD = Double.parseDouble(phone);

                if(phone.equals(markerID)){
                    nameDriver.setText(name);
                    phoneDriver.setText(phone);
                }


    }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DriverActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showDriverInfo();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_DRIVER_INFO);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    private void getJSONR(){
        class GetJSONR extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DriverActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRINGR = s;
                showRating();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_RATING);
                return s;
            }
        }
        GetJSONR gj = new GetJSONR();
        gj.execute();
    }

    private void showRating(){
        JSONObject jsonObject = null;
        int k=0;
        float j=0;
       /* ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();*/
        try {
            jsonObject = new JSONObject(JSON_STRINGR);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY_RATING);
         //   k = result.length();

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_R_ID);
                String rating =  jo.getString(Config.TAG_RATING);
                String d_id = jo.getString(Config.TAG_D_ID);
                String u_id =  jo.getString(Config.TAG_U_ID);

                if(d_id.equals(markerID)){
                    j = j + Float.parseFloat(rating);
                    k=k+1;
                }

               // j = j + Float.parseFloat(rating);


            }
            /*Toast.makeText(ViewRating.this,
                    "You have viewed RAting " + j/result.length(),
                    Toast.LENGTH_LONG).show();*/

            ratingBar = (RatingBar) findViewById(R.id.ratingBar);
            ratingBar.setRating(j/k);
            noRating.setText("No. of raters " + k);



        } catch (JSONException e) {
            e.printStackTrace();
        }




    }




}
