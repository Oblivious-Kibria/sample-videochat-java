package com.afchealth.afcpharmacyonline.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afchealth.afcpharmacyonline.R;
import com.afchealth.afcpharmacyonline.adapter.MyAmbulanceAdapter;
import com.afchealth.afcpharmacyonline.adapter.MyPlasmaAdapter;
import com.afchealth.afcpharmacyonline.data.model.ambulance.AmbulanceData;
import com.afchealth.afcpharmacyonline.data.model.plasma.PlasmaData;
import com.afchealth.afcpharmacyonline.viewmodel.MyAmbulanceModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class MyAmbulanceRequestFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private MyAmbulanceAdapter mAdapter;
    private List<AmbulanceData> mArrayList = new ArrayList<>();
    private MyAmbulanceModel mViewModel;


    public static MyAmbulanceRequestFragment newInstance() {
        return new MyAmbulanceRequestFragment();
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_ambulance_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        getData();
        fab.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyAmbulanceModel.class);
        // TODO: Use the ViewModel
    }

    private void initAdapter() {
        mArrayList = new ArrayList<>();
        mAdapter = new MyAmbulanceAdapter(getActivity(), mArrayList) ;

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_divider));
//        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(mAdapter);
    }

    private void getData() {
        AmbulanceData ambulanceData = new AmbulanceData("1", "My Ambulance", "Ambulance", "4.05AM");
        mArrayList.add(ambulanceData);
        ambulanceData = new AmbulanceData("1", "test", "test", "4.05AM");
        mArrayList.add(ambulanceData);
        mAdapter.notifyDataSetChanged();
        //return mArrayList;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                break;
        }
    }

}
