package com.bignerdranch.android.crimeintent

import java.util.*

/**
 * Created by hongy_000 on 2017/9/23.
 */
class Crime {

    var mId: UUID? = null
    var title: String? = null
    var date :Date? = null
    var mSolved: Boolean? = false

    init {
        // Generate unique identifier
        mId = UUID.randomUUID()
        date = Date()
    }

}