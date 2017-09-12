package com.maka.sujan.login.LocationD;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

import com.maka.sujan.login.LoginD.MainActivityD;
import com.maka.sujan.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewRatingActivityD extends AppCompatActivity {

    private String JSON_STRINGR;
    private RatingBar ratingBar;
    private TextView noRating;
    private TextView ratingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rating_d);
        noRating = (TextView) findViewById(R.id.noRating);
        ratingValue = (TextView) findViewById(R.id.ratingValue);

        getJSONR();
    }

    private void getJSONR(){
        class GetJSONR extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewRatingActivityD.this,"Fetching Data","Wait...",false,false);
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
                RequestHandlerD rh = new RequestHandlerD();
                String s = rh.sendGetRequest(ConfigD.URL_RATING);
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
            JSONArray result = jsonObject.getJSONArray(ConfigD.TAG_JSON_ARRAY_RATING);
            //   k = result.length();

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(ConfigD.TAG_R_ID);
                String rating =  jo.getString(ConfigD.TAG_RATING);
                String d_id = jo.getString(ConfigD.TAG_D_ID);
                String u_id =  jo.getString(ConfigD.TAG_U_ID);

                if(d_id.equals(MainActivityD.phone)){
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
            ratingValue.setText("Your Current Rating is " + j/k);
            noRating.setText("No. of raters " + k);



        } catch (JSONException e) {
            e.printStackTrace();
        }




    }



}
