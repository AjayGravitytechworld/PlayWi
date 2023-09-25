package com.abc.demo.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.abc.demo.databinding.ActivityMoreOptionBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.abc.demo.ads.AppManage

class aaMoreOptionActivity : AppCompatActivity() {

    var moreOptionBinding: ActivityMoreOptionBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moreOptionBinding = ActivityMoreOptionBinding.inflate(layoutInflater)
        setContentView(moreOptionBinding?.root)

        context = this
        store = Utilll.getInstance(this)


        if(AppManage.quiz == 1){
            moreOptionBinding!!.quizBtn.visibility = View.VISIBLE
        }else{
            moreOptionBinding!!.quizBtn.visibility = View.GONE
        }

        AppManage.getInstance(this).showNative(
            moreOptionBinding!!.nativeAd,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
        )

        AppManage.getInstance(this).showNativeSmall(
            moreOptionBinding!!.nativeAd1,
            AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes"
        )

        moreOptionBinding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

//        moreOptionBinding!!.SpinBtn.setOnClickListener {
//            AppManage.getInstance(this@MoreOptionActivity).showInterstitialAd(
//                this@MoreOptionActivity,
//                {
//
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
//        }
//
//        moreOptionBinding!!.scratchBtn.setOnClickListener {
//            AppManage.getInstance(this@MoreOptionActivity).showInterstitialAd(
//                this@MoreOptionActivity,
//                {
//
//                },
//                "",
//                AppManage.app_mainClickCntSwAd
//            )
//        }
        moreOptionBinding!!.slotBtn.setOnClickListener {
            AppManage.getInstance(this@aaMoreOptionActivity).showInterstitialAd(
                this@aaMoreOptionActivity,
                {

                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
        moreOptionBinding!!.quizBtn.setOnClickListener {
            UtilsFiff.customAdCLick(this)
        }
        moreOptionBinding!!.giftBtn.setOnClickListener {
            AppManage.getInstance(this@aaMoreOptionActivity).showInterstitialAd(
                this@aaMoreOptionActivity,
                {

                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
        moreOptionBinding!!.walletBtn.setOnClickListener {
            AppManage.getInstance(this@aaMoreOptionActivity).showInterstitialAd(
                this@aaMoreOptionActivity,
                {

                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
        moreOptionBinding!!.inviteBtn.setOnClickListener {
            AppManage.getInstance(this@aaMoreOptionActivity).showInterstitialAd(
                this@aaMoreOptionActivity,
                {

                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
        moreOptionBinding!!.rateBtn.setOnClickListener {
            AppManage.getInstance(this@aaMoreOptionActivity).showInterstitialAd(
                this@aaMoreOptionActivity,
                {

                },
                "",
                AppManage.app_mainClickCntSwAd
            )
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        AppManage.getInstance(context).showInterstitialAd(
            context,
            { onBackPressedDispatcher.onBackPressed() },
            "",
            AppManage.app_innerClickCntSwAd
        )
    }
}