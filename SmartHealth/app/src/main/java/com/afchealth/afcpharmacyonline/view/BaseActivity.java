package com.afchealth.afcpharmacyonline.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;




/**
 * Created by User on 12/26/2019.
 */
public class BaseActivity extends AppCompatActivity {


    private Snackbar snackbar;


    // Image capturing functionality;
    private static final String TAG = "BaseActivity";
    private static final int CAMERA_PERMISSION_SETTING_REQUEST_CODE = 101;
    private static final int CAMERA_ACTION_REQUEST_CODE = 100;
    private static final int GALLERY_ACTION_REQUEST_CODE = 102;
    private static final int LOCATION_PERMISSION_SETTING_REQUEST_CODE = 103;

//    private MyImage myImage;
//    private ImageCompressionListener imageCompressionListener = null;
    private String[] picturePermissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private String[] locationPermissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};




//    public void askCameraPermissions(ImageCompressionListener imageCompressionListener) {
//        this.imageCompressionListener = imageCompressionListener;
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            // Permission not found;
//            requestCameraPermission();
//        } else {
//            // Permission found;
//            showIntentChooserDialog();
//        }
//    }




    private void requestCameraPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        picturePermissions[0],
                        picturePermissions[1],
                        picturePermissions[2])
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                           // showIntentChooserDialog();
                            Log.d(TAG, "PermissionTesting  onPermissionGranted");
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            Log.d(TAG, "PermissionTesting:  isAnyPermissionPermanentlyDenied");
                            // permission is denied permanently, navigate user to app settings
                            showSettingsDialog();
                        }
                    }




                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Log.d(TAG, "PermissionTesting  onPermissionRationaleShouldBeShown");
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(error -> Toast.makeText(BaseActivity.this, "Permission request error!", Toast.LENGTH_SHORT).show())
                .onSameThread()
                .check();
    }




    private void showSettingsDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", (DialogInterface dialog, int which) -> {
            dialog.cancel();
            openCameraSettings();
        });
        builder.setNegativeButton("Cancel", (DialogInterface dialog, int which) ->
                dialog.cancel()
        );
        builder.show();
    }




    private void openCameraSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, CAMERA_PERMISSION_SETTING_REQUEST_CODE);
    }




//    private void openCamera() {
//        Uri photoUri;
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                myImage = ImageUtils.createImageFile(this);
//                photoFile = myImage.getImageFile();
//            } catch (IOException ex) {
//
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                photoUri = FileProvider.getUriForFile(this,
//                        BuildConfig.APPLICATION_ID + ".provider",
//                        photoFile);
//
//                List<ResolveInfo> resolvedIntentActivities = this.getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
//
//                for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
//                    String packageName = resolvedIntentInfo.activityInfo.packageName;
//
//                    this.grantUriPermission(packageName, photoUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                }
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                this.startActivityForResult(takePictureIntent, CAMERA_ACTION_REQUEST_CODE);
//            }
//        }
//    }




    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_ACTION_REQUEST_CODE);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == CAMERA_ACTION_REQUEST_CODE && resultCode == this.RESULT_OK) {
//            // image takes successfully;
//            if (myImage.getImageFile() != null) {
//                compressImage(myImage.getImageFile());
//            }
//        } else if (requestCode == GALLERY_ACTION_REQUEST_CODE && resultCode == this.RESULT_OK) {
//            if (data != null) {
//                Uri selectedImage = data.getData();
//                String realPath = RealPathUtil.getRealPath(this, selectedImage);
//                File imageFile = new File(realPath);
//                compressImage(imageFile);
//            }
//        }
//        else if (requestCode == LOCATION_PERMISSION_SETTING_REQUEST_CODE && resultCode == this.RESULT_OK) {
//            getCurrentLocation();
//        }

    }




//    private void compressImage(File actualImageFile) {
//        new Compressor(getApplicationContext())
//                .compressToFileAsFlowable(actualImageFile)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((File file) -> {
//
//                    try {
//                        if (imageCompressionListener != null) {
//                            imageCompressionListener.onCompression(file);
//                            //Log.d(TAG, "File Compression Done");
//                        } else {
//                            //Log.d(TAG, "File Compression Failed 1");
//                            Toast.makeText(BaseActivity.this, "Image compression error", Toast.LENGTH_SHORT).show();
//                        }
//
//                    } catch (Exception e) {
//                        //Log.d(TAG, "File Compression Failed 2");
//                        Toast.makeText(BaseActivity.this, "Image compression error", Toast.LENGTH_SHORT).show();
//                    }
//
//                });
//    }




