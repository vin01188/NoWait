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
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vince.nowait.MainActivity;
import com.vince.nowait.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RestaurantFragment extends Fragment {

    Button timerButton;
    Chronometer waitTimer;
    private boolean timerOn = false;
    private long lastPause;
    TextView title, message;

    public RestaurantFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        waitTimer = (Chronometer) view.findViewById(R.id.waitTimer);

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
        //ImageView image = (ImageView) view.findViewById(R.id.profileImageBig);
        //image.setImageResource(R.drawable.profile_icon);

        //Get references to the name, message, and icon values
        title = (TextView) view.findViewById(R.id.restaurantName);
        message = (TextView) view.findViewById(R.id.restaurantMessage);
        ImageView icon = (ImageView) view.findViewById(R.id.restaurantIcon);

        Intent intent = getActivity().getIntent();

        //Retrieve the values from the row that was clicked
        title.setText(intent.getExtras().getString(MainActivity.RESTAURANT_TITLE_EXTRA));
        message.setText(intent.getExtras().getString(MainActivity.RESTAURANT_MESSAGE_EXTRA));

        String url = intent.getExtras().getString(MainActivity.RESTAURANT_IMAGEURL_EXTRA);
        Picasso.with(getActivity()).load(url).into(icon);


        return view;
    }

    public void manualTime(View view){
        String name = title.toString();
        String mess = message.toString();
        String address = mess.substring(mess.indexOf(": ") + 2, mess.indexOf("\n"));

    }

}

