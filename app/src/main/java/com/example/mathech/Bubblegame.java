package com.example.mathech;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;

import androidx.appcompat.app.AppCompatActivity;

public class Bubblegame extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_addition);

        webView = findViewById(R.id.webview);

        // ✅ Configure WebView securely and for best performance
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false); // allow sound autoplay
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);

        // ✅ Hardware acceleration for smooth animations
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        // ✅ Ensure input focus works for touch and keyboard events
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.requestFocus();

        // ✅ Load encrypted BubbleIntegers HTML file
        try {
            String htmlContent = AESUtils.decryptFromAssets(this, "BubbleIntegers.enc");
            if (htmlContent != null && !htmlContent.isEmpty()) {
                webView.loadDataWithBaseURL(
                        "file:///android_asset/",
                        htmlContent,
                        "text/html",
                        "UTF-8",
                        null
                );
            } else {
                Log.e("Bubblegame", "Decrypted HTML is empty or null");
            }
        } catch (Exception e) {
            Log.e("Bubblegame", "Error loading BubbleIntegers.enc", e);
        }
    }

    // ✅ Clean up WebView on destroy to prevent memory leaks
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadUrl("about:blank");
            webView.stopLoading();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.destroy();
        }
        super.onDestroy();
    }
}
