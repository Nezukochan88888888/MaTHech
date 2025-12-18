package com.example.mathech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Show the introduction message immediately
        showIntroMessage();
    }

    private void showIntroMessage() {
        // 1. Inflate your custom layout file
        View customTitleView = getLayoutInflater().inflate(R.layout.dialog_custom_title, null);

        // Build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCustomTitle(customTitleView) // <-- 2. Set it as the custom title
                .setMessage(getString(R.string.app_welcome_message))
                .setPositiveButton("OK", (dialog, which) -> {
                    // Navigate to StartActivity after pressing OK
                    Intent intent = new Intent(SplashActivity.this, StartActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();  // Finish the splash activity
                })
                .setCancelable(false);

        // Create and show dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Apply rounded corners with a background color
        if (dialog.getWindow() != null) {
            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.RECTANGLE);
            shape.setColor(Color.parseColor("#2d343b")); // Dark background
            shape.setCornerRadius(48f); // Rounded corners
            dialog.getWindow().setBackgroundDrawable(shape);
        }

        // 3. You can now REMOVE the entire block for styling the old title.
        //    It is no longer needed.

        // Style the message (leaving it at default alignment)
        int messageId = android.R.id.message;
        TextView messageView = dialog.findViewById(messageId);
        if (messageView != null) {
            messageView.setTextColor(Color.WHITE); // Make message text visible on dark background
            messageView.setTextSize(15f);
            messageView.setLineSpacing(4f, 1f);
            messageView.setPadding(40, 0, 40, 32); // Adjust padding as needed
        }

        // Style the button
        if (dialog.getButton(AlertDialog.BUTTON_POSITIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#74b9ff"));
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(16f);
        }
    }
}