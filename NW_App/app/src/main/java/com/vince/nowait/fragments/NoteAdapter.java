package com.vince.nowait.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vince.nowait.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
        Picasso.with(getContext()).load(note.getImageurl()).into(viewHolder.noteIcon);
/*
        try {
            URL newurl = new URL(note.getImageurl());
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            viewHolder.noteIcon.setImageBitmap(mIcon_val);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        */
        return convertView;
    }
}
