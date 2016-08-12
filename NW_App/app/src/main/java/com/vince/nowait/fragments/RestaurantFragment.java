package com.vince.nowait.fragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import com.vince.nowait.R;

public class RestaurantFragment extends Fragment {

    Button timerButton;
    Chronometer waitTimer;
    boolean timerOn = false;

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
                    waitTimer.start();
                    timerOn = true;
                } else {
                    timerOn = false;
                    waitTimer.stop();
                }
            }
        });
        //ImageView image = (ImageView) view.findViewById(R.id.profileImageBig);
        //image.setImageResource(R.drawable.profile_icon);
        return view;
    }
}

