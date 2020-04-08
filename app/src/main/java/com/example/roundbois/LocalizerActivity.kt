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
import java.nio.channels.FileChannel.MapMode.READ_ONLY
import android.content.res.AssetFileDescriptor
import android.app.Activity
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import org.tensorflow.lite.Interpreter;
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T









class LocalizerActivity: AppCompatActivity() {
    //var picture: String = "no file"
    var modelFile = "RoundboiModel.lite"
    var tflite: Interpreter? = null
    var inp = arrayOf(floatArrayOf(0f, 0f)) //TODO shape correctly for my model
    var out = arrayOf(floatArrayOf(0f)) //TODO shape correctly for my model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localizer)


        var picture = intent.extras?.get("picture") as? String

        if (picture != null) {
            val myBitmap = BitmapFactory.decodeFile(picture)
            val myImage = findViewById<View>(R.id.my_image) as ImageView
            myImage.setImageBitmap(myBitmap)
        }

        try { //no clue how this works. Something interpreter, load, model
            tflite = Interpreter(loadModelFile(this@LocalizerActivity, modelFile))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        //Run the model here.
        //TODO put image into input shaped correctly
        tflite.run(inp,out); //fix something herer
        //TODO make sure to save output so it can be processed properly.



    }
    @Throws(IOException::class)
    private fun loadModelFile(activity: Activity, MODEL_FILE: String): MappedByteBuffer {
        val fileDescriptor = activity.assets.openFd(MODEL_FILE)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.getChannel()
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
}

