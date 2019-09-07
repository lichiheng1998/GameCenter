package fall2018.csc207project.NewModels;
import android.net.Uri;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void removeGame(final String game, final OnGameReady receiver, final FirebaseFirestore database){
        database.collection("users")
                .document(currentUser.getUid())
                .update("gameList", FieldValue.arrayRemove(game))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    receiver.onGameReady(new ArrayList<>(Collections.singletonList(game)),
                            false);
                }
                receiver.onGameReady(null, false);
            }
        });
    }
    public void addGame(final List<String> game, final OnGameReady receiver,
                           final FirebaseFirestore database){
        final DocumentReference doc = database.collection("users").document(currentUser.getUid());
        doc.get().continueWithTask(new Continuation<DocumentSnapshot, Task<Void>>() {
            @Override
            public Task<Void> then(@NonNull Task<DocumentSnapshot> task) throws Exception {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    Map<String, List<String>> field = new HashMap<>();
                    field.put("gameList", game);
                    if (document.exists()) {
                        game.addAll((ArrayList<String>)document.get("gameList"));
                        field.put("gameList", game);
                    }
                    return doc.set(field, SetOptions.merge());
                }
                receiver.onGameReady(null, true);
                return null;
            }
        }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    receiver.onGameReady(game, true);
                    return;
                }
                receiver.onGameReady(null, true);
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

    public interface OnGameReady{
        void onGameReady(List<String> game, boolean isAdded);
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
