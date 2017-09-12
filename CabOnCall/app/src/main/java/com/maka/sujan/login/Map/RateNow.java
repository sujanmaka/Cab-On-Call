package com.maka.sujan.login.Map;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maka.sujan.login.Login.Main2ActivityP;
import com.maka.sujan.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RateNow extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;
    private String JSON_STRINGR;
    private String du_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_now_p);

        du_id = DriverActivity.markerID + Main2ActivityP.email;

        getJSONR();
        addListenerOnRatingBar();
        addListenerOnButton();
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });

    }

    public void addListenerOnButton() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //if click on me, then display the current rating value.
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*Toast.makeText(MainActivity.this,
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();*/
              //  btnSubmit.setVisibility(View.GONE);
                deleteRating();
                addRating();
                /*Intent intent = new Intent(RateNow.this, DriverActivity.class);
                startActivity(intent);*/
            }

        });
        //ratingBar.setIsIndicator(true);

    }

    private void addRating(){

        final String rating = String.valueOf(ratingBar.getRating());

        Toast.makeText(RateNow.this,
                rating,
                Toast.LENGTH_SHORT).show();


        class AddRating extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RateNow.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RateNow.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_RATING, rating);
                params.put(Config.DRIVER_ID, DriverActivity.markerID);
                params.put(Config.USER_ID, Main2ActivityP.email);
                params.put(Config.DRIVERUSER_ID, du_id);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddRating ae = new AddRating();
        ae.execute();
    }




    private void getJSONR(){
        class GetJSONR extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RateNow.this,"Fetching Data","Wait...",false,false);
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

        float j=0;

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

                if(d_id.equals(DriverActivity.markerID)) {
                    if (u_id.equals(Main2ActivityP.email)) {
                        j =  Float.parseFloat(rating);

                    }
                }

                // j = j + Float.parseFloat(rating);


            }
            /*Toast.makeText(ViewRating.this,
                    "You have viewed RAting " + j/result.length(),
                    Toast.LENGTH_LONG).show();*/

            ratingBar = (RatingBar) findViewById(R.id.ratingBar);
            ratingBar.setRating(j);




        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    private void deleteRating(){


        class DeleteLocation extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RateNow.this, "Deleting...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RateNow.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {

                /*HashMap<String,String> params = new HashMap<>();
                params.put(Config.DRIVER_ID, DriverActivity.markerID);
                params.put(Config.USER_ID, Main2ActivityP.email);
*/
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_RATING, du_id);


                return s;

            }
        }

        DeleteLocation de = new DeleteLocation();
        de.execute();
    }







}
