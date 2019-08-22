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


    private String userID;
    private Button addButton;

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    ListView listViewProperties;

    List<Properties> propertiesList;

    List<PropertiesLocation> propertiesListLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        editTextLocation = (EditText) findViewById(R.id.editTextPropertyLocation);
        spinnerPropertyType = (Spinner) findViewById(R.id.spinnerProperty);
        editTextRental = (EditText) findViewById(R.id.editTextRent);

        addButton = (Button) findViewById(R.id.buttonAddProperty);

        mAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference("properties");

        FirebaseUser user = mAuth.getCurrentUser();

        userID = user.getUid();

        listViewProperties = (ListView) findViewById(R.id.listViewProperties2);

        propertiesList = new ArrayList<>();

        propertiesListLocation = new ArrayList<>();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProperties();
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

//                reportDialogBox(propertyID, propertyLocation);
//                alertDialog.dismiss();
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        DatabaseReference mFirebaseRef = FirebaseDatabase.getInstance().getReference();


        Query query = mFirebaseRef.child("properties").child(userID).child(propertyID);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvReportLocation.setText(dataSnapshot.child("propertyLocation").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query2 = mFirebaseRef.child("properties").child(userID).child(propertyID);

        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvReportType.setText(dataSnapshot.child("propertyType").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query3 = mFirebaseRef.child("properties").child(userID).child(propertyID);

        query3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvRental.setText(dataSnapshot.child("propertyRental").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

        final EditText etLocationUpdate = (EditText) dialogView.findViewById(R.id.editText_Location_Update);
        final Spinner spinnerTypeUpdate = (Spinner) dialogView.findViewById(R.id.spinner_Type_Update);
        final EditText etRentalUpdate = (EditText) dialogView.findViewById(R.id.editText_Rent_Update);

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

                if (TextUtils.isEmpty(location)){
                    etLocationUpdate.setError("Field Required");
                    return;
                }
                updateProperties(propertyID, location, type, rent);

                alertDialog.dismiss();

            }
        });

        DatabaseReference mFirebaseRef = FirebaseDatabase.getInstance().getReference();
        Query query = mFirebaseRef.child("properties").child(userID).child(propertyID);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                etLocationUpdate.setText(dataSnapshot.child("propertyLocation").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query2 = mFirebaseRef.child("properties").child(userID).child(propertyID);

        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                etRentalUpdate.setText(dataSnapshot.child("propertyRental").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    private boolean updateProperties (String id, String location, String type, String rent){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("properties").child(userID).child(id);

        Properties properties = new Properties(id, location, type, rent);

        databaseReference.setValue(properties);

        Toast.makeText(PropertyActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

        return true;
    }



    private void addProperties(){
        String location = editTextLocation.getText().toString().trim();
        String propertyType = spinnerPropertyType.getSelectedItem().toString();
        String rent = editTextRental.getText().toString().trim();

        if (!TextUtils.isEmpty(location)){

            String id = myRef.push().getKey();

            Properties properties = new Properties(id, location, propertyType, rent);

            myRef.child(userID).child(id).setValue(properties);

            Toast.makeText(PropertyActivity.this, "Property Added", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(PropertyActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }
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
