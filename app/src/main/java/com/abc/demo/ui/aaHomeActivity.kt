package com.abc.demo.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.abc.demo.R
import com.abc.demo.databinding.ActivityHomeBinding
import com.abc.demo.fragment.GameFrag
import com.abc.demo.fragment.MoneyFrag
import com.abc.demo.fragment.ProfileFrag
import com.abc.demo.fragment.TaskFrag
import com.abc.demo.fragment.VideoFragm
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage

class aaHomeActivity : AppCompatActivity() {

    private var homeBinding: ActivityHomeBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding?.root)

        context = this
        store = Utilll.getInstance(this)

//        homeBinding!!.image1.setImageResource(R.drawable.iv1)
        homeBinding!!.image2.setBackgroundResource(R.drawable.iv_money)
        homeBinding!!.image3.setBackgroundResource(R.drawable.iv_task)
        homeBinding!!.image4.setBackgroundResource(R.drawable.iv_game)
        homeBinding!!.image5.setBackgroundResource(R.drawable.iv_profile)

        addFragment(VideoFragm(), false, resources.getString(R.string.str_video))

        homeBinding!!.btnVideo.setOnClickListener {
            homeBinding!!.image1.setBackgroundResource(R.drawable.iv1)
            homeBinding!!.image2.setBackgroundResource(R.drawable.iv_money)
            homeBinding!!.image3.setBackgroundResource(R.drawable.iv_task)
            homeBinding!!.image4.setBackgroundResource(R.drawable.iv_game)
            homeBinding!!.image5.setBackgroundResource(R.drawable.iv_profile)

            vibratee()
            play()
            addFragment(VideoFragm(), false, resources.getString(R.string.str_video))
        }

        homeBinding!!.btnMoney.setOnClickListener {
            homeBinding!!.image1.setBackgroundResource(R.drawable.iv_video)
            homeBinding!!.image2.setBackgroundResource(R.drawable.iv2)
            homeBinding!!.image3.setBackgroundResource(R.drawable.iv_task)
            homeBinding!!.image4.setBackgroundResource(R.drawable.iv_game)
            homeBinding!!.image5.setBackgroundResource(R.drawable.iv_profile)

            vibratee()
            play()
            addFragment(MoneyFrag(), false, resources.getString(R.string.str_money))
        }

        homeBinding!!.btnTask.setOnClickListener {
            homeBinding!!.image1.setBackgroundResource(R.drawable.iv_video)
            homeBinding!!.image2.setBackgroundResource(R.drawable.iv_money)
            homeBinding!!.image3.setBackgroundResource(R.drawable.iv3)
            homeBinding!!.image4.setBackgroundResource(R.drawable.iv_game)
            homeBinding!!.image5.setBackgroundResource(R.drawable.iv_profile)

            vibratee()
            play()
            addFragment(TaskFrag(), false, resources.getString(R.string.str_tasks))
        }

        homeBinding!!.btnGame.setOnClickListener {

            homeBinding!!.image1.setBackgroundResource(R.drawable.iv_video)
            homeBinding!!.image2.setBackgroundResource(R.drawable.iv_money)
            homeBinding!!.image3.setBackgroundResource(R.drawable.iv_task)
            homeBinding!!.image4.setBackgroundResource(R.drawable.iv4)
            homeBinding!!.image5.setBackgroundResource(R.drawable.iv_profile)

            vibratee()
            play()
            addFragment(GameFrag(), false, resources.getString(R.string.str_game))
        }

        homeBinding!!.btnProfile.setOnClickListener {

            homeBinding!!.image1.setBackgroundResource(R.drawable.iv_video)
            homeBinding!!.image2.setBackgroundResource(R.drawable.iv_money)
            homeBinding!!.image3.setBackgroundResource(R.drawable.iv_task)
            homeBinding!!.image4.setBackgroundResource(R.drawable.iv_game)
            homeBinding!!.image5.setBackgroundResource(R.drawable.iv5)

            vibratee()
            play()
            addFragment(ProfileFrag(), false, resources.getString(R.string.str_profile))

        }
    }

    private fun addFragment(fragment: Fragment?, addToBackStack: Boolean, tag: String?) {
        AppManage.getInstance(context).showInterstitialAd(
            context, {
                val manager: FragmentManager = supportFragmentManager
                val ft: FragmentTransaction = manager.beginTransaction()
                if (addToBackStack) {
                    ft.addToBackStack(tag)
                }
                ft.replace(homeBinding!!.frameLayout.id, fragment!!, tag)
                ft.commitAllowingStateLoss()
            }, "", AppManage.app_mainClickCntSwAd
        )
    }

    override fun onBackPressed() {
        vibratee()
        play()
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

    private fun play() {
        mediaPlayer = MediaPlayer.create(this, R.raw.click)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }

    private fun vibratee() {
//        var vibrationEffect2: VibrationEffect? = null
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
////            vibrationEffect2 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
////            vibrationEffect2 = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)
//            vibrationEffect2 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
//            vibrator!!.cancel()
//            vibrator!!.vibrate(vibrationEffect2)
//        }

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createOneShot(
                    200, 255
                )
            )
        } else {
            vibrator!!.vibrate(200)
        }
    }
}