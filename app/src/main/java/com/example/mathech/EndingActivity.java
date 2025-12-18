package com.example.mathech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class EndingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);

        Button btnHome = findViewById(R.id.btnHome);

        // Home button to go back to MainActivity
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndingActivity.this, TableOfContentsActivity.class);
                startActivity(intent);
                finish(); // Closes the ending page
            }
        });
    }
}
