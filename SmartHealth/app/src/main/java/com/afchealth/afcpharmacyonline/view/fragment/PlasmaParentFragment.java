package com.afchealth.afcpharmacyonline.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afchealth.afcpharmacyonline.R;

import butterknife.BindView;
import butterknife.ButterKnife;




/**
 * A simple {@link Fragment} subclass.
 */
public class PlasmaParentFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.allPost)
    TextView allPost;
    @BindView(R.id.myPost)
    TextView myPost;
    public PlasmaParentFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plasma_parent, container, false);
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        allPost.setOnClickListener(this);
        myPost.setOnClickListener(this);

        AllBloodPlasmaFragment allPostFragment = new AllBloodPlasmaFragment();
        showFragment(allPostFragment);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.allPost:
                allPost.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rounded_button_navy_blue_large));
                myPost.setBackgroundResource(0);
                allPost.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                myPost.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));

                AllBloodPlasmaFragment allPostFragment = new AllBloodPlasmaFragment();
                showFragment(allPostFragment);
                break;
            case R.id.myPost:
                myPost.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rounded_button_navy_blue_large));
                allPost.setBackgroundResource(0);
                myPost.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                allPost.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                MyPostFragment myPostFragment = new MyPostFragment();
                showFragment(myPostFragment);
                break;

        }
    }


    public void showFragment(Fragment fragment) {
        String TAG = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.plasmaContainer, fragment, TAG);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
