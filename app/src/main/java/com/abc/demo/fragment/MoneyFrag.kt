package com.abc.demo.fragment

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.abc.demo.R
import com.abc.demo.databinding.FrmoneyBinding
import com.abc.demo.ui.aExitActivity
import com.abc.demo.ui.aaInviteEarnActivity
import com.abc.demo.ui.aaScratchActivity
import com.abc.demo.ui.aaSlotActivity
import com.abc.demo.ui.aaSpinWheelNewActivity
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage
import com.rommansabbir.animationx.Bounce
import com.rommansabbir.animationx.animationXBounce

class MoneyFrag : Fragment(), View.OnClickListener {

    private var moneyBinding: FrmoneyBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null

    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        layoutInflater: LayoutInflater, viewGroup: ViewGroup?, bundle: Bundle?
    ): View? {
        moneyBinding = FrmoneyBinding.inflate(layoutInflater, viewGroup, false)
        context = getContext()
        store = Utilll.getInstance(requireContext())

        AppManage.getInstance(context).showNativetype(
            moneyBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

        if(AppManage.quiz == 1){
            moneyBinding!!.quizBtn.visibility = View.VISIBLE
        }else{
            moneyBinding!!.quizBtn.visibility = View.GONE
        }

        moneyBinding!!.SpinBtn.animationXBounce(Bounce.BOUNCE_IN)
        moneyBinding!!.scratchBtn.animationXBounce(Bounce.BOUNCE_IN)
        moneyBinding!!.slotBtn.animationXBounce(Bounce.BOUNCE_IN)
        moneyBinding!!.quizBtn.animationXBounce(Bounce.BOUNCE_IN)
//        moneyBinding!!.giftBtn.animationXBounce(Bounce.BOUNCE_IN)
//        moneyBinding!!.walletBtn.animationXBounce(Bounce.BOUNCE_IN)
        moneyBinding!!.inviteBtn.animationXBounce(Bounce.BOUNCE_IN)
        moneyBinding!!.rateBtn.animationXBounce(Bounce.BOUNCE_IN)
        moneyBinding!!.rateBtn11.animationXBounce(Bounce.BOUNCE_IN)
        moneyBinding!!.nativeAd.animationXBounce(Bounce.BOUNCE_IN)
        moneyBinding!!.nativeAd2.animationXBounce(Bounce.BOUNCE_IN)
        moneyBinding!!.qwe.animationXBounce(Bounce.BOUNCE_IN)

        if (AppManage.quiz==1){
            moneyBinding!!.slotBtn.visibility = View.VISIBLE
        }else{
            moneyBinding!!.slotBtn.visibility = View.GONE
        }

        moneyBinding!!.SpinBtn.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                {
                    startActivity(Intent(context, aaSpinWheelNewActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }

        moneyBinding!!.scratchBtn.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                {
                    startActivity(Intent(context, aaScratchActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }

        moneyBinding!!.slotBtn.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                {
//                    startActivity(Intent(context, GameActivity::class.java))
                    startActivity(Intent(context, aaSlotActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }

        moneyBinding!!.quizBtn.setOnClickListener {
            play()
            UtilsFiff.customAdCLick(requireContext())
        }

//        moneyBinding!!.giftBtn.setOnClickListener {
//            play()
//            AppManage.getInstance(context).showInterstitialAd(
//                context,
//                {
//                    startActivity(Intent(context, GiftBoxActivity::class.java))
//                    Animatoo.animateZoom(requireContext())
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
//        }

//        moneyBinding!!.walletBtn.setOnClickListener {
//            play()
////            AppManage.getInstance(context).showInterstitialAd(
////                context,
////                {
//                    startActivity(Intent(context, SpinWheelActivity::class.java))
//                    Animatoo.animateZoom(requireContext())
////                },
////                "",
////                AppManage.app_mainClickCntSwAd
////            )
//        }

        moneyBinding!!.inviteBtn.setOnClickListener {
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

        moneyBinding!!.rateBtn.setOnClickListener {
            play()
            UtilsFiff.rateApp(requireContext())
        }

        moneyBinding!!.rateBtn11.setOnClickListener {
            play()
//            AppManage.getInstance(context).showInterstitialAd(
//                context,
//                {
                    startActivity(Intent(context, aExitActivity::class.java).putExtra("type",1))
                    Animatoo.animateZoom(requireContext())
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }

        moneyBinding!!.qwe.setOnClickListener {
            play()
//            AppManage.getInstance(context).showInterstitialAd(
//                context,
//                {
                    startActivity(Intent(context, aExitActivity::class.java).putExtra("type",2))
                    Animatoo.animateZoom(requireContext())
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
        }

        return moneyBinding?.root
    }

    override fun onClick(v: View) {}

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