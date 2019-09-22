package com.rameezsajid.propertymanagementapplication.Model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rameezsajid.propertymanagementapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ImageAdapter extends ArrayAdapter<Upload> {
    private Activity context;
    private List<Upload> mUploads;

    public ImageAdapter (Activity context, List<Upload> mUploads) {
        super(context, R.layout.list_layout, mUploads);
        this.context = context;
        this.mUploads = mUploads;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.image_item, null, true);
        Upload uploadCurr = mUploads.get(position);

        TextView textViewRent = listViewItem.findViewById(R.id.text_view_rent);
        TextView textViewLocation = listViewItem.findViewById(R.id.text_view_location);
        ImageView imageView = listViewItem.findViewById(R.id.image_view_upload);
        TextView textViewType = listViewItem.findViewById(R.id.text_view_type);

        Picasso.with(context).load(uploadCurr.getImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);

        textViewLocation.setText(uploadCurr.getAddress() + ", " + uploadCurr.getPostcode() + ", " + uploadCurr.getLocation());
        textViewRent.setText("£" + uploadCurr.getRent() + "pcm");

        textViewType.setText(uploadCurr.getBedroom() + " " + "Bedroom " + uploadCurr.getPropertyType());


        return listViewItem;
    }
}
