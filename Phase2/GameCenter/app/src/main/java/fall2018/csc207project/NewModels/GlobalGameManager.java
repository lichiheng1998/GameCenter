package fall2018.csc207project.NewModels;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class GlobalGameManager {
    private FirebaseFirestore database;
    public GlobalGameManager(FirebaseFirestore database){
        this.database = database;
    }
    public void getGameCollection(final GameReceiver receiver, final FirebaseStorage storage){
        database.collection("games").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Map<String, StorageReference> gameToCover = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        StorageReference coverReference = storage.getReference().
                                child("gameCovers/" + document.get("gameCover"));
                        gameToCover.put(document.getId(), coverReference);
                    }
                    receiver.onGameCollectionReady(gameToCover);
                } else {
                    receiver.onGameCollectionReady(null);
                }
            }
        });
    }

    public interface GameReceiver{
        void onGameCollectionReady(Map<String, StorageReference> gameCollection);
    }
}
