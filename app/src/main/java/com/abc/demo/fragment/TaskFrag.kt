package com.abc.demo.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.abc.demo.R
import com.abc.demo.adapter.Adpater_taskaa
import com.abc.demo.adapter.aaTaskAdpater
import com.abc.demo.database.Database
import com.abc.demo.databinding.FragmentTaskBinding
import com.abc.demo.model.aaIncome
import com.abc.demo.ui.aaInviteEarnActivity
import com.abc.demo.ui.aaLoginActivity
import com.abc.demo.ui.aaNotificationActivity
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.fujiyuu75.sequent.Sequent
import com.abc.demo.ads.AppManage
import com.sarnava.textwriter.TextWriter

class TaskFrag : Fragment(), View.OnClickListener {

    private var taskBinding: FragmentTaskBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null
    private var mediaPlayer: MediaPlayer? = null
    private var db: Database? = null
    var daily: Int? = 0

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        layoutInflater: LayoutInflater, viewGroup: ViewGroup?, bundle: Bundle?
    ): View? {

        taskBinding = FragmentTaskBinding.inflate(layoutInflater, viewGroup, false)
        context = getContext()
        store = Utilll.getInstance(requireContext())
        db = Database(context)

        AppManage.getInstance(context).showNativetype(
            taskBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

//        AppManage.getInstance(context).showNativetype(
//            taskBinding!!.nativeAd2,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
//        )

        AppManage.getInstance(context).showNativetype(
            taskBinding!!.nativeAd3,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

//        AppManage.getInstance(context).showNative(
//            taskBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )
//
//        AppManage.getInstance(context).showNative(
//            taskBinding!!.nativeAd2,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )
//
//        AppManage.getInstance(context).showNative(
//            taskBinding!!.nativeAd3,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

        animateIcon()

        taskBinding!!.rv1.adapter = Adpater_taskaa(UtilsFiff.task_iteams!!, requireContext())

        taskBinding!!.TodayTask
//            .setWidth(12F)
            .setDelay(30)
            .setColor(Color.WHITE)
            .setSizeFactor(15f)
            .setConfig(TextWriter.Configuration.INTERMEDIATE)
//            .setSizeFactor(30f)
            .setLetterSpacing(25f)
            .setText("TODAY TASK")
//            .setText(resources.getString(R.string.str_today_task))
            .setListener(TextWriter.Listener {
                //do stuff after animation is finished
            })
            .startAnimation()

        taskBinding!!.day1Btn.bindTargetView(taskBinding!!.myCardView)
        taskBinding!!.day2Btn.bindTargetView(taskBinding!!.myCardView)
        taskBinding!!.day3Btn.bindTargetView(taskBinding!!.myCardView)
        taskBinding!!.day4Btn.bindTargetView(taskBinding!!.myCardView)
        taskBinding!!.day5Btn.bindTargetView(taskBinding!!.myCardView)
        taskBinding!!.day6Btn.bindTargetView(taskBinding!!.myCardView)
        taskBinding!!.day7Btn.bindTargetView(taskBinding!!.myCardView)

        if (store!!.getInt(Utilll.str_day) < 2 && store!!.getInt(Utilll.str_day1) == 1) {
            taskBinding!!.day1Btn.setBackgroundResource(R.drawable.day_selected)
        } else {
            taskBinding!!.day1Btn.setBackgroundResource(R.drawable.day_normal)
        }

        if (store!!.getInt(Utilll.str_day) < 3 && store!!.getInt(Utilll.str_day2) == 1) {
            taskBinding!!.day2Btn.setBackgroundResource(R.drawable.day_selected)
        } else {
            taskBinding!!.day2Btn.setBackgroundResource(R.drawable.day_normal)
        }

        if (store!!.getInt(Utilll.str_day) < 4 && store!!.getInt(Utilll.str_day3) == 1) {
            taskBinding!!.day3Btn.setBackgroundResource(R.drawable.day_selected)
        } else {
            taskBinding!!.day3Btn.setBackgroundResource(R.drawable.day_normal)
        }

        if (store!!.getInt(Utilll.str_day) < 5 && store!!.getInt(Utilll.str_day4) == 1) {
            taskBinding!!.day4Btn.setBackgroundResource(R.drawable.day_selected)
        } else {
            taskBinding!!.day4Btn.setBackgroundResource(R.drawable.day_normal)
        }

        if (store!!.getInt(Utilll.str_day) < 6 && store!!.getInt(Utilll.str_day5) == 1) {
            taskBinding!!.day5Btn.setBackgroundResource(R.drawable.day_selected)
        } else {
            taskBinding!!.day5Btn.setBackgroundResource(R.drawable.day_normal)
        }

        if (store!!.getInt(Utilll.str_day) < 7 && store!!.getInt(Utilll.str_day6) == 1) {
            taskBinding!!.day6Btn.setBackgroundResource(R.drawable.day_selected)
        } else {
            taskBinding!!.day6Btn.setBackgroundResource(R.drawable.day_normal)
        }

        if (store!!.getInt(Utilll.str_day) < 8 && store!!.getInt(Utilll.str_day7) == 1) {
            taskBinding!!.day7Btn.setBackgroundResource(R.drawable.day_selected)
        } else {
            taskBinding!!.day7Btn.setBackgroundResource(R.drawable.day_normal)
        }

        taskBinding!!.day1Btn.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_day) == 1) {
                if (store!!.getInt(Utilll.str_day1) == 1) {
                    Toast.makeText(
                        requireContext(),
                        "You already collect Coin.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    daily = 1
                    taskBinding!!.day1Btn.startTransform()
                }
            } else {
                Toast.makeText(requireContext(), "No Coin Available.", Toast.LENGTH_SHORT).show()
            }
        }

        taskBinding!!.day2Btn.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_day) == 2) {
                if (store!!.getInt(Utilll.str_day2) == 1) {
                    Toast.makeText(
                        requireContext(),
                        "You already collect Coin.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    daily = 2
                    taskBinding!!.day2Btn.startTransform()
                }
            } else {
                Toast.makeText(requireContext(), "No Coin Available.", Toast.LENGTH_SHORT).show()
            }
        }

        taskBinding!!.day3Btn.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_day) == 3) {
                if (store!!.getInt(Utilll.str_day3) == 1) {
                    Toast.makeText(
                        requireContext(),
                        "You already collect Coin.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    daily = 3
                    taskBinding!!.day3Btn.startTransform()
                }
            } else {
                Toast.makeText(requireContext(), "No Coin Available.", Toast.LENGTH_SHORT).show()
            }
        }

        taskBinding!!.day4Btn.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_day) == 4) {
                if (store!!.getInt(Utilll.str_day4) == 1) {
                    Toast.makeText(
                        requireContext(),
                        "You already collect Coin.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    daily = 4
                    taskBinding!!.day4Btn.startTransform()
                }
            } else {
                Toast.makeText(requireContext(), "No Coin Available.", Toast.LENGTH_SHORT).show()
            }
        }

        taskBinding!!.day5Btn.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_day) == 5) {
                if (store!!.getInt(Utilll.str_day5) == 1) {
                    Toast.makeText(
                        requireContext(),
                        "You already collect Coin.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    daily = 5
                    taskBinding!!.day5Btn.startTransform()
                }
            } else {
                Toast.makeText(requireContext(), "No Coin Available.", Toast.LENGTH_SHORT).show()
            }
        }

        taskBinding!!.day6Btn.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_day) == 6) {
                if (store!!.getInt(Utilll.str_day6) == 1) {
                    Toast.makeText(
                        requireContext(),
                        "You already collect Coin.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    daily = 6
                    taskBinding!!.day6Btn.startTransform()
                }
            } else {
                Toast.makeText(requireContext(), "No Coin Available.", Toast.LENGTH_SHORT).show()
            }
        }

        taskBinding!!.day7Btn.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_day) == 7) {
                if (store!!.getInt(Utilll.str_day7) == 1) {
                    Toast.makeText(
                        requireContext(),
                        "You already collect Coin.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    daily = 7
                    taskBinding!!.day7Btn.startTransform()
                }
            } else {
                Toast.makeText(requireContext(), "No Coin Available.", Toast.LENGTH_SHORT).show()
            }
        }

