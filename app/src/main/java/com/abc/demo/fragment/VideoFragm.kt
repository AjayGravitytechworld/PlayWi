package com.abc.demo.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.abc.demo.R
import com.abc.demo.adapter.aaBannerAdapter
import com.abc.demo.adapter.aaPopularAdpater
import com.abc.demo.adapter.aaTrendingAdpater
import com.abc.demo.database.Database
import com.abc.demo.databinding.FrvideoBinding
import com.abc.demo.model.aaIncome
import com.abc.demo.ui.aaGiftBoxActivity
import com.abc.demo.ui.aaMoreVideoActivity
import com.abc.demo.ui.aaRedeemActivity
import com.abc.demo.ui.aaScratchActivity
import com.abc.demo.ui.aaSettingActivity
import com.abc.demo.ui.aaSpinWheelNewActivity
import com.abc.demo.ui.aaVideoViewActivity
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.abc.demo.ads.AppManage
import tyrantgit.explosionfield.ExplosionField

class VideoFragm : Fragment(), View.OnClickListener {
    private var vadioo: FrvideoBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mExplosionField: ExplosionField? = null
    private var mediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null
    private var db: Database? = null

    var handler: Handler? = null
    var delay = 2000
    var page = 0
    var adapter: aaBannerAdapter? = null
    var runnable: Runnable = object : Runnable {
        override fun run() {
            if (adapter!!.getCount() === page) {
                page = 0
            } else {
                page++
            }
            vadioo!!.vpHome.setCurrentItem(page, true)
            handler!!.postDelayed(this, delay.toLong())
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        layoutInflater: LayoutInflater, viewGroup: ViewGroup?, bundle: Bundle?
    ): View? {
        vadioo = FrvideoBinding.inflate(layoutInflater, viewGroup, false)
        context = getContext()
        store = Utilll.getInstance(requireContext())

        db = Database(context)

        vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

        mExplosionField = ExplosionField.attach2Window(activity)

        vadioo!!.groupEmojiContainer.addEmoji(R.drawable.coin1)
        vadioo!!.groupEmojiContainer.addEmoji(R.drawable.coin2)
        vadioo!!.groupEmojiContainer.addEmoji(R.drawable.coin3)
        vadioo!!.groupEmojiContainer.setPer(10)
        vadioo!!.groupEmojiContainer.setDuration(3000)
        vadioo!!.groupEmojiContainer.setDropDuration(2400)
        vadioo!!.groupEmojiContainer.setDropFrequency(500)

        if(AppManage.quiz == 1){
            vadioo!!.floating.visibility = View.VISIBLE
        }else{
            vadioo!!.floating.visibility = View.GONE
        }

        handler = Handler()

        adapter = aaBannerAdapter(requireContext())
        vadioo!!.vpHome.adapter = adapter

        vadioo!!.tabLayout.setupWithViewPager(vadioo!!.vpHome, true)
        vadioo!!.vpHome.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                page = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        AppManage.getInstance(context).showNativetype(
            vadioo!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

//        AppManage.getInstance(context).showNative(
//            videoBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

        if (store!!.getInt(Utilll.str_isLogin) == 0) {
            vadioo!!.userImage.visibility = View.GONE
            vadioo!!.layoutName.visibility = View.GONE
        } else {
            vadioo!!.userName.text = store!!.getString(Utilll.str_userName)
            Glide.with(requireContext()).load(store!!.getString(Utilll.str_userImage))
                .into(vadioo!!.userImage)


            if (store!!.getInt(Utilll.str_totalCoin) < 1000) {
                vadioo!!.level.text = "LEVEL 1"
            } else if (store!!.getInt(Utilll.str_totalCoin) in 1001..4999) {
                vadioo!!.level.text = "LEVEL 2"
            } else if (store!!.getInt(Utilll.str_totalCoin) in 5001..9999) {
                vadioo!!.level.text = "LEVEL 3"
            } else if (store!!.getInt(Utilll.str_totalCoin) in 10001..19999) {
                vadioo!!.level.text = "LEVEL 4"
            } else {
                vadioo!!.level.text = "LEVEL 5"
            }
        }

        vadioo!!.dailyBonus.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_isDailyBonus) == 0) {
                playCoin1()
                vadioo!!.groupEmojiContainer.startDropping()
                store!!.putInt(Utilll.str_isDailyBonus, 1)
                db!!.addContact(
                    aaIncome(
                        "Daily Bonus " + store!!.getString(Utilll.str_currentDate),
                        "100"
                    )
                )
                updateCoin(100)
                Toast.makeText(context, "You Earned 100 Coins", Toast.LENGTH_SHORT)
                    .show()
            } else {
                alreadyCheckDialog()
            }
        }

