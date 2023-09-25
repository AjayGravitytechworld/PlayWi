package com.abc.demo.ui

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.R
import com.abc.demo.database.Database
import com.abc.demo.databinding.ActivitySlotBinding
import com.abc.demo.model.aaIncome
import com.abc.demo.slot.aSlotMachine
import com.abc.demo.slot.AaSymbols
import com.abc.demo.slot.aWheel
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage

class aaSlotActivity : AppCompatActivity() {

    private var slotBinding: ActivitySlotBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null

    private var aSlotMachine: aSlotMachine? = null

    private var aWheel1: aWheel? = null
    private var aWheel2: aWheel? = null
    private var aWheel3: aWheel? = null

    private var animation1: AnimationDrawable? = null
    private var animation2: AnimationDrawable? = null
    private var animation3: AnimationDrawable? = null

    private var winSound: MediaPlayer? = null
    private var rattleSound: MediaPlayer? = null
    private var loseSound: MediaPlayer? = null

    private var db: Database? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        slotBinding = ActivitySlotBinding.inflate(layoutInflater)
        setContentView(slotBinding?.root)

        context = this
        store = Utilll.getInstance(this)
        db = Database(context)

        if(AppManage.soon == 1){
            slotBinding!!.ll1.visibility = View.GONE
            slotBinding!!.ll2.visibility = View.VISIBLE
        }else{
            slotBinding!!.ll2.visibility = View.GONE
            slotBinding!!.ll1.visibility = View.VISIBLE
        }

        slotBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

        slotBinding!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)

