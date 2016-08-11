package com.vince.nowait.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vince.nowait.R;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i("Profile Fragment", "ONCREATE PROFILE FRAGMENT");

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}

