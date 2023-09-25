package com.abc.demo.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import com.abc.demo.BuildConfig
import com.abc.demo.R
import com.abc.demo.model.aaGameModel
import com.abc.demo.model.aaGameModel1
import com.abc.demo.model.aaLeaderBoardModel
import com.abc.demo.model.aaTaskModel
import com.abc.demo.model.aaVideoModel
import com.abc.demo.ads.AppManage
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale


class UtilsFiff {
    companion object {
        var myapi = "https://bhano.digitalbazarshopingcart.com/Apps_API/Watch_Video/Video/"
        var applist = "https://bhano.digitalbazarshopingcart.com/Apps_API/Watch_Video/App_List/"
        private var dateListener: DateListener? = null
        private var dialogListener: DialogListener? = null

        var sound: Int? = null

        var game_iteams1: ArrayList<aaGameModel>? = ArrayList()
        var game_iteams2: ArrayList<aaGameModel>? = ArrayList()
        var game_iteams3: ArrayList<aaGameModel>? = ArrayList()
        var game_iteams4: ArrayList<aaGameModel1>? = ArrayList()
        var game_iteams5: ArrayList<aaGameModel1>? = ArrayList()
        var task_iteams: ArrayList<aaTaskModel>? = ArrayList()

        var today_video: ArrayList<aaVideoModel>? = ArrayList()
        var trending_video: ArrayList<aaVideoModel>? = ArrayList()
        var popular_video: ArrayList<aaVideoModel>? = ArrayList()

        var leader1: ArrayList<aaLeaderBoardModel>? = ArrayList()
        var leader2: ArrayList<aaLeaderBoardModel>? = ArrayList()

        fun loadGameData1(context: Context) {
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game1,
                    context.resources.getString(R.string.str_game1),
                    "https://play107.atmegame.com/games/plug-it/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game2,
                    context.resources.getString(R.string.str_game2),
                    "https://play107.atmegame.com/games/magic-bubble-fox/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game3,
                    context.resources.getString(R.string.str_game3),
                    "https://play107.atmegame.com/games/paint-the-house/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game4,
                    context.resources.getString(R.string.str_game4),
                    "https://play107.atmegame.com/games/happy-popcorn/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game5,
                    context.resources.getString(R.string.str_game5),
                    "https://play107.atmegame.com/games/crossy-traffic/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game6,
                    context.resources.getString(R.string.str_game6),
                    "https://play107.atmegame.com/games/jungle-boy/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game7,
                    context.resources.getString(R.string.str_game7),
                    "https://play107.atmegame.com/games/desert-champion/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game8,
                    context.resources.getString(R.string.str_game8),
                    "https://play107.atmegame.com/games/hexa-match/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game9,
                    context.resources.getString(R.string.str_game9),
                    "https://play107.atmegame.com/games/air-hockey/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game10,
                    context.resources.getString(R.string.str_game10),
                    "https://play107.atmegame.com/games/air-warfare/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game11,
                    context.resources.getString(R.string.str_game11),
                    "https://play107.atmegame.com/games/alien-hunter-2/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game12,
                    context.resources.getString(R.string.str_game12),
                    "https://play107.atmegame.com/games/angry-cat-shot/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game13,
                    context.resources.getString(R.string.str_game13),
                    "https://play107.atmegame.com/games/animals-crush-match3/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game14,
                    context.resources.getString(R.string.str_game14),
                    "https://play107.atmegame.com/games/assassin-knight/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game15,
                    context.resources.getString(R.string.str_game15),
                    "https://play107.atmegame.com/games/barn-dash/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game16,
                    context.resources.getString(R.string.str_game16),
                    "https://play107.atmegame.com/games/basketball/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game17,
                    context.resources.getString(R.string.str_game17),
                    "https://play107.atmegame.com/games/billiards/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game18,
                    context.resources.getString(R.string.str_game18),
                    "https://play107.atmegame.com/games/block-pile/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game19,
                    context.resources.getString(R.string.str_game19),
                    "https://play107.atmegame.com/games/block-snake/"
                )
            )
            game_iteams1!!.add(
                aaGameModel(
                    R.drawable.game20,
                    context.resources.getString(R.string.str_game20),
                    "https://play107.atmegame.com/games/breakfast-time/"
                )
            )
        }

        fun loadGameData2(context: Context) {
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame1,
                    context.resources.getString(R.string.str_m_game1),
                    "https://play107.atmegame.com/games/brickout/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame2,
                    context.resources.getString(R.string.str_m_game2),
                    "https://play107.atmegame.com/games/bubblegum-balloon/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame3,
                    context.resources.getString(R.string.str_m_game3),
                    "https://play107.atmegame.com/games/burger-time/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame4,
                    context.resources.getString(R.string.str_m_game4),
                    "https://play107.atmegame.com/games/cars/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame5,
                    context.resources.getString(R.string.str_m_game5),
                    "https://play107.atmegame.com/games/crazy-runner/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame6,
                    context.resources.getString(R.string.str_m_game6),
                    "https://play107.atmegame.com/games/creepy-day/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame7,
                    context.resources.getString(R.string.str_m_game7),
                    "https://play107.atmegame.com/games/cube-dash/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame8,
                    context.resources.getString(R.string.str_m_game8),
                    "https://play107.atmegame.com/games/cube-ninja/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame9,
                    context.resources.getString(R.string.str_m_game9),
                    "https://play107.atmegame.com/games/cyber-slashman/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame10,
                    context.resources.getString(R.string.str_m_game10),
                    "https://play107.atmegame.com/games/cyber-soldier/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame11,
                    context.resources.getString(R.string.str_m_game11),
                    "https://play107.atmegame.com/games/dark-ninja/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame12,
                    context.resources.getString(R.string.str_m_game12),
                    "https://play107.atmegame.com/games/dashers/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame13,
                    context.resources.getString(R.string.str_m_game13),
                    "https://play107.atmegame.com/games/dead-land-adventure-2/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame14,
                    context.resources.getString(R.string.str_m_game14),
                    "https://play107.atmegame.com/games/defence-champion/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame15,
                    context.resources.getString(R.string.str_m_game15),
                    "https://play107.atmegame.com/games/duck-hunter/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame16,
                    context.resources.getString(R.string.str_m_game16),
                    "https://play107.atmegame.com/games/duck-shooter/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame17,
                    context.resources.getString(R.string.str_m_game17),
                    "https://play107.atmegame.com/games/duosometric-jump/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame18,
                    context.resources.getString(R.string.str_m_game18),
                    "https://play107.atmegame.com/games/fire-soldier/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame19,
                    context.resources.getString(R.string.str_m_game19),
                    "https://play107.atmegame.com/games/fishing-frenzy/"
                )
            )
            game_iteams2!!.add(
                aaGameModel(
                    R.drawable.mgame20,
                    context.resources.getString(R.string.str_m_game20),
                    "https://play107.atmegame.com/games/flappy-pig/"
                )
            )
        }

        fun loadGameData3(context: Context) {
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.aaaaa,
                    context.resources.getString(R.string.str_ad_game1),
                    "https://play107.atmegame.com/games/floor-jumper-escape/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.aaaaa,
                    context.resources.getString(R.string.str_ad_game2),
                    "https://play107.atmegame.com/games/frog-super-bubbles/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.vd,
                    context.resources.getString(R.string.str_ad_game3),
                    "https://play107.atmegame.com/games/fruit-snake/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.aaa,
                    context.resources.getString(R.string.str_ad_game4),
                    "https://play107.atmegame.com/games/fruitslasher/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.ascf5,
                    context.resources.getString(R.string.str_ad_game5),
                    "https://play107.atmegame.com/games/funny-animals-match3/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.cf47,
                    context.resources.getString(R.string.str_ad_game6),
                    "https://play107.atmegame.com/games/girl-dress-up/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.vd54,
                    context.resources.getString(R.string.str_ad_game7),
                    "https://play107.atmegame.com/games/going-nuts/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.sc7,
                    context.resources.getString(R.string.str_ad_game8),
                    "https://play107.atmegame.com/games/gold-miner/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.xcd4,
                    context.resources.getString(R.string.str_ad_game9),
                    "https://play107.atmegame.com/games/gold-miner-jack/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.dvd7,
                    context.resources.getString(R.string.str_ad_game10),
                    "https://play107.atmegame.com/games/great-air-battles/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.ad_game11,
                    context.resources.getString(R.string.str_ad_game11),
                    "https://play107.atmegame.com/games/halloween-bubble-shooter/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.ad_game12,
                    context.resources.getString(R.string.str_ad_game12),
                    "https://play107.atmegame.com/games/handless-millionaire/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.ad_game13,
                    context.resources.getString(R.string.str_ad_game13),
                    "https://play107.atmegame.com/games/happy-halloween/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.vfdg7,
                    context.resources.getString(R.string.str_ad_game14),
                    "https://play107.atmegame.com/games/hexa-puzzle/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.ad_game15,
                    context.resources.getString(R.string.str_ad_game15),
                    "https://play107.atmegame.com/games/joee-adventure/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.zxc,
                    context.resources.getString(R.string.str_ad_game16),
                    "https://play107.atmegame.com/games/jump-jump/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.dcf,
                    context.resources.getString(R.string.str_ad_game17),
                    "https://play107.atmegame.com/games/jumpers/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.sd,
                    context.resources.getString(R.string.str_ad_game18),
                    "https://play107.atmegame.com/games/jungle-treasure/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.a65,
                    context.resources.getString(R.string.str_ad_game19),
                    "https://play107.atmegame.com/games/knife-vs-zombies/"
                )
            )
            game_iteams3!!.add(
                aaGameModel(
                    R.drawable.szdc,
                    context.resources.getString(R.string.str_ad_game20),
                    "https://play107.atmegame.com/games/mad-scientist/"
                )
            )
        }

        fun loadGameData4(context: Context) {
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game1,
                    "https://play107.atmegame.com/games/mad-shark/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game2,
                    "https://play107.atmegame.com/games/monster-rush/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game3,
                    "https://play107.atmegame.com/games/monster-truck-football/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game4,
                    "https://play107.atmegame.com/games/mummy-candies/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game5,
                    "https://play107.atmegame.com/games/ninja-run/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game6,
                    "https://play107.atmegame.com/games/penalty-challenge/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game7,
                    "https://play107.atmegame.com/games/piggybank-adventure/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game8,
                    "https://play107.atmegame.com/games/playful-kitty/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game9,
                    "https://play107.atmegame.com/games/plumber/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game10,
                    "https://play107.atmegame.com/games/pool-8-ball/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game11,
                    "https://play107.atmegame.com/games/pops-billiards/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game12,
                    "https://play107.atmegame.com/games/popstar-dress-up/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game13,
                    "https://play107.atmegame.com/games/ranger-vs-zombies/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game14,
                    "https://play107.atmegame.com/games/road-racer/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game15,
                    "https://play107.atmegame.com/games/rocky-jetpack/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game16,
                    "https://play107.atmegame.com/games/santa-run/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game17,
                    "https://play107.atmegame.com/games/scary-run/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game18,
                    "https://play107.atmegame.com/games/shoot-robbers/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game19,
                    "https://play107.atmegame.com/games/space-purge/"
                )
            )
            game_iteams4!!.add(
                aaGameModel1(
                    R.drawable.tr_game20,
                    "https://play107.atmegame.com/games/speed-racer/"
                )
            )
        }

        fun loadGameData5(context: Context) {
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game1,
                    "https://play107.atmegame.com/games/speedy-driving/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game2,
                    "https://play107.atmegame.com/games/stack-jump/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game3,
                    "https://play107.atmegame.com/games/stick-monkey/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game4,
                    "https://play107.atmegame.com/games/stick-panda/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game5,
                    "https://play107.atmegame.com/games/stickman-table-tennis/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game6,
                    "https://play107.atmegame.com/games/strike-expert/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game8,
                    "https://play107.atmegame.com/games/supe-pon-goal/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game8,
                    "https://play107.atmegame.com/games/super-cowboy-run/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game9,
                    "https://play107.atmegame.com/games/swat-vs-zombies/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game10,
                    "https://play107.atmegame.com/games/tank-defender/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game11,
                    "https://play107.atmegame.com/games/tank-wars/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game12,
                    "https://play107.atmegame.com/games/the-bandit-hunter/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game13,
                    "https://play107.atmegame.com/games/traffic/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game14,
                    "https://play107.atmegame.com/games/traffic-command/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game15,
                    "https://play107.atmegame.com/games/truck-parking-pro/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game16,
                    "https://play107.atmegame.com/games/viking-attack/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game17,
                    "https://play107.atmegame.com/games/viking-escape/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game18,
                    "https://play107.atmegame.com/games/vikings-vs-skeletons/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game19,
                    "https://play107.atmegame.com/games/virus-attack/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game20,
                    "https://play107.atmegame.com/games/wothan-escape/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game21,
                    "https://play107.atmegame.com/games/zombie-buster/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game22,
                    "https://play107.atmegame.com/games/zombie-shooter/"
                )
            )
            game_iteams5!!.add(
                aaGameModel1(
                    R.drawable.po_game23,
                    "https://play107.atmegame.com/games/zoo-run/"
                )
            )
        }

        fun openGame(context: Context, gamelink: String) {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(gamelink))
        }

        fun customAdCLick(context: Context) {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(AppManage.Qureka))
        }

        fun shareApp(context: Context) {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    context.resources.getString(R.string.app_name)
                )
