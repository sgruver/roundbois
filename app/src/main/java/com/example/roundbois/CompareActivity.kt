package com.example.roundbois

import CompareSetup
import Setup
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_compare.*

class CompareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare)

        val newSetup = intent.extras?.get("EXTRA_NEWSETUP") as Setup
        val currentSetup = intent.extras?.get("EXTRA_CURRENTSETUP") as Setup

        newDiameterTextView.text = newSetup.wheel?.diameter.toString()
        newWidthTextView.text = newSetup.wheel?.width.toString()
        newOffsetTextView.text = newSetup.wheel?.offset.toString()
        newWeightTextView.text = newSetup.wheel?.weight.toString()
        newCamberTextView.text = newSetup.fender?.camber.toString()

        currentDiameterTextView.text = currentSetup.wheel?.diameter.toString()
        currentWidthTextView.text = currentSetup.wheel?.width.toString()
        currentOffsetTextView.text = currentSetup.wheel?.offset.toString()
        currentWeightTextView.text = currentSetup.wheel?.weight.toString()
        currentCamberTextView.text = currentSetup.fender?.camber.toString()
    }
}
