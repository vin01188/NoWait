package com.vince.nowait;

import android.app.FragmentManager;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vince.nowait.fragments.RestaurantFragment;

public class RestaurantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        createAndAddFragment();
    }

    private void createAndAddFragment()
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RestaurantFragment restaurantFragment = new RestaurantFragment();

        setTitle(R.string.viewFragmentTitle);
        //Put the fragment into the linear layout
        fragmentTransaction.add(R.id.restaurant_container, restaurantFragment, "RESTAURANT_FRAGMENT");
        fragmentTransaction.commit();
    }
}
