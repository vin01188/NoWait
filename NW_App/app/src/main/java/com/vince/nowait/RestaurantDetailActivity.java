package com.vince.nowait;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vince.nowait.fragments.RestaurantViewFragment;

public class RestaurantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        createAndAddFragment();
    }

    private void createAndAddFragment()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RestaurantViewFragment restaurantViewFragment = new RestaurantViewFragment();
        setTitle(R.string.viewFragmentTitle);
        //Put the fragment into the linear layout with the tag "RESTAURANT_VIEW_FRAGMENT"
        fragmentTransaction.add(R.id.restaurant_container, restaurantViewFragment, "RESTAURANT_VIEW_FRAGMENT");
        fragmentTransaction.commit();
    }
}