//        taskBinding!!.Tday1.setOnClickListener {
//            taskBinding!!.day1Btn.startTransform()
//        }

        if (store!!.getInt(Utilll.str_isLogin) == 0) {
            taskBinding!!.registration.visibility = View.VISIBLE
        } else {
            taskBinding!!.registration.visibility = View.GONE
        }

        if (store!!.getInt(Utilll.str_isfirstInvite) == 0) {
            taskBinding!!.inviteBtn.visibility = View.VISIBLE
        } else {
            taskBinding!!.inviteBtn.visibility = View.GONE
        }

        taskBinding!!.registration.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    startActivity(
                        Intent(context, aaLoginActivity::class.java)
                    )
                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }

        taskBinding!!.inviteBtn.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    startActivity(
                        Intent(context, aaInviteEarnActivity::class.java)
                    )
                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }

        taskBinding!!.guaranteed.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_garented) == 0){
                welcomeBonusDialog1()
            }else{
                Toast.makeText(
                    requireContext(),
                    "You already collect Coin.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        taskBinding!!.newUser.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
//                    startActivity(
//                        Intent(context, InviteEarnActivity::class.java)
//                    )
//                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }

        taskBinding!!.btnCheckIn.setOnClickListener {
            play()
            if (store!!.getInt(Utilll.str_checkIn) == 0){
                welcomeBonusDialog()
            }else{
                Toast.makeText(
                    requireContext(),
                    "You already collect Coin.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        taskBinding!!.notificationCheck.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context, {
                    startActivity(
                        Intent(context, aaNotificationActivity::class.java)
                    )
                    Animatoo.animateZoom(requireContext())
                }, "", AppManage.app_mainClickCntSwAd
            )
        }

        taskBinding!!.btnCollect1.setOnClickListener {
            play1()
            when (daily) {
                1 -> {
                    taskBinding!!.day1Btn.finishTransform()
                    taskBinding!!.day1Btn.setBackgroundResource(R.drawable.day_selected)
                    store!!.putInt(Utilll.str_day1, 1)
                }

                2 -> {
                    taskBinding!!.day2Btn.finishTransform()
                    taskBinding!!.day2Btn.setBackgroundResource(R.drawable.day_selected)
                    store!!.putInt(Utilll.str_day2, 1)
                }

                3 -> {
                    taskBinding!!.day3Btn.finishTransform()
                    taskBinding!!.day3Btn.setBackgroundResource(R.drawable.day_selected)
                    store!!.putInt(Utilll.str_day3, 1)
                }

                4 -> {
                    taskBinding!!.day4Btn.finishTransform()
                    taskBinding!!.day4Btn.setBackgroundResource(R.drawable.day_selected)
                    store!!.putInt(Utilll.str_day4, 1)
                }

                5 -> {
                    taskBinding!!.day5Btn.finishTransform()
                    taskBinding!!.day5Btn.setBackgroundResource(R.drawable.day_selected)
                    store!!.putInt(Utilll.str_day5, 1)
                }

                6 -> {
                    taskBinding!!.day6Btn.finishTransform()
                    taskBinding!!.day6Btn.setBackgroundResource(R.drawable.day_selected)
                    store!!.putInt(Utilll.str_day6, 1)
                }

                7 -> {
                    taskBinding!!.day7Btn.finishTransform()
                    taskBinding!!.day7Btn.setBackgroundResource(R.drawable.day_selected)
                    store!!.putInt(Utilll.str_day7, 1)
                }
            }
        }

        Sequent.origin(taskBinding!!.layoutHead).anim(activity, R.anim.overshoot).start()

        taskBinding!!.taskList.adapter = aaTaskAdpater(UtilsFiff.task_iteams!!, requireContext())
        return taskBinding?.root
    }

    override fun onClick(v: View) {}

    fun animateIcon() {
        val loadAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
        Handler().postDelayed({ taskBinding!!.btnCheckIn.startAnimation(loadAnimation) }, 2000L)
        loadAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                animateIcon()
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

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun play1() {
        val vibrator = getContext()?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            )
        } else {
            vibrator!!.vibrate(100)
        }

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.coin1)
        if (UtilsFiff.sound == 1) {
            mediaPlayer!!.start()
        }
    }

    private fun welcomeBonusDialog() {
        UtilsFiff.alertDialog(requireActivity(),
            R.layout.dialog_checkin,
            true,
            object : UtilsFiff.DialogListener {
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onCreated(alertDialog: android.app.AlertDialog?) {
                    (alertDialog!!.findViewById<View>(R.id.btnCLose) as ImageView).setOnClickListener {
                        play()
                        alertDialog.dismiss()
                    }
                    (alertDialog.findViewById<View>(R.id.okay_btn) as RelativeLayout).setOnClickListener {
                        play1()
                        store!!.putInt(Utilll.str_checkIn, 1)
                        db!!.addContact(aaIncome("Check In", "300"))
                        store!!.putInt(
                            Utilll.str_todayCoin,
                            store!!.getInt(Utilll.str_todayCoin) + 300
                        )
                        store!!.putInt(
                            Utilll.str_totalCoin,
                            store!!.getInt(Utilll.str_totalCoin) + 300
                        )
                        alertDialog.dismiss()
                    }
                }
            })
    }

    private fun welcomeBonusDialog1() {
        UtilsFiff.alertDialog(requireActivity(),
            R.layout.dialog_garented,
            true,
            object : UtilsFiff.DialogListener {
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onCreated(alertDialog: android.app.AlertDialog?) {
                    (alertDialog!!.findViewById<View>(R.id.btnCLose) as ImageView).setOnClickListener {
                        play()
                        alertDialog.dismiss()
                    }
                    (alertDialog.findViewById<View>(R.id.okay_btn) as RelativeLayout).setOnClickListener {
                        play1()
                        store!!.putInt(Utilll.str_garented, 1)
                        db!!.addContact(aaIncome("Garented Coin", "30"))
                        store!!.putInt(
                            Utilll.str_todayCoin,
                            store!!.getInt(Utilll.str_todayCoin) + 30
                        )
                        store!!.putInt(
                            Utilll.str_totalCoin,
                            store!!.getInt(Utilll.str_totalCoin) + 30
                        )
                        alertDialog.dismiss()
                    }
                }
            })
    }
}