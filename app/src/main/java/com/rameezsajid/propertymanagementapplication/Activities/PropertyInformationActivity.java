package com.rameezsajid.propertymanagementapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rameezsajid.propertymanagementapplication.Model.Properties;
import com.rameezsajid.propertymanagementapplication.R;

import java.util.ArrayList;
import java.util.List;

public class PropertyInformationActivity extends AppCompatActivity {

    private String userID;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    private String currentPropertyLocaton;
    private String currentPropertyType;
    private String currentRental;
    private String currentManagementName;
    private String currentRefurb;
    private String currentAddress;
    private String currentPostcode;
    private String currentBedrooms;
    private String currentTenancyLength;
    private String currentTenantName;

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

        TextView tvLocation = findViewById(R.id.holder_Location);
        TextView tvAddress = findViewById(R.id.holder_Address);
        TextView tvType = findViewById(R.id.holder_PropertyType);
        TextView tvPostcode = findViewById(R.id.holder_Postcode);
        TextView tvBedrooms = findViewById(R.id.holder_Bedrooms);
        TextView tvLength = findViewById(R.id.holder_TenancyLength);
        TextView tvTenant = findViewById(R.id.holder_TenantName);


        currentPropertyLocaton = getIntent().getExtras().get("propertyCurrent").toString();
        currentPropertyType = getIntent().getExtras().get("propertyType").toString();
        currentRental = getIntent().getExtras().get("propertyRental").toString();
        currentManagementName = getIntent().getExtras().get("managementName").toString();
        currentRefurb = getIntent().getExtras().get("Refurb").toString();
        currentAddress = getIntent().getExtras().get("Address").toString();
        currentPostcode = getIntent().getExtras().get("Postcode").toString();
        currentBedrooms = getIntent().getExtras().get("Bedrooms").toString();
        currentTenancyLength = getIntent().getExtras().get("Length").toString();
        currentTenantName = getIntent().getExtras().get("Tenant").toString();



        tvLocation.setText("Your Property Location: " + currentPropertyLocaton);

        tvAddress.setText("" + currentAddress);
        tvPostcode.setText("" + currentPostcode);

        tvType.setText("Property Type: " + currentPropertyType);
        tvBedrooms.setText("Number of Bedrooms: " + currentBedrooms);

        tvLength.setText("Tenancy Length: " + currentTenancyLength);
        tvTenant.setText("Your Tenants Name is: " + currentTenantName);


        checkIfEmpty();


    }

    private void checkIfEmpty(){

        TextView tvRental = findViewById(R.id.holder_Rental);
        TextView tvManagement = findViewById(R.id.holder_Management);
        TextView tvRefurb = findViewById(R.id.holder_Refurbishment);

        if (currentRental.isEmpty()){
            tvRental.setText("No Rent For This Property");
        }else {
            tvRental.setText("Your Monthly Rental for This Property is: £" + currentRental);
        }


        if (currentManagementName.isEmpty()){
            tvManagement.setText("Your Property is Privately Rented");
        }else {
            tvManagement.setText("Your Property is Managed by: " + currentManagementName);
        }

        if (currentRefurb.isEmpty()){
            tvRefurb.setText("You Have Not Refurbished This Property");
        }else {
            tvRefurb.setText("Refurbishment Cost for this property: £" + currentRefurb);
        }


    }



}
