package com.abc.demo.ads;

import static com.abc.demo.ads.AppManage.customappopen;
import static com.abc.demo.ads.AppManage.mysharedpreferences;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.abc.demo.R;
import com.android.volley.BuildConfig;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ADS_SplashActivity extends AppCompatActivity {

    public static boolean need_internet = false;
    String bytemode = "";
    boolean is_retry;
    boolean is_splash_ad_loaded = false;
    boolean on_sucess = false;
    private Runnable runnable;
    private Handler refreshHandler;
    private AppOpenManager manager;
    public static String akbsvl679056 = "https://thegt.in/AppsManager/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_splash);

    }

    public void ADSinit(final Activity activity, final int cversion, final getDataListner myCallback1) {

        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        View view = getLayoutInflater().inflate(R.layout.retry_layout, null);
        dialog.setContentView(view);
        final TextView retry_buttton = view.findViewById(R.id.retry_buttton);

        final SharedPreferences preferences = activity.getSharedPreferences("ad_pref", 0);
        final SharedPreferences.Editor editor_AD_PREF = preferences.edit();

        need_internet = preferences.getBoolean("need_internet", need_internet);

        if (!isNetworkAvailable() && need_internet) {
            is_retry = false;
            dialog.show();
        }

        mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);

        dialog.dismiss();
        refreshHandler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (isNetworkAvailable()) {
                    is_retry = true;
                    retry_buttton.setText(activity.getString(R.string.retry));
                } else if (need_internet) {
                    dialog.show();
                    is_retry = false;
                    retry_buttton.setText(activity.getString(R.string.connect_internet));
                }
                refreshHandler.postDelayed(this, 1000);
            }
        };

        refreshHandler.postDelayed(runnable, 1000);

        retry_buttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_retry) {
                    if (need_internet) {
                        myCallback1.onReload();
                    } else {
                        myCallback1.onSuccess();
                    }


                } else {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                }
            }
        });


        Calendar calender = Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        String currentDate = df.format(calender.getTime());


        final int addfdsf123;
        String status = mysharedpreferences.getString("firsttime", "true");
        final SharedPreferences.Editor editor = mysharedpreferences.edit();
        if (status.equals("true")) {
            editor.putString("date", currentDate).apply();
            editor.putString("firsttime", "false").apply();
            editor.putString("am_check_user_status", "13421").apply();
            addfdsf123 = 13421;


        } else {
            String date = mysharedpreferences.getString("date", "");
            if (!currentDate.equals(date)) {
                editor.putString("date", currentDate).apply();
                editor.putString("am_check_user_status", "26894").apply();
                addfdsf123 = 26894;
            } else {
                addfdsf123 = 87332;
                editor.putString("am_check_user_status", "87332").apply();
            }
        }

        try {
            bytemode = akbsvl679056 + "v2/get_app.php";

        } catch (Exception e) {
            e.printStackTrace();
        }

        final String sdfsdf;
        if (BuildConfig.DEBUG) {
            sdfsdf = "TRSOFTAG12789I";
        } else {
            sdfsdf = "TRSOFTAG82382I";
        }


        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        StringRequest strRequest = new StringRequest(Request.Method.POST, bytemode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response1) {
                        try {

                            response1 = decryptRes(activity, response1);
                            JSONObject response = new JSONObject(response1);
                            try {
                                boolean status = response.getBoolean("STATUS");
                                if (status == true) {

                                    String need_in = response.getJSONObject("APP_SETTINGS").getString("app_needInternet");
                                    if (need_in.endsWith("1")) {
                                        need_internet = true;
                                    } else {
                                        need_internet = false;
                                    }
                                    editor_AD_PREF.putBoolean("need_internet", need_internet).apply();
                                    editor_AD_PREF.putString("Advertise_List", response.getJSONArray("Advertise_List").toString()).apply();
                                    editor_AD_PREF.putString("MORE_APP_SPLASH", response.getJSONArray("MORE_APP_SPLASH").toString()).apply();
                                    editor_AD_PREF.putString("MORE_APP_EXIT", response.getJSONArray("MORE_APP_EXIT").toString()).apply();

                                    SharedPreferences.Editor editor1 = mysharedpreferences.edit();
                                    editor1.putString("response", response.toString());
                                    editor1.apply();


                                } else {

                                }


                            } catch (Exception e) {
                                if (need_internet) {
                                    dialog.dismiss();
                                    refreshHandler = new Handler();
                                    runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            if (isNetworkAvailable()) {
                                                is_retry = true;
                                                retry_buttton.setText(activity.getString(R.string.retry));
                                            } else {
                                                dialog.show();
                                                is_retry = false;
                                                retry_buttton.setText(activity.getString(R.string.connect_internet));
                                            }
                                            refreshHandler.postDelayed(this, 1000);
                                        }
                                    };
                                } else {
                                    myCallback1.onSuccess();
                                }
                            }

                            AppManage.getInstance(activity).getResponseFromPref(new getDataListner() {
                                @Override
                                public void onSuccess() {
                                    int splash_ad2 = mysharedpreferences.getInt("app_AppOpenAdStatus", 0);
                                    if (splash_ad2 == 1 && isNetworkAvailable()) {
                                        on_sucess = true;
                                        showSplashAd(activity, myCallback1);
                                    } else {
                                        myCallback1.onSuccess();
                                    }
                                }

                                @Override
                                public void onUpdate(String url) {
                                    myCallback1.onUpdate(url);
                                }

                                @Override
                                public void onRedirect(String url) {
                                    myCallback1.onRedirect(url);
                                }

                                @Override
                                public void onReload() {
                                }

                                @Override
                                public void onGetExtradata(JSONObject extraData) {
                                    myCallback1.onGetExtradata(extraData);

                                }
                            }, cversion);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (need_internet) {
                            dialog.dismiss();
                            refreshHandler = new Handler();
                            runnable = new Runnable() {
                                @Override
                                public void run() {
                                    if (isNetworkAvailable()) {
                                        is_retry = true;
                                        retry_buttton.setText(activity.getString(R.string.retry));
                                    } else {
                                        dialog.show();
                                        is_retry = false;
                                        retry_buttton.setText(activity.getString(R.string.connect_internet));
                                    }
                                    refreshHandler.postDelayed(this, 1000);
                                }
                            };
                        } else {
                            myCallback1.onSuccess();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("PHSUGSG6783019KG", activity.getPackageName());
                params.put("AFHJNTGDGD563200K", getKeyHash(activity));
                params.put("DTNHGNH7843DFGHBSA", String.valueOf(addfdsf123));
                params.put("DBMNBXRY4500991G", sdfsdf);
                params.put("NOISREVC", cversion + "");

                return params;
            }
        };

        strRequest.setShouldCache(false);
        requestQueue.add(strRequest);

    }

    public String decryptRes(Activity activity, String data) {
        if (data.contains("APP_SETTINGS")) {
            return data;
        } else {
            try {
                String key = getKeyHash(activity) + activity.getPackageName();
                key = key.substring(0, 16);
                String CIPHER_NAME = "AES/CBC/PKCS5PADDING";
                int CIPHER_KEY_LEN = 16; //128 bits
                if (key.length() < CIPHER_KEY_LEN) {
                    int numPad = CIPHER_KEY_LEN - key.length();

                    for (int i = 0; i < numPad; i++) {
                        key += "0"; //0 pad to len 16 bytes
                    }

                } else if (key.length() > CIPHER_KEY_LEN) {
                    key = key.substring(0, CIPHER_KEY_LEN); //truncate to 16 bytes
                }

                String[] parts = data.split(":");

                IvParameterSpec iv = new IvParameterSpec(Base64.decode(parts[1], Base64.DEFAULT));
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("ISO-8859-1"), "AES");

                Cipher cipher = Cipher.getInstance(CIPHER_NAME);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

                byte[] decodedEncryptedData = Base64.decode(parts[0], Base64.DEFAULT);

                byte[] original = cipher.doFinal(decodedEncryptedData);

                return new String(original);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    private void showSplashAd(Activity activity, final getDataListner myCallback1) {

        if (AppManage.app_adShowStatus == 0) {
            myCallback1.onSuccess();
            return;
        }


        String appSplashAdType = mysharedpreferences.getString("app_splashAdType", "");
        String app_open_id = mysharedpreferences.getString("AppOpenID", "");

        if(customappopen.equals("0")){
            if (appSplashAdType.equals("AppOpen") && !app_open_id.isEmpty()) {
                manager = new AppOpenManager(activity);
                manager.fetchAd(new AppOpenManager.splshADlistner() {
                    @Override
                    public void onSuccess() {
                        is_splash_ad_loaded = true;
                        manager.showAdIfAvailable(new AppOpenManager.splshADlistner() {
                            @Override
                            public void onSuccess() {
                                myCallback1.onSuccess();
                            }

                            @Override
                            public void onError(String error) {
                                myCallback1.onSuccess();
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {
                        myCallback1.onSuccess();

                    }
                });

            } else if (appSplashAdType.equals("Interstitial")) {
                AppManage.getInstance(activity).loadAndShowInterstitialAd(activity, false, new AppManage.MyCallback() {
                    public void callbackCall() {
                        myCallback1.onSuccess();
                    }
                }, "", 0);
            } else {
                myCallback1.onSuccess();
            }
        }else {
            CustomAdModel customAdModel = AppManage.getInstance(activity).getMyCustomAd("AppOpen");
            if (customAdModel != null) {
                try {
                    final CustomAppOpenAds customAppOpenAds = new CustomAppOpenAds(activity, R.style.Theme_AppOpen_Dialog, customAdModel);
                    customAppOpenAds.setCanceledOnTouchOutside(false);
                    customAppOpenAds.setCancelable(false);
                    customAppOpenAds.setOnCloseListener(new CustomAppOpenAds.OnCloseListener() {
                        public void onAdsCloseClick() {
                            customAppOpenAds.dismiss();
                            myCallback1.onSuccess();
                        }

                        public void setOnKeyListener() {
                            customAppOpenAds.dismiss();
                            myCallback1.onSuccess();
                        }
                    });
                    customAppOpenAds.show();
                } catch (Exception e) {
                    e.printStackTrace();
                    myCallback1.onSuccess();
                }
            } else {
                myCallback1.onSuccess();
            }
        }
    }

    public String getKeyHash(Activity activity) {
        PackageInfo info;
        try {
            info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = (Base64.encodeToString(md.digest(), Base64.NO_WRAP));
                return something.replace("+", "*");
            }
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();

        } catch (NoSuchAlgorithmException e) {

        } catch (Exception e) {

        }
        return null;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        refreshHandler.removeCallbacks(runnable);
    }
}