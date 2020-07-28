package com.afchealth.afcpharmacyonline.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.RelativeLayout;

import com.afchealth.afcpharmacyonline.R;
import com.afchealth.afcpharmacyonline.data.preference.AppPreference;
import com.afchealth.afcpharmacyonline.data.preference.PrefKey;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;




public class SplashActivity extends BaseActivity {
    @BindView(R.id.rl_splash_screen)
    ConstraintLayout clSplashScreen;
    private AppPreference appPreference;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        appPreference = AppPreference.getInstance(getApplicationContext());
    }


    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void navigateToSignInScreen() {
        Intent intent = new Intent(this, SignInSignUpActivity.class);
        startActivity(intent);
        finish();
    }


    private void startSplashTimer() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            navigateToSignInScreen();
        }, 3000);
    }


    private void registerOnlineChecker() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        this.registerReceiver(mNetworkChangeReceiver, filter);
    }




    private void unregisterOnlineChecker() {
        if (mNetworkChangeReceiver != null) {
            this.unregisterReceiver(mNetworkChangeReceiver);
        }
    }




    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (isOnline()) {
               // hideSnackBarWhenNetAvailable();
                boolean isLogin = appPreference.getBoolean(PrefKey.IS_LOGIN);
                if (isLogin) {
                    navigateToMainActivity();
                } else {
                    navigateToSignInScreen();
                }

            } else {

            }
        }

    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    @Override
    protected void onResume() {
        super.onResume();

        boolean isLogin = appPreference.getBoolean(PrefKey.IS_LOGIN);

        if (isOnline()) {
//            // If user is signed in then load customer and product list; Otherwise move to sign in screen;
            if (isLogin) {
               // viewModel.loadDataFromNetworkCalls();
            } else {
                startSplashTimer();
            }
//            hideSnackBarWhenNetAvailable();
        } else {

            // If internet not available and user is signed in then
            if (isLogin) {
                navigateToMainActivity();
            } else {
                registerOnlineChecker();
                showSnackBarWhenNetworkNotAvailable(clSplashScreen);
                // Show dialog to connect to internet when user installs the app very first time;
            }
        }

        if (isLogin) {
           // initMyWorker();
        }

       // FirebaseCrashlytics.getInstance().setCustomKey("str_key", "hello");
        //throw new RuntimeException("Test Crash");
    }




    @Override
    protected void onPause() {
        super.onPause();
       // splashLoader.clearAnimation();
        unregisterOnlineChecker();
    }



}
