package com.rameezsajid.propertymanagementapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PropertyActivity extends AppCompatActivity {

    private EditText editTextLocation;
    private Spinner spinnerPropertyType;
    private EditText editTextRental;
    private EditText editTextAddress;
    private EditText editTextPostcode;
    private EditText editTextTenancyLength;
    private EditText editTextTenantName;
    private Spinner spinnerManagementType;
    private EditText editTextManagementName;


    private String userID;
    private Button addButton;

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    private Button btnGoTo;

    ListView listViewProperties;

    List<Properties> propertiesList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);


//        editTextLocation = (EditText) findViewById(R.id.editTextPropertyLocation);
//        spinnerPropertyType = (Spinner) findViewById(R.id.spinnerProperty);
//        editTextRental = (EditText) findViewById(R.id.editTextRent);
//        editTextAddress = (EditText) findViewById(R.id.editTextPropertyAddress);
//        editTextPostcode = findViewById(R.id.editTextPropertyPostcode);
//        editTextTenancyLength = findViewById(R.id.editTextPropertyTenancyLength);
//        editTextManagementName = findViewById(R.id.editTextManagementName);
//        spinnerManagementType = findViewById(R.id.spinnerManagementType);
//        editTextTenantName = findViewById(R.id.editTextPropertyTenantName);

        mAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference("properties");

        FirebaseUser user = mAuth.getCurrentUser();

        userID = user.getUid();

        btnGoTo = findViewById(R.id.btnGoToAdd);

        listViewProperties = (ListView) findViewById(R.id.listViewProperties2);

        propertiesList = new ArrayList<>();


        btnGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddPropertyDialogbox();
            }
        });

        //when list view clicked will open dialog
//        listViewProperties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String PropertiesLocation = propertiesList.get(i).getPropertyLocation();
//                String PropertiesType = propertiesList.get(i).getPropertyType();
//
//                Intent reportIntent = new Intent(PropertyActivity.this, PropertyInformationActivity.class);
//
//                reportIntent.putExtra("propertyCurrent", PropertiesLocation);
//                reportIntent.putExtra("propertyType", PropertiesType);
//                startActivity(reportIntent);
//
//
//
//
//                //showDialogBox(properties.getPropertyID(), properties.getPropertyLocation());
//
//            }
//        });

//        listViewProperties.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public void onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Properties properties = propertiesList.get(i);
//
//                showDialogBox(properties.getPropertyID(), properties.getPropertyLocation());
//
//            }
//        });




        listViewProperties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Properties properties =  propertiesList.get(i);

                showDialogBox(properties.getPropertyID(), properties.getPropertyLocation(), properties.getPropertyType(), properties.getPropertyRental());
            }
        });



    }

    private void showDialogBox(final String propertyID, final String propertyLocation, final String propertyType, final String propertyRental) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PropertyActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_box, null);
        dialogBuilder.setView(dialogView);

        final TextView tvReportLocation = (TextView) dialogView.findViewById(R.id.tv_Report_Location);
        final TextView tvReportType = (TextView) dialogView.findViewById(R.id.tv_Report_Type);
        final TextView tvRental = (TextView) dialogView.findViewById(R.id.tv_Report_Rental);

        final Button buttonCancel = (Button) dialogView.findViewById(R.id.btnClose);
        final Button buttonReport = (Button) dialogView.findViewById(R.id.btnReport);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);

        dialogBuilder.setTitle("Your Property " + propertyLocation);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();



        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDialogBox(propertyID, propertyLocation);
                alertDialog.dismiss();
            }
        });

        buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String PropertiesLocation = propertyLocation;
                String PropertyType = propertyType;
                String PropertyRental = propertyRental;
                Intent reportIntent = new Intent(PropertyActivity.this, PropertyInformationActivity.class);
                reportIntent.putExtra("propertyCurrent", PropertiesLocation);
                reportIntent.putExtra("propertyType", PropertyType);
                reportIntent.putExtra("propertyRental", PropertyRental);

                startActivity(reportIntent);
                alertDialog.dismiss();
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }


