package fall2018.csc207project.Views;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

class AvatarHandler {
    private Uri mCropImageUri;
    static final int PICK_BGIMAGE_PERMISSION_REQUEST_CODE = 1;
    static final int PICK_BGIMAGE_REQUEST_CODE = 1;
    private Activity act;

    AvatarHandler(Activity act){
        this.act = act;
    }

    void handleAvatarRequest(int requestCode, int resultCode, Intent data){
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE){
            Uri imageUri = CropImage.getPickImageResultUri(act.getApplicationContext(), data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(act, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri;
                act.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},   CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                // no permissions required or already granted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }
    }

    void handleAvatarPermission(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults){
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode){
                case CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE:
                    CropImage.startPickImageActivity(act);
                    break;
                case CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE:
                    if (mCropImageUri != null) {
                        // required permissions granted, start crop image activity
                        startCropImageActivity(mCropImageUri);
                    }
                    break;
                case PICK_BGIMAGE_PERMISSION_REQUEST_CODE:
                    openGallery();
                    break;
            }
            CropImage.startPickImageActivity(act);
        } else {
            Toast.makeText(act, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG).show();
        }
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setAspectRatio(1,1)
                .setFixAspectRatio(true)
                .setCropShape(CropImageView.CropShape.OVAL)
                .start(act);
    }


    void openGallery(){
        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        act.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_BGIMAGE_REQUEST_CODE);
    }

}
