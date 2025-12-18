package com.example.mathech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;

import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        Button btnNext = findViewById(R.id.btnNext);
        Button btnBack = findViewById(R.id.btnBack);
        ImageButton btnTableOfContents = findViewById(R.id.btnTableOfContents);

        // ✅ Improved WebView Setup
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true); // For Lumi/interactive content
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // ✅ Performance tuning
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.clearCache(false);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null); // smoother animations
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.requestFocus();

        // ✅ Load the local HTML file
        webView.loadUrl("file:///android_asset/AddingIntegersIntroduction.html");

        // ✅ Button actions
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReadyAddition.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TableOfContentsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        btnTableOfContents.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TableOfContentsActivity.class);
            startActivity(intent);
        });
    }

    // ✅ Optional: Handle back press inside WebView
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
