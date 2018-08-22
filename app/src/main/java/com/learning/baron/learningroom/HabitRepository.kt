package com.learning.baron.learningroom

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class HabitRepository(application: Application) {

    private val habitDAO: HabitDAO?
    private val listLiveData: LiveData<List<Habit>>?

    init {
        val habitDatabase = HabitDatabase.getDatabase(application)
        habitDAO = habitDatabase?.habitDAO()
        listLiveData = habitDAO?.getAllHabits()
    }

    fun getAllHabits() : LiveData<List<Habit>>? {
        return listLiveData
    }

    fun insert(habit: Habit?) {
        InsertAsyncTask(habitDAO!!).execute(habit)
    }

    private class InsertAsyncTask internal constructor(private val asyncTaskDAO : HabitDAO)
        : AsyncTask<Habit, Void, Void>() {
        override fun doInBackground(vararg p0: Habit): Void? {
            asyncTaskDAO.insert(p0[0])
            return null
        }
    }

}