package com.vince.nowait;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vince.nowait.fragments.MainFragment;
import com.vince.nowait.fragments.ProfileFragment;
import com.vince.nowait.fragments.RestaurantFragment;
import com.vince.nowait.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String RESTAURANT_ID_EXTRA = "com.vince.nowait.Restaurant Identifier";
    public static final String RESTAURANT_TITLE_EXTRA = "com.vince.nowait.Restaurant Title";
    public static final String RESTAURANT_MESSAGE_EXTRA = "com.vince.nowait.Restaurant Message";
    public static final String RESTAURANT_CATEGORY_EXTRA = "com.vince.nowait.Restaurant Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fm = getFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the Home action
            fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
        } else if (id == R.id.nav_profile) {
            // Handles the Profile action
            fm.beginTransaction().replace(R.id.content_frame, new ProfileFragment()).commit();

        } else if (id == R.id.nav_login) {
            // Handles the Log in Option
            Intent login = new Intent(this, Login.class);
            startActivity(login);
        } else if (id == R.id.nav_search) {
            // Handles the Search Option
            //Intent search = new Intent(this, Search.class);
            //startActivity(search);
            fm.beginTransaction().replace(R.id.content_frame, new SearchFragment()).commit();
        }else if (id == R.id.nav_restaurant) {
            fm.beginTransaction().replace(R.id.content_frame, new RestaurantFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
