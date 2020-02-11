package com.example.roundbois

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SetupDiagram(context: Context, attrs: AttributeSet) : View (context, attrs) {
    private var mShowNew : Boolean
    private var mShowCurrent : Boolean
    private var mShowNewTire : Boolean
    private var mShowCurrentTire : Boolean
    private var mShowNewFender : Boolean
    private var mShowCurrentFender : Boolean
    lateinit private var mNew : Setup
    lateinit private var mCurrent: Setup

    // Drawing params
    private var maxWidth : Float = 100f
    private var maxHeight : Float = 400f

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
                mShowNewTire = getBoolean(R.styleable.SetupDiagram_showNewTire, true)
                mShowCurrentTire = getBoolean(R.styleable.SetupDiagram_showCurrentTire, true)
                mShowNewFender = getBoolean(R.styleable.SetupDiagram_showNewFender, true)
                mShowCurrentFender = getBoolean(R.styleable.SetupDiagram_showCurrentFender, true)
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
        mNew = setup
        invalidate()
    }

    fun setCurrentSetup(setup: Setup) {
        mCurrent = setup
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        var xpad = (paddingRight + paddingLeft).toFloat()
        var ypad = (paddingTop + paddingBottom).toFloat()

        maxWidth = w.toFloat() - xpad
        maxHeight = h.toFloat() - ypad
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (mShowNew || mShowCurrent)
            canvas?.apply {
                if (mShowCurrent){
                    val w: Float = mCurrent.wheel.width.toFloat()
                    val d: Float = mCurrent.wheel.diameter.toFloat()

                    drawRect(100f,100f,100f+w*10,100f+d*10, currentPaint)
                }
                if (mShowNew){
                    val w: Float = mNew.wheel.width.toFloat()
                    val d: Float = mNew.wheel.diameter.toFloat()

                    drawRect(100f,100f,100f+w*10,100f+d*10,newPaint)
                }
                if (mShowNewTire) {

                }
                if (mShowCurrentTire) {

                }
            }
    }
}