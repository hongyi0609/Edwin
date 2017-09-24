package com.bignerdranch.android.crimeintent;

import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class CrimeActivity extends SingleFragmentActivity {

    @NotNull
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
