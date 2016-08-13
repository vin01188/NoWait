package com.vince.nowait.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vince.nowait.R;

import java.util.ArrayList;

/**
 * Created by Eric on 8/11/2016.
 */

public class NoteAdapter extends ArrayAdapter<Note>
{

    /*
     * Used to store Text and Image views in order to optimize these functions by
     * reducing the amount of times that findViewById() is called.
     */
    public static class ViewHolder
    {
        TextView title;
        TextView note;
        ImageView noteIcon;
    }

    public NoteAdapter(Context context, ArrayList<Note> notes)
    {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Note note = getItem(position);

        ViewHolder viewHolder;

        //check if an existing View is being reused, otherwise inflate a new view from custom row layout
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            //recreate the View
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            //grab references of the views to populate the specific row's data
            viewHolder.title = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
            viewHolder.note = (TextView) convertView.findViewById(R.id.listItemNoteBody);
            viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.listItemNoteImg);

            //remember the viewHolder which holds the references
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //fill each referenced view with data associated with the note it's referencing
        viewHolder.title.setText(note.getTitle());
        viewHolder.note.setText(note.getMessage());
        // change this
        viewHolder.noteIcon.setImageResource(note.getAssociatedDrawable());
        return convertView;
    }
}
