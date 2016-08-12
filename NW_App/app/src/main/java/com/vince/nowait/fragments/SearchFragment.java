package com.vince.nowait.fragments;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;

import com.vince.nowait.MainActivity;
import com.vince.nowait.RestaurantDetailActivity;
import com.vince.nowait.fragments.Note;
import com.vince.nowait.fragments.NoteAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends ListFragment
{

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;

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

        notes = new ArrayList<Note>();
        notes.add(new Note("McDonald's", "Address: \nPhone number:", Note.Category.PERSONAL));
        notes.add(new Note("Burger King", "Address: \nPhone number:", Note.Category.PERSONAL));
        notes.add(new Note("Chik-Fil-A", "Address: \n Phone number:", Note.Category.PERSONAL));
        notes.add(new Note("Subway", "Address: \n Phone number:", Note.Category.PERSONAL));
        notes.add(new Note("Pizza Hut", "Address: \n Phone number:", Note.Category.PERSONAL));

        noteAdapter = new NoteAdapter(getActivity(), notes);
        setListAdapter(noteAdapter);

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
