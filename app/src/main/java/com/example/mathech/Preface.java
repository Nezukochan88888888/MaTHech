package com.example.mathech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class Preface extends AppCompatActivity {

    private WebView webView;

    @SuppressLint({"SetJavaScriptEnabled", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_addition);

        // Enable hardware acceleration explicitly
        getWindow().setFlags(
                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        );

        // Initialize WebView
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);

        // Ensure links stay inside WebView
        webView.setWebViewClient(new WebViewClient());
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null); // GPU rendering

        // ✅ Match JavaScript interface name with HTML
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void goNextPage() {
                runOnUiThread(() -> {
                    Intent intent = new Intent(Preface.this, TableOfContentsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                });
            }
        }, "Android");

        // Load HTML file from assets
        webView.loadUrl("file:///android_asset/Preface.html");
    }
}
