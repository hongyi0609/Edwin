package com.bignerdranch.android.crimeintent;

import java.util.UUID;

/**
 * Created by hongy_000 on 2017/9/23.
 */

public class Crime {
    private final UUID mId;

    public UUID getID() {
        return mID;
    }

    public void setID(UUID ID) {
        mID = ID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    private UUID mID;
    private String mTitle;

    public Crime() {
        // Generate unique identifier
        mId = UUID.randomUUID();
    }

}
