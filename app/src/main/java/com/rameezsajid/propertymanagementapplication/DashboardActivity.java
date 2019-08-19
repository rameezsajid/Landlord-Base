package com.rameezsajid.propertymanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button dashboardButton = findViewById(R.id.dashboardButton);

        Button dashboardButton2 = findViewById(R.id.dashboardButton2);

        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, PropertyActivity.class));
            }
        });

        dashboardButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, ReportActivity.class));
            }
        });


    }
}
