package com.afchealth.afcpharmacyonline;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


import com.afchealth.afcpharmacyonline.videocalling.util.QBResRequestExecutor;
import com.quickblox.BuildConfig;
import com.quickblox.auth.session.QBSettings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class App extends MultiDexApplication {
    public static final String TAG = App.class.getSimpleName();
    private static App mInstance;
//    private RequestQueue mRequestQueue;
//    private Tracker mTracker;

    public static synchronized App getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }

//    /**
//     * Gets the default {@link Tracker} for this {@link Application}.
//     *
//     * @return tracker
//     */
//    synchronized public Tracker getDefaultTracker() {
//        if (mTracker == null) {
//            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
//            mTracker = analytics.newTracker(R.xml.global_tracker);
//        }
//        return mTracker;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        //printKeyHash();
        mInstance = this;
      //  Firebase.setAndroidContext(this);
        //FirebaseMessaging.getInstance().subscribeToTopic("shibly");

        // For Video calling;
      //  initFabric();
        checkAppCredentials();
        initCredentials();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    /**
     * Call this method inside onCreate once to get your hash key
     */
    public void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.afchealth.smartphysician", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
               // ShowLog.e("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
        }
    }

//    public RequestQueue getRequestQueue() {
//        if (mRequestQueue == null) {
//            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
//        }
//
//        return mRequestQueue;
//    }

//    public <T> void addToRequestQueue(Request<T> req, String tag) {
//        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
//        getRequestQueue().add(req);
//    }

//    public <T> void addToRequestQueue(Request<T> req) {
//        req.setTag(TAG);
//        getRequestQueue().add(req);
//    }
//
//    public void cancelPendingRequests(Object tag) {
//        if (mRequestQueue != null) {
//            mRequestQueue.cancelAll(tag);
//        }
//    }


    // For video calling;

    //App credentials
    private static final String APPLICATION_ID = "84711";
    private static final String AUTH_KEY = "GHOCOFR2wmDSm3O";
    private static final String AUTH_SECRET = "yZRCyzt4p4K--6z";
    private static final String ACCOUNT_KEY = "2C31UKMRzHyxhmfv8sab";

    public static final String USER_DEFAULT_PASSWORD = "quickblox";

    private static App instance;
    private QBResRequestExecutor qbResRequestExecutor;



//    private void initFabric() {
//        if (!BuildConfig.DEBUG) {
//            Fabric.with(this, new Crashlytics());
//        }
//    }

    private void checkAppCredentials() {
        if (APPLICATION_ID.isEmpty() || AUTH_KEY.isEmpty() || AUTH_SECRET.isEmpty() || ACCOUNT_KEY.isEmpty()) {
            throw new AssertionError(getString(R.string.error_credentials_empty));
        }
    }

    private void initCredentials() {
        QBSettings.getInstance().init(getApplicationContext(), APPLICATION_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);
        QBSettings.getInstance().setEnablePushNotification(true);

        // Uncomment and put your Api and Chat servers endpoints if you want to point the sample
        // against your own server.
        //
        // QBSettings.getInstance().setEndpoints("https://your_api_endpoint.com", "your_chat_endpoint", ServiceZone.PRODUCTION);
        // QBSettings.getInstance().setZone(ServiceZone.PRODUCTION);
    }

    public synchronized QBResRequestExecutor getQbResRequestExecutor() {
        return qbResRequestExecutor == null
                ? qbResRequestExecutor = new QBResRequestExecutor()
                : qbResRequestExecutor;
    }


}