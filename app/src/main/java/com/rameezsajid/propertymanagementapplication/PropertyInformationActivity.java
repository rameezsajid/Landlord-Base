package com.rameezsajid.propertymanagementapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
    private String currentRental;

    List<Properties> propertiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_information);

        mAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference("properties");

        FirebaseUser user = mAuth.getCurrentUser();

        userID = user.getUid();

        propertiesList = new ArrayList<>();

        TextView tvHolder1 = findViewById(R.id.tv_PI1);
        TextView tvHolder2 = findViewById(R.id.tv_PI2);
        TextView tvHolder3 = findViewById(R.id.tv_PI3);

        currentProperty = getIntent().getExtras().get("propertyCurrent").toString();
        currentPropertyType = getIntent().getExtras().get("propertyType").toString();
        currentRental = getIntent().getExtras().get("propertyRental").toString();

        tvHolder1.setText("Your Property Location is in " + currentProperty);
        tvHolder2.setText("Your Property Type is a " + currentPropertyType);
        tvHolder3.setText("The Monthly Rental is " + currentRental);




    }



}
