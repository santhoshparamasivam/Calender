package com.vidhai.calender;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    WebView wv1;
    String url = "http://www.tamildailycalendar.com";
    // appid   ca-app-pub-3643602219143275~4684936349
    InterstitialAd mInterstitialAd;
    private AdView mAdView;
    static final int DATE_DIALOG_ID = 0;
    private RewardedVideoAd mRewardedVideoAd;
    Calendar cal;
    private Button btnFullscreenAd, btnShowRewardedVideoAd;
    private int mDay;
    private int mMonth;
    private int mYear;
    private String sMonth;
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){

        public void onDateSet(DatePicker datePicker, int n, int n2, int n3) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            MainActivity.access$0(MainActivity.this, n);
            MainActivity.access$1(MainActivity.this, n2);
            MainActivity tamilDailyCalendarActivity = MainActivity.this;
            Object[] arrobject = new Object[]{1 + MainActivity.this.mMonth};
            MainActivity.access$3(MainActivity.this, String.format((String)"%02d", (Object[])arrobject));
            MainActivity.access$4(MainActivity.this, n3);
            MainActivity.this.wv1.loadData("<IMG HEIGHT=\"" + displayMetrics.heightPixels + "px\"" + " WIDTH=\"" + displayMetrics.widthPixels + "px\"" + " SRC=\"http://www.tamildailycalendar.com/" + MainActivity.this.mYear + "/" + MainActivity.this.mDay + MainActivity.this.sMonth + MainActivity.this.mYear + ".jpg\" />", "text/html", "UTF-8");
        }
    };

    private static void access$4(MainActivity mainActivity, int n3) {
        mainActivity.mDay = n3;
    }
    private static void access$3(MainActivity mainActivity, String format) {
        mainActivity.sMonth = format;
    }
    private static void access$1(MainActivity mainActivity, int n2) {
        mainActivity.mMonth = n2;
    }

    private static void access$0(MainActivity mainActivity, int n) {
        mainActivity.mYear = n;
    }
    Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     getWindow().requestFeature(2);
        setContentView(R.layout.activity_main);
//        mAdView = (AdView) findViewById(R.id.adView);
        wv1 = (WebView) findViewById(R.id.webview);
        DisplayMetrics displayMetrics = new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
       cal = Calendar.getInstance();
       mYear = cal.get(1);
        mMonth = cal.get(2);
        Object[] arrobject = new Object[]{1 + mMonth};
       sMonth = String.format((String)"%02d", (Object[])arrobject);
        mDay = cal.get(5);
        MobileAds.initialize(this,("ca-app-pub-3643602219143275~4684936349"));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(("ca-app-pub-3643602219143275/4695888099"));

        newads();




        wv1.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int progress) {
                activity.setProgress(progress * 100);
                if(progress == 100)
                    activity.setTitle("Tamil Daily Calender");
            }
        });
        this.wv1.setWebViewClient(new WebViewClient(){
            private final Activity val$activity;
            {
                this.val$activity = activity;
            }

            public void onReceivedError(WebView webView, int n, String string2, String string3) {
//                Toast.makeText((Context)this.val$activity, (CharSequence)("Oh, no! " + string2), (int)0).show();
            }
        });


//        wv1.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageFinished(WebView view, String url)
//            {
//                wv1.loadUrl("javascript:(function() { " +
//                        "document.getElementById('footer').style.display='none'; " +
//                        "document.getElementById('header').style.display='none'; " +
//                        "document.getElementsByClassName('logo')[0].style.display='none'; " +
//                        "})()");
//                wv1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//                wv1.setVerticalScrollBarEnabled(false);
//                wv1.setHorizontalScrollBarEnabled(false);
//            }
//        });
//        wv1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return (event.getAction() == MotionEvent.ACTION_MOVE);
//            }
//        });
        wv1.getSettings().setBuiltInZoomControls(true);
        wv1.loadData("<IMG HEIGHT=\"" + displayMetrics.heightPixels + "px\"" + " WIDTH=\"" + displayMetrics.widthPixels + "px\"" + " SRC=\"http://www.tamildailycalendar.com/" +mYear + "/" + mDay + sMonth + mYear + ".jpg\" />", "text/html", "UTF-8");


//        mInterstitialAd.setAdUnitId(("ca-app-pub-3940256099942544/1033173712"));




//        RewardVideo();
//        btnFullscreenAd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        btnShowRewardedVideoAd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


    }

    private void newads() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                Log.e("Thread","Working");
                Intersitial();
                Admethoad();

                handler.postDelayed(this, 3000);
            }
        };
        runnable.run();
    }

    private void Admethoad() {


            Log.e("Admethoad","Working");
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
//            mAdView.setAdListener(new AdListener() {
//                @Override
//                public void onAdLoaded() {
//                    Toast.makeText(activity, "Ad Show Success ", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onAdClosed() {
//                    Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onAdFailedToLoad(int errorCode) {
//                    Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onAdLeftApplication() {
//                    Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onAdOpened() {
//                    super.onAdOpened();
//                }
//            });
//
//            mAdView.loadAd(adRequest);
            Intersitial();


    }

    protected Dialog onCreateDialog(int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0:
        }
        return new DatePickerDialog((Context)this, this.mDateSetListener, this.mYear, this.mMonth, this.mDay);
    }
    private void RewardVideo() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {

            @Override
            public void onRewarded(RewardItem rewardItem) {
                Toast.makeText(MainActivity.this, "onRewarded! currency: " + rewardItem.getType() + "  amount: " +
                        rewardItem.getAmount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                Toast.makeText(MainActivity.this, "onRewardedVideoAdLeftApplication",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdClosed() {
                Toast.makeText(MainActivity.this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int errorCode) {
                Toast.makeText(MainActivity.this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoCompleted() {
                Toast.makeText(MainActivity.this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdLoaded() {
                Toast.makeText(MainActivity.this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdOpened() {
                Toast.makeText(MainActivity.this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoStarted() {
                Toast.makeText(MainActivity.this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
            }
        });

        loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.rewarded_video),
                new AdRequest.Builder().build());

        // showing the ad to user
        showRewardedVideo();
    }

    private void showRewardedVideo() {
        // make sure the ad is loaded completely before showing it
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }



    private void Intersitial() {


        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu2) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            default: {

                return true;
            }
            case R.id.calendar: {
                newads();
                this.showDialog(0);

                return true;
            }
            case R.id.today: {
                newads();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                this.mYear = this.cal.get(1);
                this.mMonth = this.cal.get(2);
                Object[] arrobject = new Object[]{1 + this.mMonth};
                this.sMonth = String.format((String)"%02d", (Object[])arrobject);
                this.mDay = this.cal.get(5);
                this.wv1.loadData("<IMG HEIGHT=\"" + displayMetrics.heightPixels + "px\"" + " WIDTH=\"" + displayMetrics.widthPixels + "px\"" + " SRC=\"http://www.tamildailycalendar.com/" + this.mYear + "/" + this.mDay + this.sMonth + this.mYear + ".jpg\" />", "text/html", "UTF-8");
                return true;
            }
            case R.id.VideoView: {
                newads();
                Intent S=new Intent(MainActivity.this,VideoStoredInSDCard.class);
                startActivity(S);

            }
            case R.id.exit:
        }
        System.exit((int)0);
        return true;
    }

    }


