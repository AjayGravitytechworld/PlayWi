package com.abc.demo.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import com.abc.demo.R
import com.abc.demo.adapter.aaLeaderAdpater
import com.abc.demo.databinding.LeaderboardBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.abc.demo.ads.AppManage

class LeaderboardActivity : AppCompatActivity() {

    private var leaderboardBinding: LeaderboardBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null

    private var flag: Int? = 1

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leaderboardBinding = LeaderboardBinding.inflate(layoutInflater)
        setContentView(leaderboardBinding?.root)

        context = this
        store = Utilll.getInstance(this)

        AppManage.getInstance(this).showNativeBanner(
            leaderboardBinding!!.bannerContainer,
            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
        )

        leaderboardBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

        leaderboardBinding!!.btnshare.setOnClickListener {
            play()
            UtilsFiff.shareApp(context!!)
        }

        setData()

        leaderboardBinding!!.btnweek.setOnClickListener {
            play()
            leaderboardBinding!!.btnweek.setBackgroundResource(R.drawable.leaderboxx)
            leaderboardBinding!!.btnmonth.setBackgroundResource(R.drawable.dddfggh)
            flag = 1
            setData()
        }

        leaderboardBinding!!.btnmonth.setOnClickListener {
            play()
            leaderboardBinding!!.btnmonth.setBackgroundResource(R.drawable.leaderboxx)
            leaderboardBinding!!.btnweek.setBackgroundResource(R.drawable.dddfggh)
            flag = 2
            setData()
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

    private fun setData() {
        if (flag == 1) {

            leaderboardBinding!!.lblUserName1.text = UtilsFiff.leader1?.get(0)?.name
            leaderboardBinding!!.lblUSerCoin1.text = "" + UtilsFiff.leader1?.get(0)?.coin

            Glide.with(context!!).load(UtilsFiff.leader1?.get(0)?.image)
                .into(leaderboardBinding!!.userImage1)

            Glide.with(context!!).load(UtilsFiff.leader1?.get(0)?.country)
                .into(leaderboardBinding!!.userCountry1)

            leaderboardBinding!!.lblUserName2.text = UtilsFiff.leader1?.get(1)?.name
            leaderboardBinding!!.lblUSerCoin2.text = "" + UtilsFiff.leader1?.get(1)?.coin

            Glide.with(context!!).load(UtilsFiff.leader1?.get(1)?.image)
                .into(leaderboardBinding!!.userImage2)

            Glide.with(context!!).load(UtilsFiff.leader1?.get(1)?.country)
                .into(leaderboardBinding!!.userCountry2)

            leaderboardBinding!!.lblUserName3.text = UtilsFiff.leader1?.get(2)?.name
            leaderboardBinding!!.lblUSerCoin3.text = "" + UtilsFiff.leader1?.get(2)?.coin

            Glide.with(context!!).load(UtilsFiff.leader1?.get(2)?.image)
                .into(leaderboardBinding!!.userImage3)

            Glide.with(context!!).load(UtilsFiff.leader1?.get(2)?.country)
                .into(leaderboardBinding!!.userCountry3)

            leaderboardBinding!!.rvLeader.adapter = aaLeaderAdpater(context!!, UtilsFiff.leader1!!)
        } else {

            leaderboardBinding!!.lblUserName1.text = UtilsFiff.leader2?.get(0)?.name
            leaderboardBinding!!.lblUSerCoin1.text = "" + UtilsFiff.leader2?.get(0)?.coin

            Glide.with(context!!).load(UtilsFiff.leader2?.get(0)?.image)
                .into(leaderboardBinding!!.userImage1)

            Glide.with(context!!).load(UtilsFiff.leader2?.get(0)?.country)
                .into(leaderboardBinding!!.userCountry1)

            leaderboardBinding!!.lblUserName2.text = UtilsFiff.leader2?.get(1)?.name
            leaderboardBinding!!.lblUSerCoin2.text = "" + UtilsFiff.leader2?.get(1)?.coin

            Glide.with(context!!).load(UtilsFiff.leader2?.get(1)?.image)
                .into(leaderboardBinding!!.userImage2)

            Glide.with(context!!).load(UtilsFiff.leader2?.get(1)?.country)
                .into(leaderboardBinding!!.userCountry2)

            leaderboardBinding!!.lblUserName3.text = UtilsFiff.leader2?.get(2)?.name
            leaderboardBinding!!.lblUSerCoin3.text = "" + UtilsFiff.leader2?.get(2)?.coin

            Glide.with(context!!).load(UtilsFiff.leader2?.get(2)?.image)
                .into(leaderboardBinding!!.userImage3)

            Glide.with(context!!).load(UtilsFiff.leader2?.get(2)?.country)
                .into(leaderboardBinding!!.userCountry3)

            leaderboardBinding!!.rvLeader.adapter = aaLeaderAdpater(context!!, UtilsFiff.leader2!!)
        }
    }
}