//        AppManage.getInstance(this).showNativeBanner(
//            slotBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

        AppManage.getInstance(this).showNativetype(
            slotBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 4
        )

        aSlotMachine = aSlotMachine(3)
        val slots = aSlotMachine!!.slots
        aWheel1 = slots[0]
        aWheel2 = slots[1]
        aWheel3 = slots[2]

        slotBinding!!.animation1.setBackgroundResource(R.drawable.wheel1animation)
        animation1 = slotBinding!!.animation1.background as AnimationDrawable?

        slotBinding!!.animation2.setBackgroundResource(R.drawable.wheel2animation)
        animation2 = slotBinding!!.animation2.background as AnimationDrawable?

        slotBinding!!.animation3.setBackgroundResource(R.drawable.wheel3animation)
        animation3 = slotBinding!!.animation3.background as AnimationDrawable?

        winSound = MediaPlayer.create(applicationContext, R.raw.winsound)
        rattleSound = MediaPlayer.create(applicationContext, R.raw.rattle)
        loseSound = MediaPlayer.create(applicationContext, R.raw.lose)

        if (store!!.getInt(Utilll.str_slotLimit) < 5) {
            slotBinding!!.lblCArd.text = resources.getString(R.string.str_slot_machin)
        } else {
            slotBinding!!.lblCArd.text = resources.getString(R.string.str_slot_machin1)
        }

        slotBinding!!.lblLimit.text =
            resources.getString(R.string.str_slot_machin111) + " ( " + store!!.getInt(Utilll.str_slotLimit) + " / 5 )"

        slotBinding!!.btnSpin.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_slotLimit) < 5) {
                spin()
                startSlotAnimations()
//            showHoldIfAvailable()
//            showNudgeIfAvailable()
                val handler = Handler()
                handler.postDelayed({
                    clearSlotAnimations()
//                updatePlayerMoney()
//                resetNudgesFalse()
//                resetHoldButtonsFalse()
                }, 1300)
            } else {
                Toast.makeText(this, "Your Daily Slot Limit is Over", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBackPressed() {
        play()
        AppManage.getInstance(context).showInterstitialAd(
            context,
            {
                onBackPressedDispatcher.onBackPressed()
                Animatoo.animateSlideLeft(context!!)
            },
            "",
            AppManage.app_innerClickCntSwAd
        )
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun play() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            )
        } else {
            vibrator!!.vibrate(100)
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.click)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }

    private fun startSlotAnimations() {
        rattleSound!!.start()
        startAnimation1()
        startAnimation2()
        startAnimation3()
    }

    private fun clearSlotAnimations() {
        clearAnimation1View()
        clearAnimation2View()
        clearAnimation3View()
//        spinButton.setVisibility(View.VISIBLE)
    }

    private fun startAnimation1() {
        if (!aWheel1!!.playerHasHeld) {
            slotBinding!!.animation1.visibility = View.VISIBLE
            animation1!!.start()
        }
    }

    private fun clearAnimation1View() {
        slotBinding!!.animation1.visibility = View.INVISIBLE
    }

    private fun startAnimation2() {
        if (!aWheel2!!.playerHasHeld) {
            slotBinding!!.animation2.visibility = View.VISIBLE
            animation2!!.start()
        }
    }

    private fun clearAnimation2View() {
        slotBinding!!.animation2.visibility = View.INVISIBLE
    }

    private fun startAnimation3() {
        if (!aWheel3!!.playerHasHeld) {
            slotBinding!!.animation3.visibility = View.VISIBLE
            animation3!!.start()
        }
    }

    private fun clearAnimation3View() {
        slotBinding!!.animation3.visibility = View.INVISIBLE
    }

    private fun spin(): ArrayList<AaSymbols> {
        val newLine: ArrayList<AaSymbols> = aSlotMachine!!.spin()
        val value = aSlotMachine!!.getWinValue(newLine)
        val handler = Handler()
        handler.postDelayed({
            updateCurrentLine(newLine)
            updateTopLine()
            updateBottomLine()
//            win(value)
            if (aSlotMachine!!.checkWin(newLine)) {
                win(value)
            } else {
                loseSound!!.start()
            }
        }, 1300)
        return newLine
    }

    private fun win(value: Int) {
//        winnerImage.setVisibility(View.VISIBLE)
//        spinButton.setVisibility(View.INVISIBLE)
        winSound!!.start()
        val handler = Handler()
        handler.postDelayed({
//            winnerImage.setVisibility(View.INVISIBLE)
//            spinButton.setVisibility(View.VISIBLE)
            aSlotMachine!!.addPlayerFunds(value)

//            updatePlayerMoney()
        }, 2000)
    }

    private fun updateCurrentLine(newLine: ArrayList<AaSymbols>) {
        val lineImages = aSlotMachine!!.getLineImages(newLine)
        val image1 = lineImages[0]
        val image2 = lineImages[1]
        val image3 = lineImages[2]
        val idImage1 = resources.getIdentifier(image1, "drawable", packageName)
        val idImage2 = resources.getIdentifier(image2, "drawable", packageName)
        val idImage3 = resources.getIdentifier(image3, "drawable", packageName)
        slotBinding!!.imageSymbol1.setImageResource(idImage1)
        slotBinding!!.imageSymbol2.setImageResource(idImage2)
        slotBinding!!.imageSymbol3.setImageResource(idImage3)

        val lineImages1 = aSlotMachine!!.getLineImages1(newLine)
        val number = lineImages1[0] + lineImages1[1] + lineImages1[2]

        db!!.addContact(
            aaIncome(
                "Slot Machine " + store!!.getString(Utilll.str_currentDate),
                "" + number
            )
        )

        Log.e("COINN", "vv  " + number)
        store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + number)
        store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + number)

        slotBinding!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)

        store!!.putInt(
            Utilll.str_slotLimit,
            store!!.getInt(Utilll.str_slotLimit) + 1
        )
        slotBinding!!.lblLimit.text =
            resources.getString(R.string.str_slot_machin111) + " ( " + store!!.getInt(Utilll.str_slotLimit) + " / 5 )"
    }

    private fun updateTopLine() {
        val topLine = aSlotMachine!!.topLineSymbols
        val lineImages = aSlotMachine!!.getLineImages(topLine)
        val image1 = lineImages[0]
        val image2 = lineImages[1]
        val image3 = lineImages[2]
        val idImage1 = resources.getIdentifier(image1, "drawable", packageName)
        val idImage2 = resources.getIdentifier(image2, "drawable", packageName)
        val idImage3 = resources.getIdentifier(image3, "drawable", packageName)
        slotBinding!!.wheel1TopImage.setImageResource(idImage1)
        slotBinding!!.wheel2TopImage.setImageResource(idImage2)
        slotBinding!!.wheel3TopImage.setImageResource(idImage3)
    }

    private fun updateBottomLine() {
        val bottomLine = aSlotMachine!!.bottomLineSymbols
        val lineImages = aSlotMachine!!.getLineImages(bottomLine)
        val image1 = lineImages[0]
        val image2 = lineImages[1]
        val image3 = lineImages[2]
        val idImage1 = resources.getIdentifier(image1, "drawable", packageName)
        val idImage2 = resources.getIdentifier(image2, "drawable", packageName)
        val idImage3 = resources.getIdentifier(image3, "drawable", packageName)
        slotBinding!!.wheel1BottomImage.setImageResource(idImage1)
        slotBinding!!.wheel2BottomImage.setImageResource(idImage2)
        slotBinding!!.wheel3BottomImage.setImageResource(idImage3)
    }
}