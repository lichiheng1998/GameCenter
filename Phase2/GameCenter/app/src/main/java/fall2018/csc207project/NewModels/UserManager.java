package fall2018.csc207project.NewModels;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class UserManager {

    private FirebaseUser currentUser;
    private FirebaseAuth auth;
    public UserManager(FirebaseAuth auth){
        this.auth = auth;
        this.currentUser = auth.getCurrentUser();
    }

    public Task<Void> updateNickName(String name){
        if (currentUser != null){
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build();
            return currentUser.updateProfile(profileUpdates);
        } else {
            return null;
        }
    }

    public UploadTask updateUserProfileImage(Uri imageUri, FirebaseStorage storage){
        StorageReference storageRef = storage.getReference();
        StorageReference imgRef = storageRef.child("images/" + currentUser.getUid() + "/profilePicture");
        return imgRef.putFile(imageUri);
    }

    public StorageReference getUserProfileImage(FirebaseStorage storage){
        StorageReference storageRef = storage.getReference();
        return storageRef.child("images/" + currentUser.getUid() + "/profilePicture");
    }

    public String getUserNickName(){
        return currentUser.getDisplayName();
    }

    public void logOut(){
        auth.signOut();
    }

}
