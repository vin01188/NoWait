package com.vince.nowait.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vince.nowait.MainActivity;
import com.vince.nowait.R;
import com.vince.nowait.Restaurant;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class RestaurantFragment extends Fragment {

    Button timerButton;
    Chronometer waitTimer;
    private boolean timerOn = false;
    private long lastPause;
    TextView title, message, time;
    Button manualButton;
    EditText minutes;
    private DatabaseReference mFirebaseDatabaseReference;

    public RestaurantFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();



        waitTimer = (Chronometer) view.findViewById(R.id.waitTimer);
        minutes = (EditText) view.findViewById(R.id.minutes);
        timerButton = (Button) view.findViewById(R.id.timerButton);
        timerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!timerOn) {
                    if (lastPause == 0) {
                        waitTimer.setBase(SystemClock.elapsedRealtime());
                    } else {
                        waitTimer.setBase(waitTimer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                    }

                    waitTimer.start();
                    timerOn = true;
                } else {
                    lastPause = SystemClock.elapsedRealtime();
                    waitTimer.stop();
                    timerOn = false;
                }
            }
        });


        //Get references to the name, message, and icon values
        title = (TextView) view.findViewById(R.id.restaurantName);
        message = (TextView) view.findViewById(R.id.restaurantMessage);
        time = (TextView) view.findViewById(R.id.average);
        ImageView icon = (ImageView) view.findViewById(R.id.restaurantIcon);

        Intent intent = getActivity().getIntent();

        //Retrieve the values from the row that was clicked
        title.setText(intent.getExtras().getString(MainActivity.RESTAURANT_TITLE_EXTRA));
        message.setText(intent.getExtras().getString(MainActivity.RESTAURANT_MESSAGE_EXTRA));

        String url = intent.getExtras().getString(MainActivity.RESTAURANT_IMAGEURL_EXTRA);
        Picasso.with(getActivity()).load(url).into(icon);

        DatabaseReference restaurantRef = mFirebaseDatabaseReference.
                child("Restaurants").child(title.getText().toString());

        String mess = message.getText().toString();
        String address = mess.substring(mess.indexOf(": ") + 2, mess.indexOf("\n"));
        final Restaurant temp = new Restaurant(title.getText().toString(),address);


        restaurantRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Restaurant rest = dataSnapshot.getValue(Restaurant.class);
                Log.v("E_TIME_FOUND",Integer.toString(rest.getWaitTime()));
                temp.setWaitTime(rest.getWaitTime());
                temp.setCheckins(rest.getCheckins());
                if (rest.getCheckins()==0){
                    time.setText("No checkins yet");
                }else {
                    time.setText("Wait Time Average: " + Integer.toString(rest.getWaitTime()) +
                            " minutes");
                    minutes.setText("");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        final DatabaseReference rest2ref = mFirebaseDatabaseReference.child("Restaurants");

        manualButton = (Button) view.findViewById(R.id.manualButton);
        manualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!minutes.getText().toString().equals("")) {
                    temp.checkIn(Integer.parseInt(minutes.getText().toString()));
                    rest2ref.child(temp.getName()).setValue(temp);
                    try  {
                        InputMethodManager imm;
                        imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                }
            }
        });


        return view;
    }

}

