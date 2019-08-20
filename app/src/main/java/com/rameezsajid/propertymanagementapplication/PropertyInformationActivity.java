package com.rameezsajid.propertymanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PropertyInformationActivity extends AppCompatActivity {

    private String userID;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    private String currentProperty;
    private String currentPropertyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_information);

        mAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference("properties");

        FirebaseUser user = mAuth.getCurrentUser();

        userID = user.getUid();

        TextView tvHolder1 = findViewById(R.id.tv_PI1);
        TextView tvHolder2 = findViewById(R.id.tv_PI2);

        currentProperty = getIntent().getExtras().get("propertyCurrent").toString();
        currentPropertyType = getIntent().getExtras().get("propertyType").toString();

        tvHolder1.setText("Your Property Location is in " + currentProperty);
        tvHolder2.setText("Your Property Type is a " + currentPropertyType);




    }

}
