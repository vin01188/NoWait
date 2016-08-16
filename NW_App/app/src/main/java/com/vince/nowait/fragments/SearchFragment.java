package com.vince.nowait.fragments;


import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import android.view.View;

import android.widget.ListView;

import com.vince.nowait.MainActivity;

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
    public String test;
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        notes = new ArrayList<Note>();

        YelpAPIFactory apiFactory = new YelpAPIFactory("NRgw6sbUbjHMtLSa6NOn6Q", "7Xztu7llvrePGYyL1Q8GmUz7gfA",
                "BGwMfP3C0etBQ1WEBYINnXvzId7vDZJ0", "vZr0uqC-nBXk2H2Dv6zRM5mRtxo");
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
