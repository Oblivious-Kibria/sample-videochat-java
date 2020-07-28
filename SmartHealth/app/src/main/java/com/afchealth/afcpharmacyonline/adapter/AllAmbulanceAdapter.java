package com.afchealth.afcpharmacyonline.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afchealth.afcpharmacyonline.Interface.AmbulanceItemClicklistener;
import com.afchealth.afcpharmacyonline.Interface.BPItemClicklistener;
import com.afchealth.afcpharmacyonline.R;
import com.afchealth.afcpharmacyonline.data.model.ambulance.AmbulanceData;

import java.util.List;




public class AllAmbulanceAdapter extends RecyclerView.Adapter<AllAmbulanceAdapter.ViewHolder> {

    Context context;
    public Typeface typeface, typefaceSemibold, typefacebold;
    List<AmbulanceData> dataList;
    AmbulanceItemClicklistener ambulanceItemClicklistener;

    public AllAmbulanceAdapter(Context context, List<AmbulanceData> dataList, AmbulanceItemClicklistener ambulanceItemClicklistener) {
        this.context = context;
        this.dataList = dataList;
        this.ambulanceItemClicklistener = ambulanceItemClicklistener;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.ambulance_list_item_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AmbulanceData ambulanceData = dataList.get(position);
        holder.name.setText(ambulanceData.getName());
        holder.bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             ambulanceItemClicklistener.onItemClick(dataList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, body, tvUpgradePakMonthgId1, tvAttendaceInTime, tvAttendaceOutTime;
        View attendaceViewId1, notiLowerLine;
        Button bookNow;
        LinearLayout cell;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cell = itemView.findViewById(R.id.cell);
            name = itemView.findViewById(R.id.name);
            bookNow = itemView.findViewById(R.id.bookNow);

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
