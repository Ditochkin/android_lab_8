package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.HolidayItemBinding

class HolidayAdapter : RecyclerView.Adapter<HolidayAdapter.HolidayHolder>()
{
    var holidayList = ArrayList<Holiday>()
    class HolidayHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = HolidayItemBinding.bind(item)

        fun bind(holiday: Holiday) = with(binding)
        {
            imageView2.setImageResource(holiday.id)
            tvTitle.text = holiday.nameCoutry
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holiday_item, parent, false)
        return HolidayHolder(view)
    }

    override fun getItemCount(): Int {
        return holidayList.size
    }

    override fun onBindViewHolder(holder: HolidayHolder, position: Int) {
        holder.bind(holidayList[position])
        holder.binding.imageView2.setOnClickListener {
            MAIN.navController.navigate(R.id.action_mainFragment_to_holidayListFragment)
            k_position = position
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addHoliday(holiday: Holiday)
    {
        println(getItemCount())
        holidayList.add(holiday)
        notifyDataSetChanged()
    }
}