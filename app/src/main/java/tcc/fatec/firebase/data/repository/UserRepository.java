package tcc.fatec.firebase.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tcc.fatec.firebase.BuildConfig;
import tcc.fatec.firebase.model.UserDetailsModel;
import tcc.fatec.firebase.util.UserType;

public class UserRepository {

    private UserRepository() {}

    public static void registerUserDetails(UserDetailsModel user, String id) {
        FirebaseDatabase.getInstance().getReference()
                .child("user")
                .child(BuildConfig.FIREBASE_FLAVOR_COLLECTION)
                .child(id)
                .setValue(user)
                .addOnCompleteListener(task -> {});
    }
    public void getUserDonatario(String id, OnGetDataListener onGetDataListener) {
        onGetDataListener.onStart();
        FirebaseDatabase.getInstance()
                .getReference()
                .child("user")
                .child("donatario")
                .child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        onGetDataListener.onSuccess(snapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}