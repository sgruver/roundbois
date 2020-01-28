package com.example.roundbois

import Setup
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SetupDiagram(context: Context, attrs: AttributeSet) : View (context, attrs) {
    private var mShowNew : Boolean
    private var mShowCurrent : Boolean
    lateinit var mNewSetup : Setup
    lateinit var mCurrentSetup: Setup

    private val newPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.CYAN
    }

    private val currentPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.MAGENTA
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SetupDiagram,
            0, 0).apply {
            try {
                mShowNew = getBoolean(R.styleable.SetupDiagram_showNew, true)
                mShowCurrent = getBoolean(R.styleable.SetupDiagram_showCurrent, true)
            } finally {
                recycle()
            }
        }
    }

    fun isShowNew(): Boolean {
        return  mShowNew
    }

    fun setShowNew(showNew : Boolean) {
        mShowNew = showNew
        invalidate()
        requestLayout()
    }

    fun isShowCurrent(): Boolean {
        return mShowCurrent
    }

    fun setShowCurrent(showCurrent : Boolean) {
        mShowCurrent = showCurrent
        invalidate()
        requestLayout()
    }

    fun setNewSetup(setup: Setup) {
        mNewSetup = setup
        invalidate()
    }

    fun setCurrentSetup(setup: Setup) {
        mCurrentSetup = setup
        invalidate()
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (mShowNew || mShowCurrent)
            canvas?.apply {
                if (mShowCurrent){
                    var w: Float? = mCurrentSetup.wheel?.width?.toFloat()
                    var d: Float? = mCurrentSetup.wheel?.diameter?.toFloat()

                    if (w != null && d != null) {
                        drawRect(100f,100f,100f+w*10,100f+d*10, currentPaint)
                    }
                }
                if (mShowNew){
                    var w: Float? = mNewSetup.wheel?.width?.toFloat()
                    var d: Float? = mNewSetup.wheel?.diameter?.toFloat()

                    if (w != null && d != null) {
                        drawRect(100f,100f,100f+w*10,100f+d*10,newPaint)
                    }
                }
            }
    }
}