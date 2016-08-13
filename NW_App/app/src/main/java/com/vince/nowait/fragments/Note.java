package com.vince.nowait.fragments;


import com.vince.nowait.R;

/**
 * Created by Eric on 8/11/2016.
 */

public class Note {
    private String title, message;
    private long noteId, dateCreatedMilli;
    private String imageurl;


    public Note(String title, String message, String imageurl)
    {
        this.title = title;
        this.message = message;
        this.imageurl = imageurl;
        this.noteId = 0;
        this.dateCreatedMilli = 0;
    }

    public Note(String title, String message, String imageUrl, long noteId, long dateCreatedMilli)
    {
        this.title = title;
        this.message = message;
        this.imageurl = imageUrl;
        this.noteId = noteId;
        this.dateCreatedMilli = dateCreatedMilli;
    }

    public String getTitle()
    {
        return title;
    }

    public String getMessage()
    {
        return message;
    }

    public String getImageurl()
    {
        return imageurl;
    }

    public long getDate()
    {
        return dateCreatedMilli;
    }

    public long getId()
    {
        return noteId;
    }

    public String toString() {
        return "ID: " + noteId + " Title: " + title + " Message: " + message + " IconID: "
                + imageurl + " Date: " + dateCreatedMilli;
    }
/*
    public int getAssociatedDrawable()
    {
        return categoryToDrawable(category);
    }

    public static int categoryToDrawable(Category noteCategory)
    {
        switch(noteCategory)
        {
            case PERSONAL:
                return R.drawable.mcdonalds;
            case TECHNICAL:
                return R.drawable.mcdonalds;
            case FINANCE:
                return R.drawable.mcdonalds;
            case QUOTE:
                return R.drawable.mcdonalds;

        }

        return R.drawable.mcdonalds;
    }
    */
}
