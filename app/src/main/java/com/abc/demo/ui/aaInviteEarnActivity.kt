package com.abc.demo.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.BuildConfig
import com.abc.demo.R
import com.abc.demo.databinding.ActivityInviteEarnBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.abc.demo.ads.AppManage


class aaInviteEarnActivity : AppCompatActivity() {

    var inviteEarnBinding: ActivityInviteEarnBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inviteEarnBinding = ActivityInviteEarnBinding.inflate(layoutInflater)
        setContentView(inviteEarnBinding?.root)

        context = this
        store = Utilll.getInstance(this)

        inviteEarnBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

        inviteEarnBinding!!.btnBack1.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                {
                    startActivity(
                        Intent(
                            this@aaInviteEarnActivity,
                            aaRefralActivity::class.java
                        ).putExtra("code", inviteEarnBinding!!.lblCode.text.toString())
                    )
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }

        inviteEarnBinding!!.bcopy.setOnClickListener {
            play()
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip =
                ClipData.newPlainText("Refral Code", inviteEarnBinding!!.lblCode.text.toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context!!, "Refral Code is copied to clipboard", Toast.LENGTH_SHORT)
                .show()
        }

        AppManage.getInstance(this).showNativetype(
            inviteEarnBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 3
        )

//        AppManage.getInstance(this).showNative(
//            inviteEarnBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

//        AppManage.getInstance(this).showNativeBanner(
//            inviteEarnBinding!!.bannerContainer,
//            AppManage.ADMOB_B[0], AppManage.FACEBOOK_NB[0]
//        )

        inviteEarnBinding!!.okayBtn.setOnClickListener {
            play()
            try {
                store!!.putInt(Utilll.str_isfirstInvite, 1)
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    resources.getString(R.string.app_name)
                )
                var shareMessage = resources.getString(R.string.str_recomondet)
                shareMessage =
                    """
                ${shareMessage + " https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID}
                
                
                """.trimIndent()

                shareMessage =
                    shareMessage + "Invite Code :  " + inviteEarnBinding!!.lblCode.text.toString()

                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(
                    Intent.createChooser(
                        shareIntent,
                        resources.getString(R.string.str_choose_one)
                    )
                )
            } catch (e: Exception) {
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
}