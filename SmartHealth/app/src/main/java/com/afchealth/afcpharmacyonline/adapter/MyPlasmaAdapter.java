package com.afchealth.afcpharmacyonline.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afchealth.afcpharmacyonline.R;
import com.afchealth.afcpharmacyonline.data.model.plasma.PlasmaData;

import java.util.List;




public class MyPlasmaAdapter extends RecyclerView.Adapter<MyPlasmaAdapter.ViewHolder> {

    Context context;
    public Typeface typeface, typefaceSemibold, typefacebold;
    List<PlasmaData> plasmaDataList;
    OnItemClickListener onItemClickListener;

    public MyPlasmaAdapter(Context context, List<PlasmaData> plasmaDataList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.plasmaDataList = plasmaDataList;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.plasma_list_item_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlasmaData plasmaData = plasmaDataList.get(position);
        holder.name.setText(plasmaData.getName());

//        AssetManager am = context.getApplicationContext().getAssets();
//        typeface = Typeface.createFromAsset(am,
//                String.format(Locale.US, "font/%s", "opensans_regular.ttf"));
//        typefaceSemibold = Typeface.createFromAsset(am,
//                String.format(Locale.US, "font/%s", "opensans_semibold.ttf"));
//        typefacebold = Typeface.createFromAsset(am,
//                String.format(Locale.US, "font/%s", "opensans_bold.ttf"));
//        if (position == 0)
//            holder.attendaceViewId1.setVisibility(View.INVISIBLE);
//        if ((singleAttendanceList.size() - 1) == position) {
//            holder.notiLowerLine.setVisibility(View.INVISIBLE);
//        } else
//            holder.notiLowerLine.setVisibility(View.VISIBLE);
//        holder.cell.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_transition));
//        if (singleAttendance != null && singleAttendance.getDate() != null) {
//            if (singleAttendance.getDate() != null)
//                holder.date.setText(UIHelper.dateTimeParse(singleAttendance.getDate()));
//            if (singleAttendance.getCheckIn() != null)
//                holder.tvAttendaceInTime.setText(UIHelper.TimeParse(singleAttendance.getDate() + " " + singleAttendance.getCheckIn()));
//
//
//                if (singleAttendance.getCheckOut() != null && singleAttendance.getCheckOut().equals("00:00:00")) {
//                    holder.tvAttendaceOutTime.setText("---");
//                } else
//                    holder.tvAttendaceOutTime.setText(UIHelper.TimeParse(singleAttendance.getDate() + " " + singleAttendance.getCheckOut()));
//
//
//        }

    }

    @Override
    public int getItemCount() {
        return plasmaDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, body, tvUpgradePakMonthgId1, tvAttendaceInTime, tvAttendaceOutTime;
        View attendaceViewId1, notiLowerLine;
        RelativeLayout cell;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            cell = itemView.findViewById(R.id.cell);
            name = itemView.findViewById(R.id.name);
//            tvAttendaceInTime = itemView.findViewById(R.id.tvAttendaceInTime);
//            tvAttendaceOutTime = itemView.findViewById(R.id.tvAttendaceOutTime);
//            attendaceViewId1 = itemView.findViewById(R.id.attendaceViewId1);
//            notiLowerLine = itemView.findViewById(R.id.notiLowerLine);
//            body = itemView.findViewById(R.id.body);
//            body.setTypeface(typeface);
            //   tvUpgradePakMonthgId1 = itemView.findViewById(R.id.tvUpgradePakMonthgId1);
        }




        @Override
        public void onClick(View view) {

        }

    }

    public void add(PlasmaData r) {
        plasmaDataList.add(r);
        notifyItemInserted(plasmaDataList.size());
    }

    public void addAllData(List<PlasmaData> plasmaData) {
        for (PlasmaData result : plasmaData) {
            add(result);
        }
    }


    public interface OnItemClickListener {


        void onItemClick(View view, int position);


    }
}
