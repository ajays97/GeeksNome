package com.ajay.geeksnome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends AppCompatActivity implements AdvancedWebView.Listener {

    private AdvancedWebView mWebView;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-2819514375619003~2848430374");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2819514375619003/2708829579");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("F46B367C9316B954ABD72A81A27387F0").build());

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("F46B367C9316B954ABD72A81A27387F0").build();
        mAdView.loadAd(adRequest);

        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.loadUrl("https://cybernome.blogspot.com");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mInterstitialAd.show();
            }
        }, 210000);

    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
//        if (!mWebView.onBackPressed()) {
//            return;
//        }
        if (mWebView.getUrl().equals("https://cybernome.blogspot.in/?m=1")) {
            Log.d("URL", mWebView.getUrl());
            super.onBackPressed();
        } else mWebView.goBack();
        // ...
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        Log.d("OUTPUT", "Started");
        Log.d("OUTPUT", url);
        if (url.equals("https://cybernome.blogspot.in/?m=1") || url.equals("https://cybernome.blogspot.in")) {
            mWebView.clearHistory();
            mWebView.clearCache(true);
            Log.d("OUTPUT", "Cleared");
        }
    }

    @Override
    public void onPageFinished(String url) {
        Log.d("OUTPUT", "Finished");
        Log.d("OUTPUT", url);

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
    }

    @Override
    public void onExternalPageRequest(String url) {
    }

}
