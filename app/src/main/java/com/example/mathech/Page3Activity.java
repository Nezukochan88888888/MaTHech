package com.example.mathech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Page3Activity extends AppCompatActivity {

    private WebView webView;
    private static final String PREFS_NAME = "MathEchPrefs";
    private static final String HIGH_SCORE_KEY = "HighScore_Page3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        webView = findViewById(R.id.webview);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnNext = findViewById(R.id.btnNext);
        ImageButton btnTableOfContents = findViewById(R.id.btnTableOfContents);

        // ✅ WebView setup
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(), "Android"); // <-- Add JS bridge

        webView.loadUrl("file:///android_asset/AdditionforIntegers.html");

        // ✅ Back button
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(Page3Activity.this, Page2Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        // ✅ Next button
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(Page3Activity.this, Page4Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        });

        // ✅ Table of Contents
        btnTableOfContents.setOnClickListener(v -> {
            Intent intent = new Intent(Page3Activity.this, TableOfContentsActivity.class);
            startActivity(intent);
        });
    }

    // ✅ JavaScript Interface to receive score from WebView
    private class WebAppInterface {

        @JavascriptInterface
        public void saveScore(int score) {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            int highScore = prefs.getInt(HIGH_SCORE_KEY, 0);

            if (score > highScore) {
                prefs.edit().putInt(HIGH_SCORE_KEY, score).apply();
                runOnUiThread(() ->
                        Toast.makeText(Page3Activity.this, "🎉 New High Score: " + score, Toast.LENGTH_SHORT).show()
                );
            } else {
                runOnUiThread(() ->
                        Toast.makeText(Page3Activity.this, "Your Score: " + score + "\nHigh Score: " + highScore, Toast.LENGTH_SHORT).show()
                );
            }
        }

        @JavascriptInterface
        public int getHighScore() {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            return prefs.getInt(HIGH_SCORE_KEY, 0);
        }
    }
}
