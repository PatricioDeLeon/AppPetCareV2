package com.example.petcareapp.room

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petcareapp.R
import com.example.petcareapp.util.BaseActivity
import com.example.petcareapp.util.EXTRA_IS_NEWLY_CREATED
import com.example.petcareapp.util.EXTRA_ROOM_ID
import com.example.petcareapp.util.requestPermissions

class RoomActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
    }

    fun getRoomId(): String {
        return intent.getStringExtra(EXTRA_ROOM_ID) ?: throw IllegalStateException()
    }

    fun isNewlyCreated(): Boolean {
        return intent.getBooleanExtra(EXTRA_IS_NEWLY_CREATED, false)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.nav_host_room).let {
            it?.childFragmentManager?.fragments?.firstOrNull()
        }

        if (currentFragment is GroupCallFragment) {
            // ignore event
        } else {
            super.onBackPressed()
        }
    }
}