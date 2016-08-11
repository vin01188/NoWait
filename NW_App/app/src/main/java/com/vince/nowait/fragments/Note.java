package com.vince.nowait.fragments;


import com.vince.nowait.R;

/**
 * Created by Eric on 8/11/2016.
 */

public class Note {
    private String title, message;
    private long noteId, dateCreatedMilli;
    private Category category;

    public enum Category{ PERSONAL, TECHNICAL, QUOTE, FINANCE }

    public Note(String title, String message, Category category)
    {
        this.title = title;
        this.message = message;
        this.category = category;
        this.noteId = 0;
        this.dateCreatedMilli = 0;
    }

    public Note(String title, String message, Category category, long noteId, long dateCreatedMilli)
    {
        this.title = title;
        this.message = message;
        this.category = category;
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

    public Category getCategory()
    {
        return category;
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
                + category.name() + " Date: " + dateCreatedMilli;
    }

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
}
