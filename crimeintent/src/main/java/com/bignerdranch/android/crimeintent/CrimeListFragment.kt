package com.bignerdranch.android.crimeintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

/**
 * Created by hongy_000 on 2017/9/24.
 */
class CrimeListFragment : Fragment() {

    private lateinit var mCrimeRecyclerView : RecyclerView
    private lateinit var mAdapter :CrimeAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater?.inflate(R.layout.fragment_crime_list, container, false)!!

        mCrimeRecyclerView = v.findViewById(R.id.crime_recycler_list)
        mCrimeRecyclerView.layoutManager = LinearLayoutManager(activity)

        updateUI()

        return v
    }

    override fun onResume() {
        super.onResume()
        updateUI()
        mAdapter.notifyDataSetChanged()
    }

    private fun updateUI() {
        val crimeLab: CrimeLab = CrimeLab.get(activity)
        val crimes: List<Crime> = crimeLab.crimes

        mAdapter = CrimeAdapter(crimes, activity)
        mCrimeRecyclerView.adapter = mAdapter
    }

    private class CrimeAdapter(var crimes: List<Crime>, var context: Context) : RecyclerView.Adapter<CrimeHolder>() {

        private lateinit var crime: Crime

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CrimeHolder {
            val layoutInflater :LayoutInflater = LayoutInflater.from(context)
            val v: View = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            itemViewInit(v, context)
            return CrimeHolder(v)
        }

        override fun onBindViewHolder(holder: CrimeHolder?, position: Int) {
            crime = crimes[position]

            holder?.bindCrime(crime)
        }

        override fun getItemCount(): Int {
            return crimes.size
        }

        // itemView 初始化，监听布局点击
        fun itemViewInit(itemView :View, context: Context) {
            itemView.setOnClickListener {
                //                    Toast.makeText(context, titleTextView.text.toString() + " clicked!", Toast.LENGTH_LONG).show()
                val i: Intent = CrimeActivity.newIntent(context, crime.mId)
                context.startActivity(i)
            }
        }
    }

    private class CrimeHolder(itemView :View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.list_item_crime_title_text_view)
        private val dateTextView: TextView = itemView.findViewById(R.id.list_item_crime_date_text_view)
        private val solvedCheckBox: CheckBox = itemView.findViewById(R.id.list_item_crime_solved_check_box)

        fun bindCrime(crime: Crime) {
            titleTextView.text = crime.title
            dateTextView.text = crime.date.toString()
            solvedCheckBox.isChecked = crime.mSolved!!
        }
    }
}