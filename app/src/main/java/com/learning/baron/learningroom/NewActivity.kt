package com.learning.baron.learningroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_word.*

class NewActivity : AppCompatActivity() {

    companion object {
        val EXTRA_REPLY = "REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        button_save.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_word.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val habit = edit_word.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, habit)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

}