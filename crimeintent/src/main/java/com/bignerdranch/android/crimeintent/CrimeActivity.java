package com.bignerdranch.android.crimeintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.crimeintent.crime_id";
    @NotNull
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
}
