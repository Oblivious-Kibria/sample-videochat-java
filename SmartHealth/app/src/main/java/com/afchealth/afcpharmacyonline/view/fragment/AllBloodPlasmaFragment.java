package com.afchealth.afcpharmacyonline.view.fragment;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afchealth.afcpharmacyonline.Interface.BPItemClicklistener;
import com.afchealth.afcpharmacyonline.R;
import com.afchealth.afcpharmacyonline.adapter.AllPlasmaAdapter;
import com.afchealth.afcpharmacyonline.data.model.plasma.PlasmaData;
import com.afchealth.afcpharmacyonline.viewmodel.AllPostViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class AllBloodPlasmaFragment extends Fragment implements BPItemClicklistener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AllPostViewModel mViewModel;
    private AllPlasmaAdapter mAdapter;
    private List<PlasmaData> mArrayList = new ArrayList<>();



    public static AllBloodPlasmaFragment newInstance() {
        return new AllBloodPlasmaFragment();
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.all_blood_plasma_fragment, container, false);
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
        PlasmaData plasmaData = new PlasmaData("1", "test", "test", "4.05AM");
        mArrayList.add(plasmaData);
        plasmaData = new PlasmaData("1", "test", "test", "4.05AM");
        mArrayList.add(plasmaData);
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

        mViewModel = ViewModelProviders.of(this).get(AllPostViewModel.class);
        // TODO: Use the ViewModel


    }

    private void initAdapter() {
        mArrayList = new ArrayList<>();
        mAdapter = new AllPlasmaAdapter(getActivity(), mArrayList, this);
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
    public void onItemClick(PlasmaData plasmaData) {
        BloodAndPlasmaDetailsFragment bloodAndPlasmaDetailsFragment = new BloodAndPlasmaDetailsFragment();
        showFragment(bloodAndPlasmaDetailsFragment);
    }

}
