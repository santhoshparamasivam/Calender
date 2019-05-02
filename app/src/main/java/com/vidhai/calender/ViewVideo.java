package com.vidhai.calender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class ViewVideo extends Activity {
    private String filename;
    VideoView vv;
    InterstitialAd mInterstitialAd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.gc();

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        filename = extras.getString("videofilename");

        setContentView(R.layout.activity_view);
        MobileAds.initialize(this,("ca-app-pub-3643602219143275~4684936349"));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(("ca-app-pub-3643602219143275/4695888099"));
        Intersitial();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int h = displaymetrics.heightPixels;
        int w = displaymetrics.widthPixels;
        vv = (VideoView) findViewById(R.id.videoView);
        vv.setVideoPath(filename);
        vv.setMediaController(new MediaController(this));
        vv.requestFocus();
        vv.start();
        vv.setLayoutParams(new FrameLayout.LayoutParams(w,h));
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
}