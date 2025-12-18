package com.example.mathech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ReadyMultiplication extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_addition);

        // Initialize WebView
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // 🔹 JavaScript interface to handle "Start Activity" button in HTML
        webView.addJavascriptInterface(new Object() {
            @android.webkit.JavascriptInterface
            public void goNextPage() {
                runOnUiThread(() -> {
                    Intent intent = new Intent(ReadyMultiplication.this, Page16Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                });
            }
        }, "Android");

        // Load the HTML page
        webView.loadUrl("file:///android_asset/ReadyMultiplication.html");
    }
}