//    public void showIntentChooserDialog() {
//        chooseHowToTakeImage(this, (String intentType) -> {
//            if (intentType.equals("CAMERA")) {
//                // Camera;
//                openCamera();
//            } else {
//                // Gallery;
//                openGallery();
//            }
//        });
//    }




//    public void chooseHowToTakeImage(Context context, final IntentChooserListener intentChooserInterface) {
//        final MaterialDialog dialog = new MaterialDialog.Builder(context)
//                .title("Choose Image")
//                .customView(R.layout.layout_intent_chooser, false)
//                .negativeText("Cancel")
//                .onNegative((@NonNull MaterialDialog dialog1, @NonNull DialogAction which) ->
//                        dialog1.dismiss())
//                .backgroundColor(Color.parseColor("#FFFFFF"))
//                .titleColor(getResources().getColor(R.color.color_text_prime_lite))
//                .contentColor(getResources().getColor(R.color.color_text_prime_lite))
//                .positiveColor(getResources().getColor(R.color.color_text_prime_lite))
//                .negativeColor(getResources().getColor(R.color.color_orange))
//                .show();
//
//        View view = dialog.getCustomView();
//
//        ImageView galleryIntent = view.findViewById(R.id.galleryIntent);
//        final ImageView cameraIntent = view.findViewById(R.id.cameraIntent);
//
//
//        galleryIntent.setOnClickListener((View v) -> {
//            intentChooserInterface.onChoose("GALLERY");
//            dialog.dismiss();
//        });
//
//        cameraIntent.setOnClickListener((View v) -> {
//            intentChooserInterface.onChoose("CAMERA");
//            dialog.dismiss();
//        });
//    }
//



    protected void setImageWithGlide(ImageView imageView, File file, int width, int height) {
        Glide.with(this)
                .load(file)
                .apply(new RequestOptions().override(width, height))
                .into(imageView);
    }


    // Custom dialog layout;




//    public void showDialogAfterApiResponse(String title, String messageBody) {
//        final PopupWindow popup = new PopupWindow();
//        popup.setAnimationStyle(R.style.JobDoneDialogAnimation);
//        popup.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View layout = inflater.inflate(R.layout.layout_dialog_done, null);
//
//        AppCompatTextView tvTitle = layout.findViewById(R.id.tv_title);
//        AppCompatTextView tvBody = layout.findViewById(R.id.tv_body);
//        AppCompatTextView tvOk = layout.findViewById(R.id.tv_ok);
//
//        tvTitle.setText(title);
//        tvBody.setText(messageBody);
//        popup.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//
//        tvOk.setOnClickListener(v -> {
//                    popup.dismiss();
//                }
//        );
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int width = displayMetrics.widthPixels;
//
//        popup.setContentView(layout);
//        // Set content width and height
//        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        popup.setWidth(width - 100);
//        popup.setOutsideTouchable(true);
//        popup.setFocusable(true);
//        popup.update();
//
//        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            popup.setElevation(10);
//        }
//        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);
//    }




    public static void applyDim(@NonNull ViewGroup parent, float dimAmount) {
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * dimAmount));

        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }




    public static void clearDim(@NonNull ViewGroup parent) {
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }




    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }




    public void showSnackBarWhenNetworkNotAvailable(View parentLayout) {
        snackbar = Snackbar.make(parentLayout, "Internet not available", Snackbar.LENGTH_INDEFINITE)
                .setAction("Settings", (View view) -> {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                });
        snackbar.show();
    }




    public void hideSnackBarWhenNetAvailable() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }




    public void showErrorToast(Throwable error) {
        if (error instanceof HttpException) {
            switch (((HttpException) error).code()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                    Toast.makeText(this, "Unauthorised user.", Toast.LENGTH_SHORT).show();
                    break;
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    Toast.makeText(this, "Password didn't match.", Toast.LENGTH_SHORT).show();
                    break;
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                    Toast.makeText(this, "Internal server error", Toast.LENGTH_SHORT).show();
                    break;
                case HttpsURLConnection.HTTP_BAD_REQUEST:
                    Toast.makeText(this, "Bad request", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}
