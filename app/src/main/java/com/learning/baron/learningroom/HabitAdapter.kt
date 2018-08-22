package com.learning.baron.learningroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter internal constructor(context: Context) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var habitsList: List<Habit>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return HabitViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        if (habitsList != null) {
            val (habit) = habitsList!![position]
            holder.habitItemView.text = habit
        } else {
            holder.habitItemView.text = "No Word"
        }
    }

    override fun getItemCount(): Int {
        return if (habitsList != null)
            habitsList!!.size
        else
            0
    }

    internal fun setWords(habitsList: List<Habit>?) {
        this.habitsList = habitsList
        notifyDataSetChanged()
    }

    inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val habitItemView: TextView = itemView.findViewById(R.id.textView)

    }
}