package com.abc.demo.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import com.abc.demo.R
import com.abc.demo.listener.aItemView
import tyrantgit.explosionfield.ExplosionField

class aaLuckyMonkeyPanelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var bg_1: ImageView? = null
    private var bg_2: ImageView? = null
    private var itemView1: aaPanelAItemView? = null
    private var itemView2: aaPanelAItemView? = null
    private var itemView3: aaPanelAItemView? = null
    private var itemView4: aaPanelAItemView? = null
    private var itemView6: aaPanelAItemView? = null
    private var itemView7: aaPanelAItemView? = null
    private var itemView8: aaPanelAItemView? = null
    private var itemView9: aaPanelAItemView? = null
    private var img1: LinearLayout? = null
    private var img2: LinearLayout? = null
    private var img3: LinearLayout? = null
    private var img4: LinearLayout? = null
    private var img6: LinearLayout? = null
    private var img7: LinearLayout? = null
    private var img8: LinearLayout? = null
    private var img9: LinearLayout? = null
    private var floating1: ImageView? = null
    private var floating2: ImageView? = null
    private var floating3: ImageView? = null
    private var floating4: ImageView? = null
    private var floating6: ImageView? = null
    private var floating7: ImageView? = null
    private var floating8: ImageView? = null
    private var floating9: ImageView? = null
    private val aItemViewArr = arrayOfNulls<aItemView>(8)
    private var currentIndex = 0
    private var currentTotal = 0
    private var stayIndex = 0
    private var isMarqueeRunning = false
    var isGameRunning = false
        private set
    private var isTryToStop = false
    private var currentSpeed = DEFAULT_SPEED
    private var mExplosionField: ExplosionField

    init {
        inflate(context, R.layout.view_lucky_mokey_panel, this)
        mExplosionField = ExplosionField.attach2Window(context as Activity)
        setupView()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startMarquee()
    }

    override fun onDetachedFromWindow() {
        stopMarquee()
        super.onDetachedFromWindow()
    }

    private fun setupView() {
        bg_1 = findViewById(R.id.bg_1)
        bg_2 = findViewById(R.id.bg_2)
        itemView1 = findViewById(R.id.item1)
        itemView2 = findViewById(R.id.item2)
        itemView3 = findViewById(R.id.item3)
        itemView4 = findViewById(R.id.item4)
        itemView6 = findViewById(R.id.item6)
        itemView7 = findViewById(R.id.item7)
        itemView8 = findViewById(R.id.item8)
        itemView9 = findViewById(R.id.item9)
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)
        img6 = findViewById(R.id.img6)
        img7 = findViewById(R.id.img7)
        img8 = findViewById(R.id.img8)
        img9 = findViewById(R.id.img9)
        floating1 = findViewById(R.id.floating1)
        floating2 = findViewById(R.id.floating2)
        floating3 = findViewById(R.id.floating3)
        floating4 = findViewById(R.id.floating4)
        floating6 = findViewById(R.id.floating6)
        floating7 = findViewById(R.id.floating7)
        floating8 = findViewById(R.id.floating8)
        floating9 = findViewById(R.id.floating9)
        aItemViewArr[0] = itemView4
        aItemViewArr[1] = itemView1
        aItemViewArr[2] = itemView2
        aItemViewArr[3] = itemView3
        aItemViewArr[4] = itemView6
        aItemViewArr[5] = itemView9
        aItemViewArr[6] = itemView8
        aItemViewArr[7] = itemView7

        floating1!!.setOnClickListener { _: View? -> mExplosionField.explode(floating1) }
        floating2!!.setOnClickListener { _: View? -> mExplosionField.explode(floating2) }
        floating3!!.setOnClickListener { _: View? -> mExplosionField.explode(floating3) }
        floating4!!.setOnClickListener { _: View? -> mExplosionField.explode(floating4) }
        floating6!!.setOnClickListener { _: View? -> mExplosionField.explode(floating6) }
        floating7!!.setOnClickListener { _: View? -> mExplosionField.explode(floating7) }
        floating8!!.setOnClickListener { _: View? -> mExplosionField.explode(floating8) }
        floating9!!.setOnClickListener { _: View? -> mExplosionField.explode(floating9) }
    }

    private fun stopMarquee() {
        isMarqueeRunning = false
        isGameRunning = false
        isTryToStop = false
    }

    private fun startMarquee() {
        isMarqueeRunning = true
        Thread {
            while (isMarqueeRunning) {
                try {
                    Thread.sleep(250)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                post {
                    if (bg_1 != null && bg_2 != null) {
                        if (VISIBLE == bg_1!!.visibility) {
                            bg_1!!.visibility = GONE
                            bg_2!!.visibility = VISIBLE
                        } else {
                            bg_1!!.visibility = VISIBLE
                            bg_2!!.visibility = GONE
                        }
                    }
                }
            }
        }.start()
    }

    private val interruptTime: Long
        get() {
            currentTotal++
            if (isTryToStop) {
                currentSpeed += 10
                if (currentSpeed > DEFAULT_SPEED) {
                    currentSpeed = DEFAULT_SPEED
                }
            } else {
                if (currentTotal / aItemViewArr.size > 0) {
                    currentSpeed -= 10
                }
                if (currentSpeed < MIN_SPEED) {
                    currentSpeed = MIN_SPEED
                }
            }
            return currentSpeed.toLong()
        }

    private fun hideprize() {
        img1!!.visibility = GONE
        img2!!.visibility = GONE
        img3!!.visibility = GONE
        img4!!.visibility = GONE
        img6!!.visibility = GONE
        img7!!.visibility = GONE
        img8!!.visibility = GONE
        img9!!.visibility = GONE
        floating1!!.visibility = GONE
        floating2!!.visibility = GONE
        floating3!!.visibility = GONE
        floating4!!.visibility = GONE
        floating6!!.visibility = GONE
        floating7!!.visibility = GONE
        floating8!!.visibility = GONE
        floating9!!.visibility = GONE
    }

    fun startGame() {
        hideprize()
        isGameRunning = true
        isTryToStop = false
        currentSpeed = DEFAULT_SPEED
        Thread {
            while (isGameRunning) {
                try {
                    Thread.sleep(interruptTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                post {
                    val preIndex = currentIndex
                    currentIndex++
                    if (currentIndex >= aItemViewArr.size) {
                        currentIndex = 0
                    }
                    aItemViewArr[preIndex]!!.setFocus(false)
                    aItemViewArr[currentIndex]!!.setFocus(true)
                    if (isTryToStop && currentSpeed == DEFAULT_SPEED && stayIndex == currentIndex) {
                        Log.e("pos", "$currentIndex  pooo")
                        isGameRunning = false
                    }
                }
            }
        }.start()
    }

    fun tryToStop(position: Int) {
        stayIndex = position
        isTryToStop = true
        Log.e("pos", "$position  pooo")
        val handler = Handler()
        handler.postDelayed({
            when (position) {
                0 -> {
                    img1!!.visibility = VISIBLE
                    floating1!!.visibility = VISIBLE
                }
                1 -> {
                    img2!!.visibility = VISIBLE
                    floating2!!.visibility = VISIBLE
                }
                2 -> {
                    img3!!.visibility = VISIBLE
                    floating3!!.visibility = VISIBLE
                }
                3 -> {
                    img4!!.visibility = VISIBLE
                    floating4!!.visibility = VISIBLE
                }
                4 -> {
                    img9!!.visibility = VISIBLE
                    floating9!!.visibility = VISIBLE
                }
                5 -> {
                    img7!!.visibility = VISIBLE
                    floating7!!.visibility = VISIBLE
                }
                6 -> {
                    img7!!.visibility = VISIBLE
                    floating7!!.visibility = VISIBLE
                }
                7 -> {
                    img4!!.visibility = VISIBLE
                    floating4!!.visibility = VISIBLE
                }
            }
        }, 2000)
    }

    companion object {
        private const val DEFAULT_SPEED = 150
        private const val MIN_SPEED = 50
    }
}