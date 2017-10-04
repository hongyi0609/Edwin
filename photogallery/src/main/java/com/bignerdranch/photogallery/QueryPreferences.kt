package com.bignerdranch.photogallery

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by hongy_000 on 2017/10/4.
 */
class QueryPreferences {

    companion object {
        private val PREF_SEARCH_QUERY: String = "searchQuery"

        fun getStoredQuery(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(PREF_SEARCH_QUERY, "")
        }

        fun setStoredQuery(context: Context, query: String) {
            PreferenceManager.getDefaultSharedPreferences(context)
                    .edit()
                    .putString(PREF_SEARCH_QUERY, query)
                    .apply()
        }
    }
}