package com.abc.demo.ui

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abc.demo.R
import com.abc.demo.databinding.ActivityLoginBinding
import com.abc.demo.utils.Utilll
import com.abc.demo.utils.UtilsFiff
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.abc.demo.ads.AppManage

class aaLoginActivity : AppCompatActivity() {
    private var loginBinding: ActivityLoginBinding? = null
    private var store: Utilll? = null
    private var context: Context? = null

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var mAuth: FirebaseAuth? = null
    private var mUser: FirebaseUser? = null

    private val RC_SIGN_IN = 1

    private var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding?.root)

        context = this
        store = Utilll.getInstance(this)


        AppManage.getInstance(this).showNativetype(
            loginBinding!!.nativeAd, AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0], "yes", 4)

        FirebaseAuth.getInstance().signOut()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        mAuth = FirebaseAuth.getInstance()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mGoogleSignInClient!!.signOut()

        loginBinding!!.loginBtn.setOnClickListener {
            play()
            val signInIntent = mGoogleSignInClient!!.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        loginBinding!!.btnLoginGuest.setOnClickListener {
            play()
            if (store!!.getString(Utilll.str_language).equals("")) {
                startActivity(
                    Intent(
                        this@aaLoginActivity, LanguagesActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            } else {
                startActivity(
                    Intent(
                        this@aaLoginActivity, aaStartActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.e("ERRORR", e.message!!)
                Snackbar.make(
                    loginBinding!!.root,
                    resources.getString(R.string.text_no_login),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth!!.signInWithCredential(credential).addOnCompleteListener(
            this
        ) { task: Task<AuthResult?> ->
            if (task.isSuccessful) {
                loginComplete()
            } else {
                Snackbar.make(
                    loginBinding!!.root,
                    resources.getString(R.string.text_no_login),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun loginComplete() {
        val deviceid = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        mUser = mAuth!!.currentUser
        if (mUser != null) {
            store!!.putString(Utilll.str_userEmail, mUser!!.email)
            store!!.putString(Utilll.str_userName, mUser!!.displayName)
            store!!.putString(Utilll.str_userImage, mUser!!.photoUrl.toString() + "")
            store!!.putString(Utilll.str_deviceId, deviceid)
            store!!.putInt(Utilll.str_isLogin, 1)

            if (store!!.getString(Utilll.str_language).equals("")) {
                startActivity(
                    Intent(
                        this@aaLoginActivity, LanguagesActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            } else {
                startActivity(
                    Intent(
                        this@aaLoginActivity, aaStartActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            }
        } else {
            Snackbar.make(
                loginBinding!!.root,
                resources.getString(R.string.text_no_login),
                Snackbar.LENGTH_LONG
            ).show()
        }
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