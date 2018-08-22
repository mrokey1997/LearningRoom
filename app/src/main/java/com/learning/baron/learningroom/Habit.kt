package com.learning.baron.learningroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
        @PrimaryKey
        @ColumnInfo(name = "habit")
        val habit: String
)