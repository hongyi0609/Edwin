package com.bignerdranch.android.crimeintent

import android.content.Context
import java.util.*

/**
 * Created by hongy_000 on 2017/9/24.
 */
class CrimeLab private constructor(context: Context) {

    var crimes: MutableList<Crime> = ArrayList()

    init {
        for (i in 0..99) {
            val crime = Crime()
            crime.title = "Crime # " + i
            crime.mSolved = i % 2 == 0
            crimes.add(crime)
        }
    }

    fun getCrime(uuid: UUID): Crime? {
        // 返回第一个mId == uuid 的Crime，否则返回空
        return crimes.firstOrNull { it.mId == uuid }
    }

    companion object {
        private var sCrimeLab: CrimeLab? = null

        operator fun get(context: Context): CrimeLab {
            if (sCrimeLab == null) {
                sCrimeLab = CrimeLab(context)
            }
            return sCrimeLab as CrimeLab
        }
    }

}
