package com.learning.baron.learningroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val habitRepository: HabitRepository = HabitRepository(application)
    internal val habits: LiveData<List<Habit>>?

    init {
        habits = habitRepository.getAllHabits()
    }

    fun insert(habit: Habit) {
        habitRepository.insert(habit)
    }
}