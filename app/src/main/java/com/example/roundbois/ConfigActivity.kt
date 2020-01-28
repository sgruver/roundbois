package com.example.roundbois

import Fender
import Setup
import Tire
import Wheel
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_config.*
import java.io.Serializable

const val CONFIG_NEW_REQUEST = 1
const val CONFIG_CURRENT_REQUEST = 2

class ConfigActivity : AppCompatActivity() {

    //  TODO("note") // Consider setting these to lateinit instead of null
    var newSetup = Setup(Wheel(), Tire(), Fender())
    var currentSetup = Setup(Wheel(), Tire(), Fender())
    lateinit var sd : SetupDiagram

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        sd = findViewById<SetupDiagram>(R.id.compareDiagram)
        sd.mNewSetup = newSetup
        sd.mCurrentSetup = currentSetup

        configNewButton.setOnClickListener {
            val configNewIntent = Intent(this, NewConfigActivity::class.java)
            configNewIntent.putExtra("EXTRA_NEWSETUP", newSetup as Serializable)
            startActivityForResult(configNewIntent,CONFIG_NEW_REQUEST)
        }

        selectNewButton.setOnClickListener {

        }

        configCurrentButton.setOnClickListener {

        }

        selectCurrentButton.setOnClickListener {

        }

        compareButton.setOnClickListener {
            val compareIntent = Intent(this, CompareActivity::class.java)
            compareIntent.putExtra("EXTRA_NEWSETUP", newSetup as Serializable)
            compareIntent.putExtra("EXTRA_CURRENTSETUP", currentSetup as Serializable)
            startActivity(compareIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            CONFIG_NEW_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    newSetup = data?.extras?.get("EXTRA_NEWSETUP") as Setup
                    sd.mNewSetup = newSetup
                }
            }
            CONFIG_CURRENT_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) currentSetup = data?.extras?.get("EXTRA_CURRENTSETUP") as Setup
            }
        }
    }
}
