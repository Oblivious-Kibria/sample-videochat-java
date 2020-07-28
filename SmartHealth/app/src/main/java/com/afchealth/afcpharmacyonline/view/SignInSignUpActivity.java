package com.afchealth.afcpharmacyonline.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afchealth.afcpharmacyonline.R;

import butterknife.BindView;
import butterknife.ButterKnife;




public class SignInSignUpActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.male)
    TextView male;
    @BindView(R.id.female)
    TextView female;
    @BindView(R.id.signIn)
    LinearLayout signIn;
    @BindView(R.id.signUp)
    LinearLayout signUp;
    @BindView(R.id.otpView)
    LinearLayout otpView;
    @BindView(R.id.new_sign_in)
    TextView newSignUp;
    @BindView(R.id.have_account)
    TextView have_account;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.btnLogInConfirm)
    Button btnSignIn;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.otpSubmit)
    Button otpSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);

        ButterKnife.bind(this);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        newSignUp.setOnClickListener(this);
        back.setOnClickListener(this);
        have_account.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        otpSubmit.setOnClickListener(this);

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.male:
                male.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_navy_blue_large));
                female.setBackgroundResource(0);
                male.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                female.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                break;
            case R.id.female:
                female.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_navy_blue_large));
                male.setBackgroundResource(0);
                female.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                male.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                break;
            case R.id.new_sign_in:
                signIn.setVisibility(View.GONE);
                otpView.setVisibility(View.GONE);
                signUp.setVisibility(View.VISIBLE);
                break;
            case R.id.have_account:
            case R.id.back:
                otpView.setVisibility(View.GONE);
                signUp.setVisibility(View.GONE);
                signIn.setVisibility(View.VISIBLE);
                break;

            case R.id.btnLogInConfirm:
                signIn.setVisibility(View.GONE);
                otpView.setVisibility(View.VISIBLE);
                signUp.setVisibility(View.GONE);
                break;
            case R.id.btnSignUp:
                signIn.setVisibility(View.GONE);
                otpView.setVisibility(View.VISIBLE);
                signUp.setVisibility(View.GONE);
                break;
            case R.id.otpSubmit:
                navigateToMainActivity();
                break;
        }
    }


    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
