package com.attilapalfi.cassiopeiaekontroller

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_controller.*


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ControllerActivity : AppCompatActivity() {
    private val hideHandler = Handler()
    private lateinit var myContentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controller)
        myContentView = fullscreen_content
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        hide()
    }

    private fun hide() {
        supportActionBar?.hide()
        hideHandler.postDelayed({
            myContentView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LOW_PROFILE or
                            View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }, 1)
    }
}
