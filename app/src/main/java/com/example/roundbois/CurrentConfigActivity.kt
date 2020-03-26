package com.example.roundbois

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_current_config.*
import java.io.Serializable

class CurrentConfigActivity : AppCompatActivity(), TextWatcher{
    lateinit var setup: Setup
    lateinit var sd: SetupDiagram

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_config)

        setup = intent.extras?.get("EXTRA_CURRENTSETUP") as Setup

        sd = findViewById<SetupDiagram>(R.id.curDiagram)
        sd.setCurrentSetup(setup)

        // TODO ("note") // WHEEL INIT
        diameterEditText.setText(setup.wheel.diameter.toString())
        widthEditText.setText(setup.wheel.width.toString())
        offsetEditText.setText(setup.wheel.offset.toString())
        boreEditText.setText(setup.wheel.bore.toString())
        insetEditText.setText(setup.wheel.inset.toString())
        outsetEditText.setText(setup.wheel.outset.toString())
        boltEditText.setText(setup.wheel.boltPattern.toString())
        brakeEditText.setText(setup.wheel.maxBrake.toString())
        weightEditText.setText(setup.wheel.weight.toString())

        diameterEditText.addTextChangedListener(this)
        widthEditText.addTextChangedListener(this)
        offsetEditText.addTextChangedListener(this)
        boreEditText.addTextChangedListener(this)
        boltEditText.addTextChangedListener(this)
        brakeEditText.addTextChangedListener(this)
        weightEditText.addTextChangedListener(this)


        // TODO("note") // TIRE INIT
        tireWidthEditText.setText(setup.tire.width.toString())
        aspectEditText.setText(setup.tire.width.toString())
        stretchEditText.setText(setup.tire.stretch.toString())
        sidewallEditText.setText(setup.tire.sidewall.toString())
        circumferenceEditText.setText(setup.tire.circumference.toString())
        revsEditText.setText(setup.tire.revMile.toString())
        widthRangeEditText.setText(setup.tire.widthRange.toString())
        loadEditText.setText(setup.tire.load.toString())

        tireWidthEditText.addTextChangedListener(this)
        aspectEditText.addTextChangedListener(this)
        widthRangeEditText.addTextChangedListener(this)
        loadEditText.addTextChangedListener(this)


        // TODO("note") // FENDER INIT
        pullEditText.setText(setup.fender.pull.toString())
        fenderHeightEditText.setText(setup.fender.height.toString())
        fenderWidthEditText.setText(setup.fender.width.toString())
        //fenderOffsetEditText.setText(setup.fender?.pull.toString())
        tuckEditText.setText(setup.fender.tuck.toString())
        camberEditText.setText(setup.fender.camber.toString())
        fitmentEditText.setText(setup.fender.fitment.toString())

        pullEditText.addTextChangedListener(this)
        fenderHeightEditText.addTextChangedListener(this)
        fenderWidthEditText.addTextChangedListener(this)
        camberEditText.addTextChangedListener(this)


        collapseButton.setOnClickListener {
            if(boreTR.isVisible){
                boreTR.visibility = View.GONE
                insetTR.visibility = View.GONE
                outsetTR.visibility = View.GONE
                boltPatternTR.visibility = View.GONE
            } else {
                boreTR.visibility = View.VISIBLE
                insetTR.visibility = View.VISIBLE
                outsetTR.visibility = View.VISIBLE
                boltPatternTR.visibility = View.VISIBLE
            }
        }

        saveNewButton.setOnClickListener {
            val saveIntent = Intent().apply{
                putExtra("EXTRA_CURRENTSETUP", setup as Serializable)
            }

            setResult(Activity.RESULT_OK, saveIntent)
            finish()
        }
    }

    override fun afterTextChanged(s: Editable?) {
        var t = s.toString().replace(Regex("(^[\\d|-]\\d*\\.{0,1}\\d{0,2})"),"$1")
        if (t == "" || t == "-") t = "0"

        when (currentFocus) {
            diameterEditText -> {
                setup.wheel.diameter = t.toInt()
            }
            widthEditText -> {
                setup.wheel.width = t.toDouble()
            }
            offsetEditText -> {
                setup.wheel.offset = t.toInt()
            }
            boreEditText -> {
                setup.wheel.bore = t.toDouble()
            }
            boltEditText -> {
                setup.wheel.boltPattern = t
            }
            brakeEditText -> {
                setup.wheel.weight = t.toDouble()
            }
            tireWidthEditText -> {
                setup.tire.width = t.toInt()
            }
            aspectEditText -> {
                setup.tire.aspect = t.toInt()
            }
            widthRangeEditText -> {
                setup.tire.widthRange = t
            }
            loadEditText -> {
                setup.tire.load = t.toInt()
            }
            pullEditText -> {
                setup.fender.pull = t.toDouble()
            }
            fenderHeightEditText -> {
                setup.fender.height = t.toDouble()
            }
            fenderWidthEditText -> {
                setup.fender.width = t.toDouble()
            }
            camberEditText -> {
                setup.fender.camber = t.toDouble()
            }
        }

        sd.setCurrentSetup(setup)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
