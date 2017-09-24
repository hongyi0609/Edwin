package com.bignerdranch.android.crimeintent

import android.support.v4.app.Fragment

/**
 * Created by hongy_000 on 2017/9/24.
 */
class CrimeListActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return CrimeListFragment()
    }
}