package com.example.roundbois

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_config.*
import java.io.Serializable

const val CONFIG_NEW_REQUEST = 1
const val SELECT_NEW_REQUEST = 2
const val CONFIG_CURRENT_REQUEST = 3

class ConfigActivity : AppCompatActivity() {

    //  TODO("note") // Consider setting these to lateinit instead of null
    var newSetup = Setup(Wheel(), Tire(), Fender())
    var currentSetup = Setup(Wheel(), Tire(), Fender())
    lateinit var sd : SetupDiagram

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        sd = findViewById<SetupDiagram>(R.id.compareDiagram)
        sd.setNewSetup(newSetup)
        sd.setCurrentSetup(currentSetup)

        newWheelSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sd.setShowNew(true)
            } else {
                sd.setShowNew(false)
            }
        }

        currentWheelSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                sd.setShowCurrent(true)
            } else {
                sd.setShowCurrent(false)
            }
        }

        newTireSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sd.setShowNewTire(true)
            } else {
                sd.setShowNewTire(false)
            }
        }

        configNewButton.setOnClickListener {
            val configNewIntent = Intent(this, NewConfigActivity::class.java)
            configNewIntent.putExtra("EXTRA_NEWSETUP", newSetup as Serializable)
            startActivityForResult(configNewIntent,CONFIG_NEW_REQUEST)
        }

        selectNewButton.setOnClickListener {

        }

        configCurrentButton.setOnClickListener {
            val configCurrentIntent = Intent(this, CurrentConfigActivity::class.java)
            configCurrentIntent.putExtra("EXTRA_CURRENTSETUP", currentSetup as Serializable)
            startActivityForResult(configCurrentIntent, CONFIG_CURRENT_REQUEST)
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
                    sd.setNewSetup(newSetup)
                }
            }
            CONFIG_CURRENT_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    currentSetup = data?.extras?.get("EXTRA_CURRENTSETUP") as Setup
                    sd.setCurrentSetup(currentSetup)
                }
            }
        }
    }
}
