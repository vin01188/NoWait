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

        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
            viewHolder.note = (TextView) convertView.findViewById(R.id.listItemNoteBody);
            viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.listItemNoteImg);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        viewHolder.title.setText(note.getTitle());
        viewHolder.note.setText(note.getMessage());
        viewHolder.noteIcon.setImageResource(note.getAssociatedDrawable());
        return convertView;
    }
}
