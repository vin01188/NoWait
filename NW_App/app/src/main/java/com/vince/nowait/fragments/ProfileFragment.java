package com.vince.nowait.fragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vince.nowait.MainActivity;
import com.vince.nowait.R;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView name = (TextView) view.findViewById(R.id.profileName);
        //name.setText(getActivity().getIntent().getExtras().getString(MainActivity.mUsername));
        name.setText(getArguments().getString("name"));

        // Set profile picture
        if(getArguments().getString("profilePic") != null)
        {
            ImageView image = (ImageView) view.findViewById(R.id.profileImageBig);
            Picasso.with(getActivity()).load(getArguments().getString("profilePic")).into(image);
            //Picasso.with(getActivity()).load(getActivity().getIntent().getExtras().getString(MainActivity.mPhotoUrl)).into(image);
        }


        return view;
    }
}

