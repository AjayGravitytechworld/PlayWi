package com.abc.demo.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.abc.demo.R
import com.abc.demo.databinding.FragmentProfileBinding
import com.abc.demo.ui.aFaqActivity
import com.abc.demo.ui.aaFeedbackActivity
import com.abc.demo.ui.IncomeActivity
import com.abc.demo.ui.aaInviteEarnActivity
import com.abc.demo.ui.LeaderboardActivity
import com.abc.demo.ui.aaSettingActivity
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.abc.demo.ads.AppManage
import kotlin.random.Random

class ProfileFrag : Fragment(), View.OnClickListener {

    private var profileBinding: FragmentProfileBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null

    private val anim: AnimationDrawable? = null
    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        layoutInflater: LayoutInflater, viewGroup: ViewGroup?, bundle: Bundle?
    ): View? {
        profileBinding = FragmentProfileBinding.inflate(layoutInflater, viewGroup, false)
        context = getContext()
        store = Utilll.getInstance(requireContext())

//        profileBinding!!.imgInvite.animationXSlide(Bounce.BOUNCE_IN)


        if (store!!.getInt(Utilll.str_isLogin) == 0) {
            profileBinding!!.lblUserName.visibility = View.GONE
        } else {
            profileBinding!!.lblUserName.text = "" + store!!.getString(Utilll.str_userName)
            Glide.with(requireContext()).load(store!!.getString(Utilll.str_userImage))
                .into(profileBinding!!.userImage)

            if (store!!.getInt(Utilll.str_totalCoin) < 1000) {
                profileBinding!!.lblLavel.text = "LEVEL 1"
            } else if (store!!.getInt(Utilll.str_totalCoin) in 1001..4999) {
                profileBinding!!.lblLavel.text = "LEVEL 2"
            } else if (store!!.getInt(Utilll.str_totalCoin) in 5001..9999) {
                profileBinding!!.lblLavel.text = "LEVEL 3"
            } else if (store!!.getInt(Utilll.str_totalCoin) in 10001..19999) {
                profileBinding!!.lblLavel.text = "LEVEL 4"
            } else {
                profileBinding!!.lblLavel.text = "LEVEL 5"
            }
        }

        profileBinding!!.lblCoin.text = "" + store!!.getInt(Utilll.str_totalCoin)
        profileBinding!!.lblTodayCoin.text = "" + store!!.getInt(Utilll.str_todayCoin)
        profileBinding!!.lblMyBalance.text = "" + store!!.getInt(Utilll.str_totalCoin)
        profileBinding!!.lblTotalIncome.text = "" + (store!!.getInt(Utilll.str_totalCoin) + (store!!.getInt(Utilll.str_withdrowCoin) * 100))

        if (store!!.getInt(Utilll.str_totalCoin) < 1000) {
            profileBinding!!.lblLavel.text = "LEVEL 1"
        } else if (store!!.getInt(Utilll.str_totalCoin) < 2000) {
            profileBinding!!.lblLavel.text = "LEVEL 2"
        }

        val anim = profileBinding!!.container.background as AnimationDrawable
        anim.setEnterFadeDuration(100)
        anim.setExitFadeDuration(100)
        anim.start()

        emitBubbles()

        profileBinding!!.llIncomeHistory.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                {
                    startActivity(Intent(context, IncomeActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
        profileBinding!!.llleaderboard.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                {
                    startActivity(Intent(context, LeaderboardActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
        profileBinding!!.lblSetting.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                {
                    startActivity(Intent(context, aaSettingActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
        profileBinding!!.llInviteFD.setOnClickListener {
            play()
//            AppManage.getInstance(context).showInterstitialAd(
//                context,
//                {
                    startActivity(Intent(context, aaInviteEarnActivity::class.java))
                    Animatoo.animateZoom(requireContext())
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }

        profileBinding!!.lblFAQS.setOnClickListener {
            play()
//            AppManage.getInstance(context).showInterstitialAd(
//                context,
//                {
                    startActivity(Intent(context, aFaqActivity::class.java))
                    Animatoo.animateZoom(requireContext())
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }

        profileBinding!!.lblFeedback.setOnClickListener {
            play()
//            AppManage.getInstance(context).showInterstitialAd(
//                context,
//                {
                    startActivity(Intent(context, aaFeedbackActivity::class.java))
                    Animatoo.animateZoom(requireContext())
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }

        return profileBinding?.root
    }

    override fun onClick(v: View) {

    }

    override fun onResume() {
        super.onResume()
        if (anim != null && !anim.isRunning) anim.start()
    }

    override fun onPause() {
        super.onPause()
        if (anim != null && anim.isRunning) anim.stop()
    }

    private fun emitBubbles() {
        Handler().postDelayed({
            val size = Random.nextInt(20, 80)
            profileBinding!!.bubbleEmitter.emitBubble(size)
            emitBubbles()
        }, Random.nextLong(100, 500))
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun play() {
        val vibrator = getContext()?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            )
        } else {
            vibrator!!.vibrate(100)
        }

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.click)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }
}