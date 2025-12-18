package com.example.mathech;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Page8Activity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page7); //

        webView = findViewById(R.id.webview);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnNext = findViewById(R.id.btnNext);
        ImageButton btnTableOfContents = findViewById(R.id.btnTableOfContents);

        // Load SubtractingIntegersIntro.html
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/SubtractingIntegersIntro.html");

        // Back button: Page 7
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(Page8Activity.this, Page7Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        // Next button: Ending Page
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(Page8Activity.this, ReadySubtraction.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        });

        // Table of Contents
        btnTableOfContents.setOnClickListener(v -> {
            Intent intent = new Intent(Page8Activity.this, TableOfContentsActivity.class);
            startActivity(intent);
        });
    }
}
