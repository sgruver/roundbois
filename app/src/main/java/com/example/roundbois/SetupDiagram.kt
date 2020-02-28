package com.example.roundbois

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.max

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
    private var sf: Float = 0f
    private var xC: Float = 0f
    private var yC: Float = 0f
    private var maxWidth : Float = 100f
    private var maxHeight : Float = 400f


    private val newPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.CYAN
        style = Paint.Style.STROKE
        strokeWidth = 10F
    }

    private val currentPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.YELLOW
        style = Paint.Style.STROKE
        strokeWidth = 10F
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

        val xpad = (paddingRight + paddingLeft).toFloat()
        val ypad = (paddingTop + paddingBottom).toFloat()

        maxWidth = w.toFloat() - xpad
        maxHeight = h.toFloat() - ypad

        xC = maxWidth/2 + paddingLeft
        yC = maxHeight/2 + paddingTop
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!::mCurrent.isInitialized) sf = maxHeight / mNew.totalHeight.toFloat()
        else if (!::mNew.isInitialized) sf = maxHeight / mCurrent.totalHeight.toFloat()
        else sf = maxHeight / max(mNew.totalHeight,mCurrent.totalHeight).toFloat()

        if (mShowNew || mShowCurrent) canvas?.apply {
            if (mShowCurrent){
                val w: Float = ((mCurrent.wheel.width * sf) / 2).toFloat()
                val d: Float = ((mCurrent.wheel.diameter * sf) / 2)
                val o: Float = toIN(mCurrent.wheel.offset.toFloat()) * sf

                canvas.save()
                canvas.rotate(mCurrent.fender.camber.toFloat(),xC,yC)
                drawRect(xC - w + o, yC - d, xC + w + o, yC + d, currentPaint)
                canvas.restore()
            }
            if (mShowCurrentTire) {

            }



            if (mShowNew){
                val w: Float = ((mNew.wheel.width * sf) / 2).toFloat()
                val d: Float = ((mNew.wheel.diameter * sf) / 2)
                val o: Float = toIN(mNew.wheel.offset.toFloat()) * sf

                canvas.save()
                canvas.rotate(mNew.fender.camber.toFloat(),xC,yC)
                drawRect(xC - w + o, yC - d, xC + w + o, yC + d,newPaint)
                canvas.restore()
            }
            if (mShowNewTire) {

            }
        }
    }

    fun toMM(x: Double): Double = x * 25.4
    fun toMM(x: Float): Float = x * 25.4f
    fun toIN(x: Double): Double = x * 0.0393701
    private fun toIN(x: Float): Float = x * 0.0393701f
}