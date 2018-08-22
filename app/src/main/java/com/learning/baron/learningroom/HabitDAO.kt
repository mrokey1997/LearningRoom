package com.learning.baron.learningroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HabitDAO {
    @Insert
    fun insert(habit: Habit)

    @Query("DELETE FROM habit")
    fun deleteAll()

    @Query("SELECT * FROM habit ORDER BY habit ASC")
    fun getAllHabits() : LiveData<List<Habit>>
}