        vadioo!!.next1.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    startActivity(
                        Intent(context, aaMoreVideoActivity::class.java).putExtra(
                            "type", 1
                        )
                    )
                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }

        vadioo!!.next2.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    startActivity(
                        Intent(context, aaMoreVideoActivity::class.java).putExtra(
                            "type", 2
                        )
                    )
                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }

        vadioo!!.next3.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    startActivity(
                        Intent(context, aaMoreVideoActivity::class.java).putExtra(
                            "type", 3
                        )
                    )
                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }

        vadioo!!.floating.setOnClickListener {
            mExplosionField!!.explode(vadioo!!.floating)
            vibratee()
            playCoin1()
            updateCoin(50)
            Toast.makeText(context, "You Earned 50 Coins", Toast.LENGTH_SHORT)
                .show()
        }

        vadioo!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)
        vadioo!!.userName.text = "" + store!!.getString(Utilll.str_userName)

        if (UtilsFiff.today_video != null && UtilsFiff.today_video!!.size > 5) {

            vadioo!!.video1Text.text = UtilsFiff.today_video!![0].name
            Glide.with(requireContext()).load(UtilsFiff.today_video!![0].image)
                .into(vadioo!!.video1Img)

            vadioo!!.video2Text.text = UtilsFiff.today_video!![1].name
            Glide.with(requireContext()).load(UtilsFiff.today_video!![1].image)
                .into(vadioo!!.video2Img)

            vadioo!!.video3Text.text = UtilsFiff.today_video!![2].name
            Glide.with(requireContext()).load(UtilsFiff.today_video!![2].image)
                .into(vadioo!!.video3Img)
        }

        if (UtilsFiff.trending_video != null && UtilsFiff.trending_video!!.size > 5) {
            vadioo!!.rv1.adapter =
                aaTrendingAdpater(requireContext(), UtilsFiff.trending_video!!)

//            videoBinding!!.rv1.addOnItemTouchListener(RecyclerTouchListener(this@MainActivity, object : RecyclerTouchListener.ClickListener {
//                @SuppressLint("Recycle")
//                override fun onClick(view: View?, position: Int) {
//                    dataArray[position%dataArray.size]?.let { model ->
//                        Toast.makeText(this@MainActivity,model,Toast.LENGTH_LONG).show()
//                    }
//                }
//            })
//            handler.postDelayed(runnable, speedScroll.toLong())
        }

        if (UtilsFiff.popular_video != null && UtilsFiff.popular_video!!.size > 2) {
            vadioo!!.rv2.adapter = aaPopularAdpater(requireContext(), UtilsFiff.popular_video!!)
        }

        vadioo!!.coin.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                {
                    startActivity(Intent(context, aaRedeemActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }

        vadioo!!.video1Img.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    db!!.addContact(
                        aaIncome(
                            "Watch Video Today Task",
                            "30"
                        )
                    )
                    store!!.putInt(
                        Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 30
                    )
                    store!!.putInt(
                        Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 30
                    )
                    startActivity(
                        Intent(context, aaVideoViewActivity::class.java).putExtra(
                            "link", UtilsFiff.today_video!![0].link
                        )
                    )
                    Animatoo.animateZoom(requireContext())
                    Toast.makeText(context, "You Earned 30 Coins", Toast.LENGTH_SHORT)
                        .show()
                }, "", AppManage.app_mainClickCntSwAd
            )
        }

        vadioo!!.video2Img.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    db!!.addContact(
                        aaIncome(
                            "Watch Video Today Task",
                            "30"
                        )
                    )
                    store!!.putInt(
                        Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 30
                    )
                    store!!.putInt(
                        Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 30
                    )
                    startActivity(
                        Intent(context, aaVideoViewActivity::class.java).putExtra(
                            "link", UtilsFiff.trending_video!![0].link
                        )
                    )
                    Animatoo.animateZoom(requireContext())
                    Toast.makeText(context, "You Earned 30 Coins", Toast.LENGTH_SHORT)
                        .show()
                }, "", AppManage.app_mainClickCntSwAd
            )
        }

        vadioo!!.video3Img.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    db!!.addContact(
                        aaIncome(
                            "Watch Video Today Task",
                            "30"
                        )
                    )
                    store!!.putInt(
                        Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 30
                    )
                    store!!.putInt(
                        Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 30
                    )
                    startActivity(
                        Intent(context, aaVideoViewActivity::class.java).putExtra(
                            "link", UtilsFiff.popular_video!![0].link
                        )
                    )
                    Animatoo.animateZoom(requireContext())
                    Toast.makeText(context, "You Earned 30 Coins", Toast.LENGTH_SHORT)
                        .show()
                }, "", AppManage.app_mainClickCntSwAd
            )
        }
        vadioo!!.setting.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    startActivity(Intent(context, aaSettingActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }
        vadioo!!.spinWheel.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    startActivity(Intent(context, aaSpinWheelNewActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }
        vadioo!!.scratchcard.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    startActivity(Intent(context, aaScratchActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }
        vadioo!!.quizgame1.setOnClickListener {
            play()
            UtilsFiff.customAdCLick(requireContext())
        }
        vadioo!!.giftBox.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    startActivity(Intent(context, aaGiftBoxActivity::class.java))
                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }

        animateIcon()

        if (store!!.getInt(Utilll.str_isWelcomeBonus) == 0) {
            welcomeBonusDialog()
        } else {
            if (store!!.getInt(Utilll.str_isLogin) == 1 && store!!.getInt(Utilll.str_isLoginBonus) == 0) {
                loginBonusDialog()
            }
        }

        return vadioo?.root
    }

    override fun onClick(v: View) {}

    fun animateIcon() {
        val loadAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
        Handler().postDelayed({ vadioo!!.floating.startAnimation(loadAnimation) }, 1000L)
        loadAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                animateIcon()
            }
        })
    }

    private fun welcomeBonusDialog() {
        UtilsFiff.alertDialog(requireActivity(),
            R.layout.dialog_welcomebonus,
            true,
            object : UtilsFiff.DialogListener {
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onCreated(alertDialog: android.app.AlertDialog?) {

//                    val videoView: VideoView = alertDialog!!.findViewById(R.id.videoView1)
//                    videoView.setVideoURI(Uri.parse("android.resource://" + context!!.packageName + "/" + R.raw.charm_pay))
//                    videoView.start()
//
//                    videoView.setOnPreparedListener { mp: MediaPlayer ->
//                        mp.isLooping = true
//                    }

                    (alertDialog!!.findViewById<View>(R.id.btnCLose) as ImageView).setOnClickListener {
                        play()
                        alertDialog.dismiss()
                    }
                    (alertDialog.findViewById<View>(R.id.okay_btn) as RelativeLayout).setOnClickListener {
                        playCoin1()
                        store!!.putInt(Utilll.str_isWelcomeBonus, 1)
                        alertDialog.dismiss()
                        db!!.addContact(aaIncome("Welcome Bonus", "100"))
                        vibratee()
                        updateCoin(100)
                        if (store!!.getInt(Utilll.str_isLogin) == 1 && store!!.getInt(Utilll.str_isLoginBonus) == 0) {
                            loginBonusDialog()
                        }
                    }
                }
            })
    }

    private fun loginBonusDialog() {
        UtilsFiff.alertDialog(requireActivity(),
            R.layout.dialog_welcomebonus,
            true,
            object : UtilsFiff.DialogListener {
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onCreated(alertDialog: android.app.AlertDialog?) {

//                    val videoView: VideoView = alertDialog!!.findViewById(R.id.videoView1)
//                    videoView.setVideoURI(Uri.parse("android.resource://" + context!!.packageName + "/" + R.raw.charm_pay))
//                    videoView.start()
//
//                    videoView.setOnPreparedListener { mp: MediaPlayer ->
//                        mp.isLooping = true
//                    }

                    (alertDialog!!.findViewById<View>(R.id.btnCLose) as ImageView).setOnClickListener {
                        play()
                        alertDialog.dismiss()
                    }
                    (alertDialog.findViewById<View>(R.id.okay_btn) as RelativeLayout).setOnClickListener {
                        playCoin1()
                        store!!.putInt(Utilll.str_isLoginBonus, 1)
                        db!!.addContact(aaIncome("Login Bonus", "100"))
                        vibratee()
                        updateCoin(100)
                        alertDialog.dismiss()
                    }
                }
            })
    }

    private fun alreadyCheckDialog() {
        UtilsFiff.alertDialog(requireActivity(),
            R.layout.dialog_already,
            true,
            object : UtilsFiff.DialogListener {
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onCreated(alertDialog: android.app.AlertDialog?) {
                    (alertDialog!!.findViewById<View>(R.id.lbl4) as TextView).setOnClickListener {
                        play()
                        alertDialog.dismiss()
                    }
                }
            })
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

    private fun playCoin1() {
        mediaPlayer = MediaPlayer.create(activity, R.raw.coin1)
//        mediaPlayer!!.isLooping = true
        mediaPlayer!!.start()
    }

    private fun playCoin2() {
        mediaPlayer = MediaPlayer.create(activity, R.raw.coin1)
//        mediaPlayer!!.isLooping = true
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateCoin(coin: Int) {
        store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + coin)
        store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + coin)

        vadioo!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)
    }

    private fun vibratee() {
        val vibrator = getContext()?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createOneShot(
                    400, 255
                )
            )
        } else {
            vibrator!!.vibrate(400)
        }
    }

    override fun onResume() {
        super.onResume()
//        handler.postDelayed(runnable, speedScroll.toLong())

        vadioo!!.coin.text = "" + store!!.getInt(Utilll.str_totalCoin)

        if (handler != null) handler!!.postDelayed(runnable, delay.toLong())
    }

    //    private val handler = Handler()
//    private val speedScroll = 0
//    private val runnable = object : Runnable {
//        var count = 0
//        override fun run() {
//            if (count == videoBinding!!.rv1.adapter?.itemCount) count = 0
//            if (count < videoBinding!!.rv1.adapter?.itemCount ?: -1) {
//                videoBinding!!.rv1.smoothScrollToPosition(++count)
//                handler.postDelayed(this, speedScroll.toLong())
//            }
//        }
//    }

    override fun onPause() {
        super.onPause()

        if (handler != null) handler!!.removeCallbacks(runnable)
    }

    private val llManager: LinearLayoutManager =
        object : LinearLayoutManager(context, HORIZONTAL, false) {
            override fun smoothScrollToPosition(
                recyclerView: RecyclerView,
                state: RecyclerView.State,
                position: Int
            ) {
                val smoothScroller: LinearSmoothScroller =
                    object : LinearSmoothScroller(context) {
                        private val SPEED = 4000f // Change this value (default=25f)
                        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                            return SPEED
                        }
                    }
                smoothScroller.targetPosition = position
                startSmoothScroll(smoothScroller)
            }
        }
}