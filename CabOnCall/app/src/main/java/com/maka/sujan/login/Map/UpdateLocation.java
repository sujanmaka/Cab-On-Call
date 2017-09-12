package com.maka.sujan.login.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maka.sujan.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static String markerIDD;
    private String JSON_STRING;
    private String id;
    private Marker myMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        markerIDD = extras.getString("id");
       // Toast.makeText(getApplicationContext(), "The value of marker " + markerIDD, Toast.LENGTH_LONG).show();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        getJSON();

    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateLocation.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showUpdatedLoc();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_UPDATE);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showUpdatedLoc(){
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY_UPDATE);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                String  phone = jo.getString(Config.TAG_PHONE_UPDATE);


              if(phone.equals(markerIDD)) {
                    Double longitude = Double.parseDouble(jo.getString(Config.TAG_LONG_UPDATE));
                    Double latitude = Double.parseDouble(jo.getString(Config.TAG_LAT_UPDATE));


                    LatLng marker = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(marker).title("taxi"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));

                //Toast.makeText(getApplicationContext(), "The value of marker " + phone, Toast.LENGTH_LONG).show();
           }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
