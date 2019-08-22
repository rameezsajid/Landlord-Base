package com.rameezsajid.propertymanagementapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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


    ListView listViewProperties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        listViewProperties = (ListView) findViewById(R.id.listViewTesting);

        listViewProperties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String PropertiesLocation = propertiesList.get(i).getPropertyLocation();
//                String PropertiesType = propertiesList.get(i).getPropertyType();
//
//                Intent reportIntent = new Intent(ReportActivity.this, PropertyInformationActivity.class);
//
//                reportIntent.putExtra("propertyCurrent", PropertiesLocation);
//                reportIntent.putExtra("propertyType", PropertiesType);
//                startActivity(reportIntent);


            }
        });

    }

}
