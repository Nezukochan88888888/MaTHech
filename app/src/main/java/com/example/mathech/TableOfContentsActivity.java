package com.example.mathech;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TableOfContentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_of_contents);

        setupNavigationButtons();
        setupBackPressHandler();
    }

    private void setupNavigationButtons() {
        // Home Page
        setupButton(R.id.btnMain, MainActivity.class);

        // Lessons
        setupButton(R.id.btnPage8, Page8Activity.class);
        setupButton(R.id.btnPage15, Page15Activity.class);
        setupButton(R.id.btnPage22, Page22Activity.class);

        // Games
        setupButton(R.id.btnInteractiveline, Interactiveline.class);
        setupButton(R.id.btnSnakegame, Snakegame.class);
        setupButton(R.id.btnBubblegame, Bubblegame.class);
        setupButton(R.id.btnWordproblemgame, Wordproblemgame.class);

        // User Manual
        setupButton(R.id.btnUserManual, UserManualActivity.class);
    }

    private void setupButton(int buttonId, Class<?> targetActivity) {
        Button button = findViewById(buttonId);
        if (button != null) {
            button.setOnClickListener(v -> navigateToActivity(targetActivity));
        }
    }

    private void navigateToActivity(Class<?> targetActivity) {
        Intent intent = new Intent(TableOfContentsActivity.this, targetActivity);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void setupBackPressHandler() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitDialog();
            }
        });
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    finishAffinity();
                    overridePendingTransition(0, android.R.anim.fade_out);
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Apply rounded corners with soft background
        if (dialog.getWindow() != null) {
            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.RECTANGLE);
            shape.setColor(Color.parseColor("#2d343b"));
            shape.setCornerRadius(48f);
            dialog.getWindow().setBackgroundDrawable(shape);
        }

        // Style buttons
        if (dialog.getButton(AlertDialog.BUTTON_POSITIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(16f);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setPadding(32, 16, 32, 16);
        }
        if (dialog.getButton(AlertDialog.BUTTON_NEGATIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(16f);
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setPadding(32, 16, 32, 16);
        }
    }
}