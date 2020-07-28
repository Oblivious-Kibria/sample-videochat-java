package com.afchealth.afcpharmacyonline.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afchealth.afcpharmacyonline.Interface.AmbulanceItemClicklistener;
import com.afchealth.afcpharmacyonline.Interface.BPItemClicklistener;
import com.afchealth.afcpharmacyonline.R;
import com.afchealth.afcpharmacyonline.adapter.AllAmbulanceAdapter;
import com.afchealth.afcpharmacyonline.adapter.AllPlasmaAdapter;
import com.afchealth.afcpharmacyonline.data.model.ambulance.AmbulanceData;
import com.afchealth.afcpharmacyonline.data.model.plasma.PlasmaData;
import com.afchealth.afcpharmacyonline.viewmodel.AllAmbulanceModel;
import com.afchealth.afcpharmacyonline.viewmodel.AllPostViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class AllAmbulanceRequestFragment extends Fragment implements AmbulanceItemClicklistener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private AllAmbulanceModel mViewModel;
    private AllAmbulanceAdapter mAdapter;
    private List<AmbulanceData> mArrayList = new ArrayList<>();



    public static AllAmbulanceRequestFragment newInstance() {
        return new AllAmbulanceRequestFragment();
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.all_ambulance_request_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        getData();
    }




    private void getData() {
        AmbulanceData ambulanceData = new AmbulanceData("1", "Ambulance", "test", "4.05AM");
        mArrayList.add(ambulanceData);
        ambulanceData = new AmbulanceData("1", "test", "test", "4.05AM");
        mArrayList.add(ambulanceData);
        mAdapter.notifyDataSetChanged();
        //return mArrayList;
    }
//    private List<PlasmaData> getData() {
//        PlasmaData plasmaData = new PlasmaData("1", "test", "test", "4.05AM");
//        mArrayList.add(plasmaData);
//        // mAdapter.notifyDataSetChanged();
//        return mArrayList;
//    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(AllAmbulanceModel.class);
        // TODO: Use the ViewModel


    }

    private void initAdapter() {
        mArrayList = new ArrayList<>();
        mAdapter = new AllAmbulanceAdapter(getActivity(), mArrayList, this);
//    -> {
//
//        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_divider));
//        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(mAdapter);
    }

    public void showFragment(Fragment fragment) {
        String TAG = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }







    @Override
    public void onItemClick(AmbulanceData ambulanceData) {
        postDialog();
    }



        private void postDialog(){
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(R.layout.post_create_dialog, null);
      //  TextView text = (TextView) deleteDialogView.findViewById(R.id.text_dialog);
        TextView method = deleteDialogView.findViewById(R.id.method);

        final AlertDialog deleteDialog = new AlertDialog.Builder(getActivity()).create();
        deleteDialog.setCancelable(false);
        if (deleteDialog.isShowing())
            deleteDialog.dismiss();
        deleteDialog.setView(deleteDialogView);
        deleteDialogView.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        deleteDialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your business logic
                deleteDialog.dismiss();
            }
        });


        deleteDialog.show();

    }
}
