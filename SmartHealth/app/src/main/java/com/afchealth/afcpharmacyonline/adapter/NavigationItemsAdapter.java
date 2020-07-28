package com.afchealth.afcpharmacyonline.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.afchealth.afcpharmacyonline.R;
import com.afchealth.afcpharmacyonline.utils.NavData;



public class NavigationItemsAdapter extends ArrayAdapter<NavData> {
    Context mContext;
    int layoutResourceId;
    NavData data[] = null;

    public NavigationItemsAdapter(Context mContext, int layoutResourceId, NavData[] data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;

    }
    @Override public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.ivHomeId2);
        TextView textViewName = (TextView) listItem.findViewById(R.id.tvHomeId2);
       View viewLine=listItem.findViewById(R.id.viewLine);
        NavData folder = data[position];
        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);
        int getPos=getCount();

        if (position==8){
            viewLine.setVisibility(View.GONE);
        }

        return listItem;
    }
}
