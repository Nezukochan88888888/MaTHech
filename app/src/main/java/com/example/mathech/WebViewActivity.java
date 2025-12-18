package com.example.mathech;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class WebViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private WebView webView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // Initialize Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the hamburger icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Initialize Drawer Layout
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Setup Drawer Toggle
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set Navigation Item Click Listener
        navigationView.setNavigationItemSelectedListener(this);

        // Initialize WebView
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        // ⭐ CRITICAL SETTINGS FOR CANVAS GAMES ⭐
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);  // ✅ Enable localStorage
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        // Enable hardware acceleration for better canvas performance
        webView.setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null);

        // Allow file access
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);

        // Enable mixed content (if needed)
        webView.getSettings().setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // Load the default page or saved progress
        String page = getIntent().getStringExtra("page");
        if (page == null || page.isEmpty()) {
            page = "index.html"; // Default page
        }
        loadPage(page);
    }

    // 🔒 New loadPage method (with encryption support)
    private void loadPage(String page) {
        try {
            if (page.equals("SnakeandIntegers.html") ||
                    page.equals("BouncingIntegers.html") ||
                    page.equals("BubbleIntegers.html")) {

                // For protected game files → load decrypted version
                String encFile = page.replace(".html", ".enc");
                String html = AESUtils.decryptFromAssets(this, encFile);
                webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "UTF-8", null);

            } else {
                // For normal Lumi HTMLs → load directly
                webView.loadUrl("file:///android_asset/" + page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId(); // Get selected item ID

        if (id == R.id.nav_page1) {
            loadPage("polygon.html");
        } else if (id == R.id.nav_page2) {
            loadPage("geometry.html");
        } else if (id == R.id.nav_page3) {
            loadPage("SubtractingIntegersIntro.html");
        } else if (id == R.id.nav_table_of_contents) {
            loadPage("table_of_contents.html");
        } else {
            return false;
        }

        // Close drawer after selection
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}