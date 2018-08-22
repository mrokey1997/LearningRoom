package com.learning.baron.learningroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    private var habitViewModel: HabitViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        habitViewModel = ViewModelProviders.of(this).get(HabitViewModel::class.java)

        val adapter = HabitAdapter(this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        habitViewModel?.habits?.observe(
                this,
                Observer<List<Habit>> {
                    adapter.setWords(it)
                })

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val habit = Habit(data!!.getStringExtra(NewActivity.EXTRA_REPLY))
            habitViewModel?.insert(habit)
        } else {
            Toast.makeText(
                    applicationContext,
                    "Word not saved because it is empty",
                    Toast.LENGTH_LONG).show()
        }
    }
}
