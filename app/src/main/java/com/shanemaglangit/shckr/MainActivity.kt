package com.shanemaglangit.shckr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shanemaglangit.actions.Actions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Actions.openDetectorIntent(this))
    }
}
