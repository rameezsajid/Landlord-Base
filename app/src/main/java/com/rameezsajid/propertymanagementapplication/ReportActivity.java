package com.rameezsajid.propertymanagementapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

public class ReportActivity extends AppCompatActivity {

    private String userID;


    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    List<PropertiesLocation> propertiesListLocation;

    ListView listViewProperties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        mAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference("properties");

        FirebaseUser user = mAuth.getCurrentUser();

        userID = user.getUid();

        propertiesListLocation = new ArrayList<>();

        listViewProperties = (ListView) findViewById(R.id.listViewTesting);

    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseDatabase.getInstance().getReference("properties").getRef().child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                propertiesListLocation.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    PropertiesLocation property = data.getValue(PropertiesLocation.class);

                    propertiesListLocation.add(property);
                }

                PropertyListLocation adapter = new PropertyListLocation(ReportActivity.this, propertiesListLocation);
                listViewProperties.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}
