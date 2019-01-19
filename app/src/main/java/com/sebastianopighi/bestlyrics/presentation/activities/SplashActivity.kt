package com.sebastianopighi.bestlyrics.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sebastianopighi.bestlyrics.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val tracksIntent = Intent(this, TracksActivity::class.java)
        startActivity(tracksIntent)
        finish()
    }
}
