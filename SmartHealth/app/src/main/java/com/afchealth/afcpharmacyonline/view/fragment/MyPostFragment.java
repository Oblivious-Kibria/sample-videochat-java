package com.afchealth.afcpharmacyonline.view.fragment;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afchealth.afcpharmacyonline.R;
import com.afchealth.afcpharmacyonline.adapter.AllPlasmaAdapter;
import com.afchealth.afcpharmacyonline.adapter.MyPlasmaAdapter;
import com.afchealth.afcpharmacyonline.data.model.plasma.PlasmaData;
import com.afchealth.afcpharmacyonline.viewmodel.AllPostViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class MyPostFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private MyPlasmaAdapter mAdapter;
    private List<PlasmaData> mArrayList = new ArrayList<>();
    private MyPostViewModel mViewModel;


    public static MyPostFragment newInstance() {
        return new MyPostFragment();
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_post_fragment, container, false);
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
        mViewModel = ViewModelProviders.of(this).get(MyPostViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initAdapter() {
        mArrayList = new ArrayList<>();
        mAdapter = new MyPlasmaAdapter(getActivity(), mArrayList, (view, position) -> {

        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_divider));
//        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(mAdapter);
    }

    private void getData() {
        PlasmaData plasmaData = new PlasmaData("1", "User", "test", "4.05AM");
        mArrayList.add(plasmaData);
        plasmaData = new PlasmaData("1", "test", "test", "4.05AM");
        mArrayList.add(plasmaData);
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


//    private void postDialog(){
//        LayoutInflater factory = LayoutInflater.from(getActivity());
//        final View deleteDialogView = factory.inflate(R.layout.post_create_dialog, null);
//      //  TextView text = (TextView) deleteDialogView.findViewById(R.id.text_dialog);
//        TextView method = deleteDialogView.findViewById(R.id.method);
//
//        final AlertDialog deleteDialog = new AlertDialog.Builder(getActivity()).create();
//        deleteDialog.setCancelable(false);
//        if (deleteDialog.isShowing())
//            deleteDialog.dismiss();
//        deleteDialog.setView(deleteDialogView);
//        deleteDialogView.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });
//        deleteDialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //your business logic
//                deleteDialog.dismiss();
//            }
//        });
//
//
//        deleteDialog.show();
//
//    }
}
