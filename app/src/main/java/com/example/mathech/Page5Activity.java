package com.example.mathech;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Page5Activity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5); // Corrected layout reference

        webView = findViewById(R.id.webview);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnNext = findViewById(R.id.btnNext); // Added Next button

        // Load AdditionDropandDrag.html
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/AdditionDropandDrag.html");

        // Back button - go to Page4Activity with animation
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(Page5Activity.this, Page4Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        // Next button - go to Page6Activity with animation
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(Page5Activity.this, Page6Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        });

        ImageButton btnTableOfContents = findViewById(R.id.btnTableOfContents);
        btnTableOfContents.setOnClickListener(v -> {
            Intent intent = new Intent(Page5Activity.this, TableOfContentsActivity.class);
            startActivity(intent);
        });
    }
}
