package com.vince.nowait.fragments;


import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.vince.nowait.MainActivity;
import com.vince.nowait.RestaurantDetailActivity;
import com.vince.nowait.fragments.Note;
import com.vince.nowait.fragments.NoteAdapter;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.SearchResponse;

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
        /*
        String[] values = new String[]{"mcd", "Burger King", "Chik-Fil-A", "Subway",
                "Papa John's", "Domino's", "Pizza Hut", "Arby's", "Pie Five", "Jimmy John's",
                "Burger place", "Pizza Place"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);
        */
        /*
        new AsyncTask<Void,Void,String>() {
            @Override
            protected String doInBackground(Void... params) {
                YelpAPIFactory apiFactory = new YelpAPIFactory("NRgw6sbUbjHMtLSa6NOn6Q", "7Xztu7llvrePGYyL1Q8GmUz7gfA",
                        "BGwMfP3C0etBQ1WEBYINnXvzId7vDZJ0", "vZr0uqC-nBXk2H2Dv6zRM5mRtxo");
                YelpAPI yelpAPI = apiFactory.createAPI();
                Map<String,String> params1 = new HashMap<>();
                params1.put("term","food");
                Call<SearchResponse> call = yelpAPI.search("Ellicott City", params1);
                try {
                    Response<SearchResponse> response = call.execute();
                    Log.i("test", "response found");
                    return response.body().businesses().get(0).name().toString();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "hi";
                }
            }

            @Override
            protected void onPostExecute(String result){
                Log.i("result", result);
                test = result;
                notes.add(new Note(test, "Address: \nPhone number:", Note.Category.PERSONAL));
            }
        }.execute();
        */
        notes = new ArrayList<Note>();

        YelpAPIFactory apiFactory = new YelpAPIFactory("NRgw6sbUbjHMtLSa6NOn6Q", "7Xztu7llvrePGYyL1Q8GmUz7gfA",
                "BGwMfP3C0etBQ1WEBYINnXvzId7vDZJ0", "vZr0uqC-nBXk2H2Dv6zRM5mRtxo");
        YelpAPI yelpAPI = apiFactory.createAPI();
        Callback<SearchResponse> callback = new Callback<SearchResponse>(){
          @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response){
              notes.add(new Note("Burger King", "Address: \nPhone number:", Note.Category.PERSONAL));
              Log.i("test", response.body().businesses().get(0).name().toString());
              SearchResponse searchResponse = response.body();
              notes.add(new Note(response.body().businesses().get(0).name().toString(), "Address: \nPhone number:", Note.Category.PERSONAL));
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
        params.put("term","food");
        Call<SearchResponse> call = yelpAPI.search("Ellicott City", params);
        call.enqueue(callback);

        /*
        notes.add(new Note("Burger King", "Address: \nPhone number:", Note.Category.PERSONAL));
        notes.add(new Note("Chik-Fil-A", "Address: \n Phone number:", Note.Category.PERSONAL));
        notes.add(new Note("Subway", "Address: \n Phone number:", Note.Category.PERSONAL));
        notes.add(new Note("Pizza Hut", "Address: \n Phone number:", Note.Category.PERSONAL));
*/


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
        intent.putExtra(MainActivity.RESTAURANT_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.RESTAURANT_ID_EXTRA, note.getId());

        startActivity(intent);
    }
}
