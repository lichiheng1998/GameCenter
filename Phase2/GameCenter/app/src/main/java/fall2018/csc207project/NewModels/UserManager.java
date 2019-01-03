package fall2018.csc207project.NewModels;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.List;

@SuppressWarnings("unchecked")
public class UserManager{

    private FirebaseUser currentUser;
    private FirebaseAuth auth;
    public UserManager(FirebaseAuth auth){
        this.auth = auth;
        this.currentUser = auth.getCurrentUser();
    }

    public void updateNickName(final String name, final OnUserNickNameChanged receiver){
        if (currentUser != null){
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build();
            currentUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        receiver.onUserNickNameChanged(name);
                    } else {
                        receiver.onUserNickNameChanged(null);
                    }
                }
            });
        } else {
            receiver.onUserNickNameChanged(null);
        }
    }

    public void updateUserProfileImage(Uri imageUri, final OnUserProfileImageUpdated receiver, FirebaseStorage storage){
        final StorageReference storageRef = storage.getReference();
        final StorageReference imgRef = storageRef.child("images/" + currentUser.getUid() + "/profilePicture");
        imgRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    receiver.onUserProfileImageUpdated(storageRef.child("images/" + currentUser.getUid() + "/profilePicture"));
                } else {
                    receiver.onUserProfileImageUpdated(null);
                }
            }
        });
    }

    public StorageReference getUserProfileImage(FirebaseStorage storage){
        StorageReference storageRef = storage.getReference();
        return storageRef.child("images/" + currentUser.getUid() + "/profilePicture");
    }

    public void addGame(final List<String> game, final OnAddGameReady receiver, FirebaseFirestore database){
        database.collection("users").document(currentUser.getUid())
                .update("gameList", FieldValue.arrayUnion(game)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    receiver.onAddGameReady(game);
                } else {
                    receiver.onAddGameReady(null);
                }
            }
        });
    }

    public void getGameList(final OnGameListReady receiver, final FirebaseFirestore database){
        database.collection("users").document(currentUser.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                List<String> gameList = null;
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Object data = document.get("gameList");
                        gameList = data == null ? null : (List<String>)data;
                    }
                }
                receiver.onGameListReady(gameList);
            }
        });
    }

    public String getUserNickName(){
        return currentUser.getDisplayName();
    }

    public void logOut(){
        auth.signOut();
    }

    public interface OnAddGameReady{
        void onAddGameReady(List<String> game);
    }

    public interface OnGameListReady{
        void onGameListReady(List<String> gameList);
    }

    public interface OnUserNickNameChanged{
        void onUserNickNameChanged(String name);
    }

    public interface OnUserProfileImageUpdated {
        void onUserProfileImageUpdated(StorageReference name);
    }

}