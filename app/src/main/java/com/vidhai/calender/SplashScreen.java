package com.vidhai.calender;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

public class SplashScreen extends AppCompatActivity {
Button b1;
    InterstitialAd mInterstitialAd;
    private AdView mAdView;
    private PublisherAdView mPublisherAdView;
    private PublisherInterstitialAd mPublisherInterstitialAd;
LinearLayout lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAdView = (AdView) findViewById(R.id.adView);
        lv1=(LinearLayout)findViewById(R.id.lv1);
        mPublisherAdView = findViewById(R.id.publisherAdView);
        MobileAds.initialize(this, ("ca-app-pub-3643602219143275~4684936349"));
        mPublisherInterstitialAd = new PublisherInterstitialAd(this);
        mPublisherAdView = new PublisherAdView(this);
        mPublisherInterstitialAd.setAdUnitId("ca-app-pub-3643602219143275/4695888099");
        mPublisherAdView.setAdSizes(AdSize.BANNER);
        mPublisherAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
//        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                Log.e("Thread","Working");
//                Intersitial();
                Adsmethod();

                handler.postDelayed(this, 3000);
            }
        };
        runnable.run();

        mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.e("mPublisherInterstitial","load");
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e("mPublisherInterstitial",errorCode+"");

                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });

        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        mPublisherAdView.loadAd(adRequest);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.e("mAdView ad","load");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e("errorcode",errorCode+"");
            }

            @Override
            public void onAdOpened() {

            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdClosed() {

            }
        });
        lv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent in = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(in);
                    }
                }, 2000);
            }
        });



        mPublisherAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

                Log.e("publisher ad","load");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e("publisher",errorCode+"");
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
//        b1 = (Button) findViewById(R.id.b1);
//
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(("ca-app-pub-3643602219143275/4695888099"));
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        Intent in = new Intent(SplashScreen.this, MainActivity.class);
//                        startActivity(in);
//                        finish();
//                    }
//                }, 2000);
//            }
//        });
    }

    private void Adsmethod() {


        mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
Log.e("publisher ad","load");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.e("publisher ad","load");
                Log.e("errorCode",errorCode+"");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        mPublisherAdView.loadAd(adRequest);

//        mPublisherAdView.setAdSizes(AdSize.BANNER);
//
//        mPublisherAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        mPublisherAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e("mPublisherAdView ad","load");
                Log.e("mPublisherAdView",errorCode+"");
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }

    private void Admethoad() {


            Log.e("Admethoad","Working");
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
//            mAdView.setAdListener(new AdListener() {
//                @Override
//                public void onAdLoaded() {
//                    Toast.makeText(SplashScreen.this, "Ad Show Success ", Toast.LENGTH_SHORT).show();
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
