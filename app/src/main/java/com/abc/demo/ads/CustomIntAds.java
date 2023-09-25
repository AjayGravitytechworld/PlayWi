package com.abc.demo.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import com.abc.demo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class CustomIntAds extends Dialog {
    private LinearLayout LLTop;
    public Context mContext;
    public Activity mCoasantext;
    public OnCloseListener listener_positive;
    private ImageView ad_media_view;
    private RelativeLayout int_bg;
    private TextView txt_title;
    private TextView txt_body;
    private TextView txt_rate;
    private TextView txt_download;
    TextView btn_call_to_action;
    CustomAdModel customAdModel;

    public CustomIntAds(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomIntAds(@NonNull Context context, CustomAdModel customAdModel) {
        super(context);
        this.mContext = context;
        this.customAdModel = customAdModel;
    }

    public CustomIntAds(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomIntAds(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public interface OnCloseListener {
        void onAdsCloseClick();

        void setOnKeyListener();
    }


    public CustomIntAds setOnCloseListener(OnCloseListener onCloseListener) {
        this.listener_positive = onCloseListener;
        Log.e("BABU", "Batli nani");
        return this;
    }

    @SuppressLint("WrongConstant")
    public Point screen_size_get(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(point);
        return point;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_int1);

        AppManage.standard = 1;

        this.setCancelable(false);

        this.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.e("BABU", "BHAI");
            }
        });

        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Log.e("BABU", "Batli");

                    OnCloseListener onCloseListener = listener_positive;
                    if (AppManage.backbutton == 1) {
                        if (onCloseListener != null) {
                            onCloseListener.onAdsCloseClick();
                        }
                        String action_str = customAdModel.getApp_packageName();
                        if (action_str.contains("http")) {
                            Uri marketUrias = Uri.parse(action_str);
                            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                            builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.white));
                            CustomTabsIntent customTabsIntent = builder.build();
                            customTabsIntent.launchUrl(mContext, marketUrias);
                        } else {
                            onCloseListener.onAdsCloseClick();
                            mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                        }
                    } else {
                        onCloseListener.onAdsCloseClick();
                    }
//                                finish();
//                                dialog.dismiss();
                }
                return false;
            }
        });

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = screen_size_get(getContext()).x;
        attributes.height = screen_size_get(getContext()).y;
        getWindow().setAttributes(attributes);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        if (customAdModel != null) {
            try {
                ad_media_view = findViewById(R.id.native_ad_media);
                txt_title = findViewById(R.id.native_ad_title);
                txt_body = findViewById(R.id.native_ad_social_context);
                txt_rate = findViewById(R.id.txt_rate);
                txt_download = findViewById(R.id.txt_download);
                btn_call_to_action = findViewById(R.id.native_ad_call_to_action);

                SharedPreferences mysharedpreferences = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE);
                try {
                    String appNativeButtonColor = mysharedpreferences.getString("appAdsButtonColor", "");
                    String appNativeTextColor = mysharedpreferences.getString("appAdsButtonTextColor", "");

                    if (!appNativeButtonColor.isEmpty()) {
                        btn_call_to_action.setBackgroundColor(Color.parseColor(appNativeButtonColor));
                    }
                    if (!appNativeTextColor.isEmpty()) {
                        btn_call_to_action.setTextColor(Color.parseColor(appNativeTextColor));
                    }
                } catch (Exception ignored) {

                }

                int_bg = findViewById(R.id.int_bg);

                Glide.with(mContext).load(customAdModel.getApp_banner()).diskCacheStrategy(DiskCacheStrategy.ALL).into(ad_media_view);

                ad_media_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnCloseListener onCloseListener = listener_positive;
                        if (onCloseListener != null) {
                            onCloseListener.onAdsCloseClick();
                        }
                        String action_str = customAdModel.getApp_packageName();
                        if (action_str.contains("http")) {
                            Uri marketUri = Uri.parse(action_str);
                            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                            builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.white));
                            CustomTabsIntent customTabsIntent = builder.build();
                            customTabsIntent.launchUrl(mContext, marketUri);
                        } else {
                            mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                        }
                    }
                });

                txt_title.setText(customAdModel.getApp_name());
                txt_body.setText(customAdModel.getApp_shortDecription());
                txt_rate.setText(customAdModel.getApp_rating());
                txt_download.setText(customAdModel.getApp_download());
                btn_call_to_action.setText(customAdModel.getApp_buttonName());

                findViewById(R.id.native_ad_icon).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnCloseListener onCloseListener = listener_positive;
                        if (onCloseListener != null) {
                            onCloseListener.onAdsCloseClick();
                        }
                        String action_str = customAdModel.getApp_packageName();
                        if (action_str.contains("http")) {
                            Uri marketUri = Uri.parse(action_str);

                            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                            builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.white));
                            CustomTabsIntent customTabsIntent = builder.build();
                            customTabsIntent.launchUrl(mContext, marketUri);

//                            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
//                            marketIntent.setPackage("com.android.chrome");
//                            mContext.startActivity(marketIntent);
                        } else {
                            mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                        }
                    }
                });

                btn_call_to_action.setOnClickListener(view -> {
                    OnCloseListener onCloseListener = listener_positive;
                    if (onCloseListener != null) {
                        onCloseListener.onAdsCloseClick();
                    }
                    String action_str = customAdModel.getApp_packageName();
                    if (action_str.contains("http")) {
                        Uri marketUri = Uri.parse(action_str);

                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.white));
                        CustomTabsIntent customTabsIntent = builder.build();
                        customTabsIntent.launchUrl(mContext, marketUri);

//                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
//                        marketIntent.setPackage("com.android.chrome");
//                        mContext.startActivity(marketIntent);
                    } else {
                        mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                    }
                });

                findViewById(R.id.ImgClose).setOnClickListener(view -> {
                    OnCloseListener onCloseListener = listener_positive;
                    if (AppManage.backbutton == 1) {
                        if (onCloseListener != null) {
                            onCloseListener.onAdsCloseClick();
                        }
                        String action_str = customAdModel.getApp_packageName();
                        if (action_str.contains("http")) {
                            Uri marketUrias = Uri.parse(action_str);
                            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                            builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.white));
                            CustomTabsIntent customTabsIntent = builder.build();
                            customTabsIntent.launchUrl(mContext, marketUrias);
                        } else {
                            onCloseListener.onAdsCloseClick();
                            mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                        }

//                            if (action_str.contains("http")) {
//                                Uri marketUri = Uri.parse(action_str);
//                                Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
//                                marketIntent.setPackage("com.android.chrome");
//                                onCloseListener.onAdsCloseClick();
//                                mContext.startActivity(marketIntent);
//                            } else {
//                                onCloseListener.onAdsCloseClick();
//                                mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
//                            }
                    } else {
                        onCloseListener.onAdsCloseClick();
                    }
                });

                AppManage.count_custIntAd++;
            } catch (Exception e) {
                OnCloseListener onCloseListener = listener_positive;
                if (onCloseListener != null) {
                    onCloseListener.onAdsCloseClick();
                }
            }
        } else {
//            OnCloseListener onCloseListener = listener_positive;
//            if (onCloseListener != null) {
//                onCloseListener.onAdsCloseClick();
//            }
        }

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Log.e("logggg", "hyyyy1111111y");
    }
}