//    private void reportDialogBox(final String propertyID, final String propertyLocation){
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PropertyActivity.this);
//        LayoutInflater inflater = getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.dialog_box_report, null);
//        dialogBuilder.setView(dialogView);
//
//        final TextView tvReportLocationReport = (TextView) dialogView.findViewById(R.id.tv_Report_Location_Report2);
//        final TextView tvReportTypeReport = (TextView) dialogView.findViewById(R.id.tv_Report2);
//
//        final Button buttonClose = (Button) dialogView.findViewById(R.id.btnClose_Report);
//        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.btnUpdate_Report);
//
//        dialogBuilder.setTitle("Property Report " + propertyLocation);
//        final AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        // Add Methods for Buttons
//
//        buttonClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.dismiss();
//            }
//        });
//
//        buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                updateDialogBox(propertyID, propertyLocation);
//                alertDialog.dismiss();
//            }
//        });
//
//
//        DatabaseReference mFirebaseRef = FirebaseDatabase.getInstance().getReference();
//
//
//        Query query = mFirebaseRef.child("properties").child(userID).child(propertyID);
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                tvReportLocationReport.setText(dataSnapshot.child("propertyLocation").getValue(String.class));
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        Query query2 = mFirebaseRef.child("properties").child(userID).child(propertyID);
//
//        query2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                tvReportTypeReport.setText(dataSnapshot.child("propertyType").getValue(String.class));
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }

    private void updateDialogBox(final String propertyID, final String propertyLocation){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PropertyActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_box_update, null);
        dialogBuilder.setView(dialogView);

        final EditText etLocationUpdate = (EditText) dialogView.findViewById(R.id.editTextPropertyLocation_Update);
        final Spinner spinnerTypeUpdate = (Spinner) dialogView.findViewById(R.id.spinnerProperty_Update);
        final EditText etRentalUpdate = (EditText) dialogView.findViewById(R.id.editTextRent_Update);
        final EditText etAddressUpdate = dialogView.findViewById(R.id.editTextPropertyAddress_Update);
        final EditText etPostcodeUpdate = dialogView.findViewById(R.id.editTextPropertyPostcode_Update);
        final EditText etManagementNameUpdate = dialogView.findViewById(R.id.editTextManagementName_Update);
        final EditText etTenantNameUpdate = dialogView.findViewById(R.id.editTextPropertyTenantName_Update);
        final Spinner spinnerManagementTypeUpdate = dialogView.findViewById(R.id.spinnerManagementType_Update);
        final EditText etTenancyLengthUpdate = dialogView.findViewById(R.id.editTextPropertyTenancyLength_Update);

        final Button buttonCloseUpdate = (Button) dialogView.findViewById(R.id.btnClose_Update);
        final Button buttonUpdateUpdate = (Button) dialogView.findViewById(R.id.btnUpdate_Update);

        dialogBuilder.setTitle("Updating Property " + propertyLocation);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonCloseUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        buttonUpdateUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = etLocationUpdate.getText().toString().trim();
                String type = spinnerTypeUpdate.getSelectedItem().toString();
                String rent = etRentalUpdate.getText().toString().trim();
                String address = etAddressUpdate.getText().toString().trim();
                String postcode = etPostcodeUpdate.getText().toString().trim();
                String t_length = etTenancyLengthUpdate.getText().toString().trim();
                String t_name = etTenantNameUpdate.getText().toString().trim();
                String m_type = spinnerManagementTypeUpdate.getSelectedItem().toString();
                String m_name = etManagementNameUpdate.getText().toString().trim();


                if (TextUtils.isEmpty(location)){
                    etLocationUpdate.setError("Field Required");
                    return;
                }
                updateProperties(propertyID, location, address, postcode, type, rent, t_length, t_name, m_type, m_name);

                alertDialog.dismiss();

            }
        });

        DatabaseReference mFirebaseRef = FirebaseDatabase.getInstance().getReference();


        Query queryLocation = mFirebaseRef.child("properties").child(userID).child(propertyID);

        queryLocation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                etLocationUpdate.setText(dataSnapshot.child("propertyLocation").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query queryRental = mFirebaseRef.child("properties").child(userID).child(propertyID);

        queryRental.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                etRentalUpdate.setText(dataSnapshot.child("propertyRental").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query queryAddress = mFirebaseRef.child("properties").child(userID).child(propertyID);

        queryAddress.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                etAddressUpdate.setText(dataSnapshot.child("propertyAddress").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query queryPostcode = mFirebaseRef.child("properties").child(userID).child(propertyID);

        queryPostcode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                etPostcodeUpdate.setText(dataSnapshot.child("propertyPostcode").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query queryTenancyLength = mFirebaseRef.child("properties").child(userID).child(propertyID);

        queryTenancyLength.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                etTenancyLengthUpdate.setText(dataSnapshot.child("propertyTenancyLength").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query queryTenantName = mFirebaseRef.child("properties").child(userID).child(propertyID);

        queryTenantName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                etTenantNameUpdate.setText(dataSnapshot.child("propertyTenantName").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Query queryManagementName = mFirebaseRef.child("properties").child(userID).child(propertyID);

        queryManagementName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                etManagementNameUpdate.setText(dataSnapshot.child("propertyManagementName").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private boolean updateProperties (String id, String location, String address, String postcode, String type, String rent, String t_length, String t_name, String m_type, String m_name){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("properties").child(userID).child(id);

        Properties properties = new Properties(id, location, address, postcode, type, rent, t_length, t_name, m_type, m_name);

        databaseReference.setValue(properties);

        Toast.makeText(PropertyActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

        return true;
    }


    private void showAddPropertyDialogbox(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PropertyActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_box_add_property, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Add Property");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        editTextLocation = (EditText) dialogView.findViewById(R.id.editTextPropertyLocation);
        spinnerPropertyType = (Spinner) dialogView.findViewById(R.id.spinnerProperty);
        editTextRental = (EditText) dialogView.findViewById(R.id.editTextRent);
        editTextAddress = (EditText) dialogView.findViewById(R.id.editTextPropertyAddress);
        editTextPostcode = dialogView.findViewById(R.id.editTextPropertyPostcode);
        editTextTenancyLength = dialogView.findViewById(R.id.editTextPropertyTenancyLength);
        editTextManagementName = dialogView.findViewById(R.id.editTextManagementName);
        spinnerManagementType = dialogView.findViewById(R.id.spinnerManagementType);
        editTextTenantName = dialogView.findViewById(R.id.editTextPropertyTenantName);

        addButton = (Button) dialogView.findViewById(R.id.buttonAddProperty);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = editTextLocation.getText().toString().trim();
                String type = spinnerPropertyType.getSelectedItem().toString();
                String address = editTextAddress.getText().toString().trim();
                String postcode = editTextPostcode.getText().toString().trim();
                String t_length = editTextTenancyLength.getText().toString().trim();
                String t_name = editTextTenantName.getText().toString().trim();
                String rent = editTextRental.getText().toString().trim();
                String m_name = editTextManagementName.getText().toString().trim();
                String m_type = spinnerManagementType.getSelectedItem().toString();

                if (!TextUtils.isEmpty(location)){

                    String id = myRef.push().getKey();

                    Properties properties = new Properties(id, location, address, postcode, type, rent, t_length, t_name, m_type, m_name);

                    myRef.child(userID).child(id).setValue(properties);

                    Toast.makeText(PropertyActivity.this, "Property Added", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();

                }else {
                    Toast.makeText(PropertyActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseDatabase.getInstance().getReference("properties").getRef().child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                propertiesList.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Properties property = data.getValue(Properties.class);

                    propertiesList.add(property);
                }

                PropertyList adapter = new PropertyList(PropertyActivity.this, propertiesList);
                listViewProperties.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
