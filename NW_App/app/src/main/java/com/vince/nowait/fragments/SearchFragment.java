package com.vince.nowait.fragments;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

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

        noteAdapter = new NoteAdapter(getActivity(), notes);
        setListAdapter(noteAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
    }
}
