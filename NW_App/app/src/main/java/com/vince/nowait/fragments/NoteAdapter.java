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

    public NoteAdapter(Context context, ArrayList<Note> notes)
    {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Note note = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }

        TextView noteTitle = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
        TextView noteText = (TextView) convertView.findViewById(R.id.listItemNoteBody);
        ImageView noteIcon = (ImageView) convertView.findViewById(R.id.listItemNoteImg);

        noteTitle.setText(note.getTitle());
        noteText.setText(note.getMessage());
        noteIcon.setImageResource(note.getAssociatedDrawable());
        return convertView;
    }
}
