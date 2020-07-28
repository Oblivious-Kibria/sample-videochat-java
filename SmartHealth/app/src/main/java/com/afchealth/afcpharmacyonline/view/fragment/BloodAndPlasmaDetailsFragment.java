package com.afchealth.afcpharmacyonline.view.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afchealth.afcpharmacyonline.R;
import com.afchealth.afcpharmacyonline.viewmodel.BloodAndPlasmaDetailsViewModel;




public class BloodAndPlasmaDetailsFragment extends Fragment {

    private BloodAndPlasmaDetailsViewModel mViewModel;




    public static BloodAndPlasmaDetailsFragment newInstance() {
        return new BloodAndPlasmaDetailsFragment();
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.blood_and_plasma_details_fragment, container, false);
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BloodAndPlasmaDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}