//                var shareMessage = context.resources.getString(R.string.str_recomondet)
                var shareMessage = "Download App Watch Video Daily Earn Money in Google Play Store\n\n" +
                        "Earn Real Money 100% Real with Play Game and Earn from this Awesome App.\n\n" +
                        "Enter Referral Code : B18AA1\n\n" +
                        "Install and Get 100000 Coins & Real Cash Money in your Wallet.\n\n" +
                        "Install and Earn \n" +
                        "\n"
                shareMessage =
                    """
                ${shareMessage + " https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID}
                               
                """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
//                shareIntent.type = "image/png"
//                val uri =
//                    Uri.parse("android.resource://your package name/" + R.drawable.banner)
//                shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                context.startActivity(
                    Intent.createChooser(
                        shareIntent,
                        context.resources.getString(R.string.str_choose_one)
                    )
                )
            } catch (e: Exception) {
            }
        }

        fun rateApp(context: Context) {
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + context.packageName)
                    )
                )
            } catch (e: ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$context.packageName")
                    )
                )
            }
        }

        fun task(context: Context, gamelink: String) {
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + gamelink)
                    )
                )
            } catch (e: ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(gamelink)
                    )
                )
            }
        }

        fun setLanguage(context: Context, str: String?) {
            val locale = Locale(str)
            Locale.setDefault(locale)
            val resources = context.resources
            val configuration = Configuration(resources.configuration)
            configuration.locale = locale
            context.createConfigurationContext(configuration)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }

        fun getCurrentDateAndTime(): String {
            val c: Date = Calendar.getInstance().time
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            return simpleDateFormat.format(c)
        }

        fun datePicker1(
            activity: Activity?,
            z: Boolean,
            dateListener2: DateListener
        ) {

            dateListener = dateListener2
            hideKeyboard(activity!!)
            val instance = Calendar.getInstance()
            DatePickerDialog(
                activity,
                OnDateSetListener { _, i, i2, i3 ->
                    instance[1] = i
                    instance[2] = i2
                    instance[5] = i3
                    if (z) {
                        val gregorianCalendar = GregorianCalendar(i, i2, i3)
                        val gregorianCalendar2 = GregorianCalendar()
                        gregorianCalendar2.add(1, 1)
                        if (gregorianCalendar2.before(gregorianCalendar)) {
                            Toast.makeText(activity, "Not Valid Date", Toast.LENGTH_SHORT).show()
                            return@OnDateSetListener
                        }
                        dateListener2.onDateSet(
                            SimpleDateFormat("dd-MM-yyyy", Locale.US).format(
                                instance.time
                            )
                        )
                        return@OnDateSetListener
                    }
                    dateListener2.onDateSet(
                        SimpleDateFormat(
                            "dd-MM-yyyy",
                            Locale.US
                        ).format(instance.time)
                    )
                }, instance[1], instance[2], instance[5]
            ).show()
        }

        private fun hideKeyboard(activity: Activity) {
            @SuppressLint("WrongConstant") val inputMethodManager =
                activity.getSystemService("input_method") as InputMethodManager
            var currentFocus = activity.currentFocus
            if (currentFocus == null) {
                currentFocus = View(activity)
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }

        fun alertDialog(activity: Activity, i: Int, z: Boolean, dialogListener2: DialogListener) {
            dialogListener = dialogListener2
            val builder = AlertDialog.Builder(activity)
            val viewGroup: ViewGroup? = null
            builder.setView(activity.layoutInflater.inflate(i, null as ViewGroup?))
            val create = builder.create()
            create.window!!.attributes.windowAnimations = R.style.DialogTheme //style id
            create.setCancelable(z)
            create.window!!.setBackgroundDrawable(ColorDrawable(0))
            create.show()
            dialogListener2.onCreated(create)
        }

        fun loadLeaderBoard(context: Context) {
            leader1!!.add(
                aaLeaderBoardModel(
                    1,
                    "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhXbyVBn85RlznGFvXFlDbpmctao3TRW8fCFFmIIvYHC89owJD08eGHWH7BWCEMr5byrrH7ktbe01Q8VA3ps42XIH6rgiAvi-gkJWqIB4mf0OCUrBoT3J6pxIKkcSzhXiMiV5084ij4MtWZRX8NtjdeGgmgK_yILNWPNjU98HKcD5-tkV-VkEEkYvOF/s950/broken-profile-pic-for-whatsApp.png",
                    "Binod", R.drawable.f_himdi, 55000
                )
            )
            leader1!!.add(
                aaLeaderBoardModel(
                    2,
                    "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhOxolStUQ4PtJnrNFnBrNyU_32pN36QbFmqK_F3iV9jOEcA1Tv0so58FDJ9PZBK5rYpLQbnJRS0v9_FIFudwTXLTa3qpjGDJiXheB7nAG-BUYRgDxmd8g7-RL6Z62vZ3rWz_dV0H__oD6FBZLXpvjXXUCIR5MqwH1rvTj9o2a0gXlmFKHpfbIGwtAJ/s636/whatsapp-profile-pic-girl.png",
                    "Sandes", R.drawable.f_korean, 51000
                )
            )
            leader1!!.add(
                aaLeaderBoardModel(
                    3,
                    "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhFeRRhfCwNLkFkR91rzTAmWPwj7f_BuA257e7A5mex83nqoy2junwycyXTl_dirqGx7tQegc_-Y1R-118W1nR5fbBwAL41_OvjQaILcOfgLYlZ7igiJTu5kDk_LEDTfsI7oh-9bT_Gh6uxCQGIGDrGya1CSNl1fAJg1t0hKgdTjCPvaPDCFbWqIDj2/s660/whatsapp-profile-pic-love-couple.png",
                    "Gatar", R.drawable.f_indonesia, 48000
                )
            )
            leader1!!.add(
                aaLeaderBoardModel(
                    4,
                    "Lindi",
                    "Lindi", R.drawable.f_portuguese, 47500
                )
            )
            leader1!!.add(
                aaLeaderBoardModel(
                    5,
                    "Mimi",
                    "Mimi", R.drawable.f_korean, 47320
                )
            )
            leader1!!.add(
                aaLeaderBoardModel(
                    6,
                    "John",
                    "John", R.drawable.f_korean, 47120
                )
            )
            leader1!!.add(
                aaLeaderBoardModel(
                    7,
                    "Kim",
                    "Kim", R.drawable.f_korean, 45020
                )
            )
            leader1!!.add(
                aaLeaderBoardModel(
                    8,
                    "",
                    "Symond", R.drawable.f_portuguese, 40202
                )
            )
            leader1!!.add(
                aaLeaderBoardModel(
                    9,
                    "",
                    "Symond", R.drawable.f_spanish, 38250
                )
            )
            leader1!!.add(
                aaLeaderBoardModel(
                    10,
                    "Powel",
                    "Powel", R.drawable.f_korean, 38100
                )
            )
            loadLeaderBoard2(context)
        }

        fun loadLeaderBoard2(context: Context) {
            leader2!!.add(
                aaLeaderBoardModel(
                    1,
                    "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhhcZkcOy_PdgMzm20OpHdvdxIojzmFEwo1p11NFzZE0ZQzghGPhHY1CGq_z1KrFTuqCcIpPZeDMQ90AlA9NMCAerY8lNK4skg3bxlUUg1NOwEG9YNxL4C-ITlzgUIDwptK2UhJ2NDxO9W2mw2Nx69JQjHB6CXVYB4SBqbWMCS-AuGiREXtB-zYV5a9/s562/whatsapp-profile-pic.png",
                    "Andraw", R.drawable.f_eng, 555090
                )
            )
            leader2!!.add(
                aaLeaderBoardModel(
                    2,
                    "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiQzhejLD3MLnP97MdjJwNKNHpM1edw41VlZK5oIgW0nJk3j3QvOutd_qkX7b4pEy7AylmRJxZZeuEGFnVsULynNovlhYcABgG6SZGaeuRgSY5Ufa9Zb7DprC_yLPFuOx0--VEdtMz4CNQenzW8ShemrPnkBolpmzxLUfGo-ItJcE0JndL7n294ZJUf/s924/whatsapp-profile-pic-%20free-download.png",
                    "Salman", R.drawable.f_eng, 400050
                )
            )
            leader2!!.add(
                aaLeaderBoardModel(
                    3,
                    "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEig-PbbRMbqIo8W_6yfIHbDJGfa_lYoyY9OHyB0RiNj7i5I5aaWbYFhDtOMW0rAgSWE0allLFf448rx-zGD58eSdZpymyJPHXs5InO6tbcqOjxUcOHIepuJky0KzlIHovIfXYWJX8R6DCfkxEMa1E3L4R4fHdYfOQKlylzxPIXVbksCB3EqErNuL-h1/s953/whatsapp-profile-picture-download.png",
                    "Samer", R.drawable.f_himdi, 309852
                )
            )
            leader2!!.add(
                aaLeaderBoardModel(
                    4,
                    "Aslam",
                    "Aslam", R.drawable.f_spanish, 208730
                )
            )
            leader2!!.add(
                aaLeaderBoardModel(
                    5,
                    "Smith",
                    "Smith", R.drawable.f_indonesia, 118102
                )
            )
            leader2!!.add(
                aaLeaderBoardModel(
                    6,
                    "Sapna",
                    "Sapna", R.drawable.f_himdi, 76200
                )
            )
            leader2!!.add(
                aaLeaderBoardModel(
                    7,
                    "Candy",
                    "Candy", R.drawable.f_spanish, 70100
                )
            )
            leader2!!.add(
                aaLeaderBoardModel(
                    8,
                    "Petar",
                    "Petar", R.drawable.f_portuguese, 69900
                )
            )
            leader2!!.add(
                aaLeaderBoardModel(
                    9,
                    "Tom",
                    "Tom", R.drawable.f_indonesia, 65550
                )
            )
            leader2!!.add(
                aaLeaderBoardModel(
                    10,
                    "Jack",
                    "Jack", R.drawable.f_korean, 61200
                )
            )
        }
    }

    interface DateListener {
        fun onDateSet(str: String?)
    }

    interface DialogListener {
        fun onCreated(alertDialog: AlertDialog?)
    }
}
//https://dash.applovin.com/documentation/mediation/android/ad-formats/rewarded-ads
//https://github.com/CymChad/BaseRecyclerViewAdapterHelper/tree/master
//view-source:https://play.google.com/store/apps/details?id=com.pixelart.colorbynumber.freecoloringbook.pixel.art.number
//https://bhano.digitalbazarshopingcart.com/Apps_API/Apps_Hub/Bank_Balance_Check/
//https://medium.com/@bhoomivaghasiya/use-jetpack-compose-in-your-existing-project-6f12631c8018


//https://www.sellmyapp.com/
//https://codecanyon.net/
//https://www.fliptopia.com/
//https://www.codester.com/categories/44/app-source-code
//https://flippa.com/apps