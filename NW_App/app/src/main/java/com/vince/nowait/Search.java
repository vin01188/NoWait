package com.vince.nowait;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class Search extends AppCompatActivity {

    EditText restaurant;
    EditText location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        restaurant = (EditText) findViewById(R.id.restaurantEdit);
        location = (EditText) findViewById(R.id.locationEdit);

    }

    public void searchButton(View view){
        String rest = restaurant.getText().toString();
        String address = location.getText().toString();
        LatLng temp = getLocationFromAddress(address);
        if (temp == null || rest.equals("")){
            Toast toast = Toast.makeText(this, "Not a valid Description/location", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else{
            Intent goingBack = new Intent();
            goingBack.putExtra("Lat",temp.latitude);
            goingBack.putExtra("Long",temp.longitude);
            goingBack.putExtra("Rest",rest);
            setResult(RESULT_OK, goingBack);
            finish();
        }
    }

    public LatLng getLocationFromAddress(String strAddress){
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1 = null;

        try{
            address = coder.getFromLocationName(strAddress,5);
            if(address ==null || address.size() == 0){
                return null;
            }else{
                Address location = address.get(0);
                location.getLatitude();
                location.getLongitude();

                p1 = new LatLng(location.getLatitude(), (location.getLongitude()));

                return p1;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return p1;

    }
}
