package com.example.mathech;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Page6Activity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page6); // Corrected layout reference

        webView = findViewById(R.id.webview);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnNext = findViewById(R.id.btnNext); // Added Next button

        // Load AdditionMatchMePositive.html
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/AdditionMatchMePositive.html");

        // Back button - go to Page5Activity with animation
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(Page6Activity.this, Page5Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        // Next button - go to Page7Activity with animation
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(Page6Activity.this, Page7Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        });

        ImageButton btnTableOfContents = findViewById(R.id.btnTableOfContents);
        btnTableOfContents.setOnClickListener(v -> {
            Intent intent = new Intent(Page6Activity.this, TableOfContentsActivity.class);
            startActivity(intent);
        });

    }
}
