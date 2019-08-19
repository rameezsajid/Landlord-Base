package com.rameezsajid.propertymanagementapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PropertyListLocation extends ArrayAdapter<PropertiesLocation> {
    private Activity context;
    private List<PropertiesLocation> propertyListOfLocation;


    public PropertyListLocation (Activity context, List<PropertiesLocation> propertyListOfLocation) {
        super(context, R.layout.test_list_layout, propertyListOfLocation);
        this.context = context;
        this.propertyListOfLocation = propertyListOfLocation;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.test_list_layout, null, true);


        TextView textViewLocationLocation = (TextView)listViewItem.findViewById(R.id.tvTest1);

        PropertiesLocation propertiesLocation = propertyListOfLocation.get(position);

        textViewLocationLocation.setText(propertiesLocation.getPropertyLocation());


        return listViewItem;
    }
}
