package com.afchealth.afcpharmacyonline.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afchealth.afcpharmacyonline.Interface.BPItemClicklistener;
import com.afchealth.afcpharmacyonline.R;
import com.afchealth.afcpharmacyonline.data.model.ambulance.AmbulanceData;

import java.util.List;




public class MyAmbulanceAdapter extends RecyclerView.Adapter<MyAmbulanceAdapter.ViewHolder> {

    Context context;
    public Typeface typeface, typefaceSemibold, typefacebold;
    List<AmbulanceData> dataList;
    BPItemClicklistener bpItemClicklistener;

    public MyAmbulanceAdapter(Context context, List<AmbulanceData> dataList) {
        this.context = context;
        this.dataList = dataList;

//        this.bpItemClicklistener = bpItemClicklistener;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.plasma_list_item_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AmbulanceData ambulanceData = dataList.get(position);
        holder.name.setText(ambulanceData.getName());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, body, tvUpgradePakMonthgId1, tvAttendaceInTime, tvAttendaceOutTime;
        View attendaceViewId1, notiLowerLine;
        LinearLayout cell;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cell = itemView.findViewById(R.id.cell);
            name = itemView.findViewById(R.id.name);

        }





    }

    public void add(AmbulanceData r) {
        dataList.add(r);
        notifyItemInserted(dataList.size());
    }

    public void addAllData(List<AmbulanceData> ambulanceData) {
        for (AmbulanceData result : ambulanceData) {
            add(result);
        }
    }


}
