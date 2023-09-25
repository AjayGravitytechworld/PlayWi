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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.abc.demo.R
import com.abc.demo.database.Database
import com.abc.demo.databinding.FraggameBinding
import com.abc.demo.model.aaIncome
import com.abc.demo.ui.aaGameAllActivity
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.abc.demo.ads.AppManage

class GameFrag : Fragment(), View.OnClickListener {
    private var gameBinding: FraggameBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null

    private var mediaPlayer: MediaPlayer? = null
    private var db: Database? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        layoutInflater: LayoutInflater, viewGroup: ViewGroup?, bundle: Bundle?
    ): View? {
        gameBinding = FraggameBinding.inflate(layoutInflater, viewGroup, false)
        context = getContext()

        store = Utilll.getInstance(requireContext())
        db = Database(context)

        AppManage.getInstance(context).showNativetype(
            gameBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

        AppManage.getInstance(context).showNativetype(
            gameBinding!!.nativeAd3,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 1
        )

//        AppManage.getInstance(context).showNative(
//            gameBinding!!.nativeAd,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )
//
//        AppManage.getInstance(context).showNative(
//            gameBinding!!.nativeAd2,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )
//
//        AppManage.getInstance(context).showNative(
//            gameBinding!!.nativeAd3,
//            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
//        )

        gameBinding!!.trendingGame.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                { startActivity(Intent(context, aaGameAllActivity::class.java).putExtra("type" ,4)) },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }

        gameBinding!!.popularGame.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                { startActivity(Intent(context, aaGameAllActivity::class.java).putExtra("type" ,5)) },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }


        gameBinding!!.gamers.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                { startActivity(Intent(context, aaGameAllActivity::class.java).putExtra("type" ,1)) },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }

        gameBinding!!.mostGames.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                { startActivity(Intent(context, aaGameAllActivity::class.java).putExtra("type" ,2)) },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }

        gameBinding!!.adventurGame.setOnClickListener {
            play()
            AppManage.getInstance(context).showInterstitialAd(
                context,
                { startActivity(Intent(context, aaGameAllActivity::class.java).putExtra("type" ,3)) },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
        gameBinding!!.game1.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/plug-it/")
        }
        gameBinding!!.game2.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/magic-bubble-fox/")
        }
        gameBinding!!.game3.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/paint-the-house/")
        }
        gameBinding!!.game4.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/happy-popcorn/")
        }
        gameBinding!!.game5.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/crossy-traffic/")
        }
        gameBinding!!.game6.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/jungle-boy/")
        }
        gameBinding!!.game7.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/desert-champion/")
        }
        gameBinding!!.game8.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/hexa-match/")
        }

        gameBinding!!.MostGame1.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/brickout/")
        }
        gameBinding!!.MostGame2.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/bubblegum-balloon/")
        }
        gameBinding!!.MostGame3.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/burger-time/")
        }
        gameBinding!!.MostGame4.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/cars/")
        }
        gameBinding!!.MostGame5.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/crazy-runner/")
        }
        gameBinding!!.MostGame6.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/creepy-day/")
        }
        gameBinding!!.MostGame7.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/cube-dash/")
        }
        gameBinding!!.MostGame8.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/cube-ninja/")
        }

        gameBinding!!.adGame1.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/floor-jumper-escape/")
        }
        gameBinding!!.adGame2.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/frog-super-bubbles/")
        }
        gameBinding!!.adGame3.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/fruit-snake/")
        }
        gameBinding!!.adGame4.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/fruitslasher/")
        }
        gameBinding!!.adGame5.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/funny-animals-match3/")
        }
        gameBinding!!.adGame6.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/girl-dress-up/")
        }
        gameBinding!!.adGame7.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/going-nuts/")
        }
        gameBinding!!.adGame8.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/gold-miner/")
        }
        gameBinding!!.trendingGame1.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/air-warfare/")
        }
        gameBinding!!.trendingGame2.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/ninja-run/")
        }
        gameBinding!!.trendingGame3.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/playful-kitty/")
        }
        gameBinding!!.trendingGame4.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/pops-billiards/")
        }

        gameBinding!!.popularGame1.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/brickout/")
        }
        gameBinding!!.popularGame2.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/crazy-runner/")
        }
        gameBinding!!.popularGame3.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/stickman-table-tennis/")
        }
        gameBinding!!.popularGame4.setOnClickListener {
            play()
            addCoin()
            db!!.addContact(
                aaIncome(
                    "Playing Game ",
                    "20"
                )
            )
            UtilsFiff.openGame(requireContext(),"https://play107.atmegame.com/games/strike-expert/")
        }

        return gameBinding?.root
    }

    override fun onClick(v: View) {}

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun play(){
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

    private fun addCoin(){
        store!!.putInt(Utilll.str_todayCoin, store!!.getInt(Utilll.str_todayCoin) + 20)
        store!!.putInt(Utilll.str_totalCoin, store!!.getInt(Utilll.str_totalCoin) + 20)

        Toast.makeText(requireContext(), "You Earned 20 Coins", Toast.LENGTH_SHORT)
            .show()
    }
}