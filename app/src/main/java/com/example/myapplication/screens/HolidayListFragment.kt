package com.example.myapplication.screens

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import com.example.myapplication.MAIN
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHolidayListBinding
import com.example.myapplication.k_position
import com.example.retrofit.HolidayContry

class HolidayListFragment : Fragment() {

    lateinit var binding: FragmentHolidayListBinding
    val mapCoutryes = mutableMapOf<String, HolidayContry>()

    private lateinit var listViewYesterday: ListView
    private lateinit var listViewToday: ListView
    private lateinit var listViewTomorrow: ListView

    private lateinit var adapterYesterday: ArrayAdapter<String>
    private lateinit var adapterToday: ArrayAdapter<String>
    private lateinit var adapterTomorrow: ArrayAdapter<String>

    private val itemListYesterday: ArrayList<String> = ArrayList()
    private val itemListToday: ArrayList<String> = ArrayList()
    private val itemListTomorrow: ArrayList<String> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHolidayListBinding.inflate(layoutInflater, container, false)

        binding.btnBack.setOnClickListener{
            MAIN.navController.navigate(R.id.action_holidayListFragment_to_mainFragment)
        }

        init()

        return binding.root
    }

    private fun init()
    {
        binding.tvContry.setText(MAIN.contryList[k_position])

        var holiday = MAIN.mapCoutryes[MAIN.contryKeys[k_position]]

        listViewYesterday = binding.listYesterday
        adapterYesterday = ArrayAdapter(MAIN, MAIN.listItem, itemListYesterday)
        listViewYesterday.adapter = adapterYesterday

        if (holiday != null) {
            itemListYesterday.add(holiday.yesterday.replace(", ", "\n").replace("n/a", "Yesterday there was no name day"))
        }
        else
        {
            itemListYesterday.add("NO NAME")
        }

        adapterYesterday.notifyDataSetChanged()

        listViewToday = binding.listToday
        adapterToday = ArrayAdapter(MAIN, MAIN.listItem, itemListToday)
        listViewToday.adapter = adapterToday

        if (holiday != null) {
            itemListToday.add(holiday.today.replace(", ", "\n").replace("n/a", "Today is no name day"))
        }
        else
        {
            itemListToday.add("NO NAME")
        }

        adapterToday.notifyDataSetChanged()

        listViewTomorrow = binding.listTomorrow
        adapterTomorrow = ArrayAdapter(MAIN, MAIN.listItem, itemListTomorrow)
        listViewTomorrow.adapter = adapterTomorrow

        if (holiday != null) {
            itemListTomorrow.add(holiday.tomorrow.replace(", ", "\n").replace("n/a", "Tomorrow will not be a name day"))
        }
        else
        {
            itemListTomorrow.add("NO NAME")
        }

        adapterTomorrow.notifyDataSetChanged()
    }

}