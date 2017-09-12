package com.maka.sujan.login.Map;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maka.sujan.login.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;



import com.google.android.gms.maps.model.Marker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.maka.sujan.login.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private String JSON_STRING;
    private Marker myMarker;
    private String id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_p);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(this);
        getJSON();



    }




    private void showLongLat(){
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString(Config.TAG_ID);
                Double longitude = Double.parseDouble(jo.getString(Config.TAG_LONG));
                Double latitude = Double.parseDouble(jo.getString(Config.TAG_LAT));
                //  Toast.makeText(getApplicationContext(),latitude+longitude,Toast.LENGTH_LONG).show();


                //       Toast.makeText(this, "the values are" + longitude + latitude, Toast.LENGTH_SHORT).show();

                //    LatLng marker = new LatLng(Double.parseDouble(longitude),Double.parseDouble(latitude));

                // LatLng marker = new LatLng(42.7689876+i,87.9876587-i);


                LatLng marker = new LatLng(latitude,longitude);
                myMarker =  mMap.addMarker(new MarkerOptions().position(marker).title(id).snippet("driver's no.").icon(BitmapDescriptorFactory.fromResource(R.drawable.cab)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }




    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MapsActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showLongLat();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
/*
        if (marker.equals(myMarker)) {

           // startActivity(new Intent(MapsActivity.this, MainActivity.class));
            Toast.makeText(this, "You clicked on marker" , Toast.LENGTH_SHORT).show();

        }*/
        /*Toast.makeText(getApplicationContext(),
                "Marker Clicked: " + marker.getTitle(), Toast.LENGTH_LONG)
                .show();
        */

        AlertDialog.Builder builder = new AlertDialog.Builder(
                MapsActivity.this);
        builder.setTitle("Call");
        builder.setMessage("Call Taxi No. " + marker.getTitle());
        builder.setNegativeButton("Updated Loc",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        //Toast.makeText(getApplicationContext(),"No is clicked",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MapsActivity.this, UpdateLocation.class);
                        Bundle extras = new Bundle();
                        extras.putString("id", marker.getTitle());
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });


        builder.setNeutralButton("Profile",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(MapsActivity.this, DriverActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("id", marker.getTitle());

                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });

        builder.setPositiveButton("Call",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+ marker.getTitle()));
                        startActivity(callIntent);

                    }
                });


        builder.show();

        return false;


    }
}





