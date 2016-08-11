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
        notes.add(new Note("McDonald's", "Wait time: 5 minutes", Note.Category.PERSONAL));
        notes.add(new Note("Burger King", "Wait time: 10 minutes", Note.Category.PERSONAL));
        notes.add(new Note("Chik-Fil-A", "Wait time: 50 minutes", Note.Category.PERSONAL));
        notes.add(new Note("Subway", "Wait time: 3 minutes", Note.Category.PERSONAL));
        notes.add(new Note("Pizza Hut", "Wait time: 2 minutes", Note.Category.PERSONAL));

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
        Note note = (Note) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
        
        intent.putExtra(MainActivity.RESTAURANT_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.RESTAURANT_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.RESTAURANT_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.RESTAURANT_ID_EXTRA, note.getId());

        startActivity(intent);
    }
}
