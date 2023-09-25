package com.abc.demo.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.android.volley.BuildConfig;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class AdsAnalysis {
    Context activity;

    public AdsAnalysis(Context context) {
        this.activity = context;
    }

    public void init_adAnalysis() {

        SharedPreferences mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
        if (mysharedpreferences.getInt("app_ad_analysis", 0) == 0) {
            return;
        }
        if (mysharedpreferences.getString("am_check_user_status", "87332").equals("87332")) {
            return;
        }

        Map<String, Map<String, String>> map = new HashMap<>();

        Map<String, String> config1 = new HashMap<>();
        config1.put("REQ_LOAD", "0");
        config1.put("REQ_FAIL", "0");
        config1.put("AD_SHOW", "0");
        config1.put("AD_IMP", "0");
        config1.put("ERROR_FAIL", "0");
        config1.put("ERROR_LOG", "");
        map.put(AppManage.ADMOB, config1);

        Map<String, String> config2 = new HashMap<>();
        config2.put("REQ_LOAD", "0");
        config2.put("REQ_FAIL", "0");
        config2.put("AD_SHOW", "0");
        config2.put("AD_IMP", "0");
        config2.put("ERROR_FAIL", "0");
        config2.put("ERROR_LOG", "");
        map.put(AppManage.FACEBOOK, config2);


        Data data = new Data(map);
        Gson gson = new Gson();
        String json_ad_analysis = gson.toJson(data);
        Log.e("json_ad_analysis", json_ad_analysis);


        SharedPreferences.Editor editor = mysharedpreferences.edit();
        editor.putString("json_ad_analysis", json_ad_analysis);
        editor.commit();

    }

    public void count_adAnalysis(String platform, String action) {
        turnCount_adAnalysis(platform, action, "");
    }

    public void count_adAnalysis(String platform, String action, String error_log) {
        turnCount_adAnalysis(platform, action, error_log);
    }

    public void turnCount_adAnalysis(String platform, String action, String error_log) {

        try {
            SharedPreferences mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
            if (mysharedpreferences.getInt("app_ad_analysis", 0) == 0) {
                return;
            }
            if (mysharedpreferences.getString("am_check_user_status", "87332").equals("87332")) {
                return;
            }
            String json_ad_analysis = mysharedpreferences.getString("json_ad_analysis", "");
            if (json_ad_analysis.isEmpty()) {
                return;
            }


            Gson gson = new Gson();
            Data data = gson.fromJson(json_ad_analysis, Data.class);
            int count_no = Integer.parseInt(data.getMap().get(platform).get(action));
            count_no = count_no + 1;

            Map<String, String> config1 = new HashMap<>();

            if (action.equals("REQ_LOAD")) {
                config1.put("REQ_LOAD", count_no + "");
            } else {
                config1.put("REQ_LOAD", data.getMap().get(platform).get("REQ_LOAD"));
            }

            if (action.equals("REQ_FAIL")) {
                config1.put("REQ_FAIL", count_no + "");
                if (!error_log.isEmpty()) {
                    config1.put("ERROR_LOG", error_log);
                } else {
                    config1.put("ERROR_LOG", data.getMap().get(platform).get("ERROR_LOG"));
                }
            } else {
                config1.put("REQ_FAIL", data.getMap().get(platform).get("REQ_FAIL"));
                config1.put("ERROR_LOG", data.getMap().get(platform).get("ERROR_LOG"));
            }

            if (action.equals("AD_SHOW")) {
                config1.put("AD_SHOW", count_no + "");
            } else {
                config1.put("AD_SHOW", data.getMap().get(platform).get("AD_SHOW"));
            }

            if (action.equals("AD_IMP")) {
                config1.put("AD_IMP", count_no + "");
            } else {
                config1.put("AD_IMP", data.getMap().get(platform).get("AD_IMP"));
            }

            if (action.equals("ERROR_FAIL")) {
                config1.put("ERROR_FAIL", count_no + "");
            } else {
                config1.put("ERROR_FAIL", data.getMap().get(platform).get("ERROR_FAIL"));
            }


            data.getMap().put(platform, config1);
            json_ad_analysis = gson.toJson(data);
            Log.e("json_ad_analysis2", json_ad_analysis);

            SharedPreferences.Editor editor = mysharedpreferences.edit();
            editor.putString("json_ad_analysis", json_ad_analysis);
            editor.commit();


        } catch (Exception e) {

        }

    }

    public void request_adAnalysis(final String activityName) {
        final SharedPreferences mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
        if (mysharedpreferences.getInt("app_ad_analysis", 0) == 0) {
            return;
        }
        final String json_ad_analysis = mysharedpreferences.getString("json_ad_analysis", "");
        if (json_ad_analysis.isEmpty()) {
            return;
        }

        if (mysharedpreferences.getString("am_check_user_status", "87332").equals("87332")) {
            return;
        }

        String activityNames = mysharedpreferences.getString("activityNames", "");
        if (activityNames.contains(activityName)) {
            return;
        } else {
            if (activityNames.isEmpty()) {
                activityNames = activityName;
            } else {
                activityNames = activityNames + "," + activityName;
            }
        }


        final String sdfsdf;
        if (BuildConfig.DEBUG) {
            sdfsdf = "TRSOFTAG12789I";
        } else {
            sdfsdf = "TRSOFTAG82382I";
        }


        final String finalActivityNames = activityNames;
        Thread thread = new Thread() {
            @Override
            public void run() {

                String bytemode = ADS_SplashActivity.akbsvl679056 + "v1/app_analysis_2022.php";
                RequestQueue requestQueue = Volley.newRequestQueue(activity);
                StringRequest strRequest = new StringRequest(Request.Method.POST, bytemode,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response1) {
                                try {
                                    JSONObject response = new JSONObject(response1);
                                    boolean status = response.getBoolean("status");
                                    if (status == true) {
                                        SharedPreferences.Editor editor = mysharedpreferences.edit();
                                        editor.putString("activityNames", finalActivityNames);
                                        editor.commit();

                                        init_adAnalysis();
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("ad_analysis_error", error.getMessage());
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("PHSUGSG6783019KG", activity.getPackageName());
                        params.put("AFHJNTGDGD563200K", getKeyHash((Activity) activity));
                        params.put("DTNHGNH7843DFGHBSA", mysharedpreferences.getString("am_check_user_status", "87332"));
                        params.put("DBMNBXRY4500991G", sdfsdf);
                        params.put("sisylana_da_nosj", json_ad_analysis);
                        params.put("emaNytivitca", activityName);
                        return params;
                    }
                };

                strRequest.setShouldCache(false);
                requestQueue.add(strRequest);

            }
        };

        thread.start();


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

}
