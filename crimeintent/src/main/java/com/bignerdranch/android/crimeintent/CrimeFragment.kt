package com.bignerdranch.android.crimeintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

/**
 * Created by hongy_000 on 2017/9/23.
 */
class CrimeFragment : Fragment() {

    private lateinit var mCrime :Crime
    private lateinit var mTitleField :EditText
    private lateinit var mDateButton :Button
    private lateinit var mSolvedCheckbox :CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCrime = Crime()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v :View = inflater?.inflate(R.layout.fragment_crime, container, false)!!

        mTitleField = v.findViewById(R.id.crime_title) as EditText
        mTitleField.addTextChangedListener(
                object : TextWatcher{
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        mCrime.title = s.toString()
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                }
        )

        mDateButton = v.findViewById(R.id.crime_date) as Button
        mDateButton.text = mCrime.mDate.toString()
        mDateButton.isEnabled = false

        mSolvedCheckbox = v.findViewById(R.id.crime_solved) as CheckBox
        mSolvedCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            // Set the crime's solved property
            mCrime.mSolved = isChecked
        }


        return v
    }
}