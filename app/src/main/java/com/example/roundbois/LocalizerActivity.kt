package com.example.roundbois

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_localizer.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.nio.file.Files.exists
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.ImageView
import java.io.File
import java.net.URI


class LocalizerActivity: AppCompatActivity() {
    //var picture: String = "no file"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localizer)


        var picture = intent.extras?.get("picture") as? String

        if (picture != null) {
            val myBitmap = BitmapFactory.decodeFile(picture)

            val myImage = findViewById<View>(R.id.my_image) as ImageView

            myImage.setImageBitmap(myBitmap)
        }



    }



}