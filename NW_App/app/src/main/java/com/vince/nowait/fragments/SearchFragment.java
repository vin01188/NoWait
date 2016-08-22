package com.vince.nowait.fragments;


import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import android.view.View;

import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vince.nowait.MainActivity;

import com.vince.nowait.Restaurant;
import com.vince.nowait.RestaurantDetailActivity;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.Coordinate;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends ListFragment
{

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;
    private ArrayList<String> restaurantNames;
    public String test;

    private String consumerKey,consumerSecret,token, tokenSecret;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mFirebaseDatabaseReference;
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        consumerKey ="";
        consumerSecret ="";
        token ="";
        tokenSecret ="";

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        restaurantNames = new ArrayList<String>();

        final DatabaseReference restaurantRef = mFirebaseDatabaseReference.child("Restaurants");
        restaurantRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String name = dataSnapshot.getKey();
                Log.v("E_CHILD_ADDED", name);
                restaurantNames.add(name);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        notes = new ArrayList<Note>();

        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey,consumerSecret,
                token, tokenSecret);
        YelpAPI yelpAPI = apiFactory.createAPI();
        Callback<SearchResponse> callback = new Callback<SearchResponse>(){
          @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response){

              SearchResponse searchResponse = response.body();
              ArrayList<Business> businesses = searchResponse.businesses();
              //loop to load in restaurants
              for (int i = 0; i < 10; i++){
                  Business currentbus = businesses.get(i);
                  String businessname = currentbus.name();
                  String address = currentbus.location().displayAddress().toString();
                  address = address.substring(1,address.length()-1);
                  String phonenumber = currentbus.displayPhone();
                  String imageurl = currentbus.imageUrl();
                  notes.add(new Note(businessname, "Address: " + address + "\nPhone number: " + phonenumber, imageurl));

                  //Adds restaurant to database if not already in the database.
                  if (restaurantNames.indexOf(businessname) == -1){
                      Restaurant rest = new Restaurant(businessname,address);
                      restaurantRef.child(businessname).setValue(rest);

                  }

              }



              noteAdapter = new NoteAdapter(getActivity(), notes);
              setListAdapter(noteAdapter);
          }
          @Override
            public void onFailure(Call<SearchResponse> call,Throwable t){
              //HTTP error happened do something to handle it
              Log.e("Errorcallback", "hi");
          }
        };
        Map<String,String> params = new HashMap<>();
        params.put("term",getArguments().getString("rest"));

        Double lat = getArguments().getDouble("lat");
        Double lng = getArguments().getDouble("lng");

        CoordinateOptions coord = new CoordinateOptions() {
            @Override
            public Double latitude() {
                return getArguments().getDouble("lat");
            }

            @Override
            public Double longitude() {
                return getArguments().getDouble("lng");
            }

            @Override
            public Double accuracy() {
                return null;
            }

            @Override
            public Double altitude() {
                return null;
            }

            @Override
            public Double altitudeAccuracy() {
                return null;
            }
        };
        Call<SearchResponse> call = yelpAPI.search(coord, params);
        call.enqueue(callback);



        //getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.black));
        //getListView().setDividerHeight(1);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        launchNoteDetailActivity(position);
    }

    private void launchNoteDetailActivity(int position)
    {
        //retrieve information for the note item that was clicked on
        Note note = (Note) getListAdapter().getItem(position);

        //create a new intent that launches the new activity
        Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);

        //pass along the information of the note that was clicked on to the new activity
        intent.putExtra(MainActivity.RESTAURANT_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.RESTAURANT_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.RESTAURANT_IMAGEURL_EXTRA, note.getImageurl());
        intent.putExtra(MainActivity.RESTAURANT_ID_EXTRA, note.getId());

        startActivity(intent);
    }
}
