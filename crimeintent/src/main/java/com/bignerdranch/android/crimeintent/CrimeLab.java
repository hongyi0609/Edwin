/*
package com.bignerdranch.android.crimeintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

*/
/**
 * Created by hongy_000 on 2017/9/24.
 *//*


public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> crimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        crimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime # " + i);
            crime.setMSolved(i % 2 == 0);
            crimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public Crime getCrime(UUID uuid) {
        for (Crime crime : crimes) {
            if (crime.getMId().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }

}
*/
