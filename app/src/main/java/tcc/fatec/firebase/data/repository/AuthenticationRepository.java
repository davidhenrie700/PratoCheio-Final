package tcc.fatec.firebase.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tcc.fatec.firebase.BuildConfig;
import tcc.fatec.firebase.data.firebase.FirebaseHelper;
import tcc.fatec.firebase.model.UserDetailsModel;

public class AuthenticationRepository {

    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> userLoggedMutableLiveData;
    private MutableLiveData<UserDetailsModel> userDetailMutableLiveData;
    private MutableLiveData<String> errorMessageMutableLiveData;

    private FirebaseAuth auth;

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public MutableLiveData<Boolean> getUserLoggedMutableLiveData() {
        return userLoggedMutableLiveData;
    }

    public MutableLiveData<UserDetailsModel> getUserDetailMutableLiveData() {
        return userDetailMutableLiveData;
    }

    public MutableLiveData<String> getErrorMessageMutableLiveData() {
        return errorMessageMutableLiveData;
    }

    public AuthenticationRepository(){
        firebaseUserMutableLiveData = new MutableLiveData<>();
        userLoggedMutableLiveData = new MutableLiveData<>();
        errorMessageMutableLiveData = new MutableLiveData<>();
        userDetailMutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null){
            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());

            FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("user")
                    .child("1")
                    .child(auth.getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            userDetailMutableLiveData.postValue(snapshot.getValue(UserDetailsModel.class));
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("Error", error.getMessage());
                        }
                    });
        }
    }

    public void register(String email , String pass, UserDetailsModel user){
        auth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                    UserRepository.registerUserDetails(user, auth.getUid());
                } else {
                    errorMessageMutableLiveData.postValue(FirebaseHelper.translateError(task.getException().getMessage()));
                }
        });
    }

    public void login(String email , String pass){
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    FirebaseDatabase
                            .getInstance()
                            .getReference()
                            .child("user")
                            .child(BuildConfig.FIREBASE_FLAVOR_COLLECTION)
                            .child(auth.getCurrentUser().getUid())
                            .child("userDoador")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                                    else errorMessageMutableLiveData.postValue("Usuário inválido!");
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.d("Error", error.getMessage());
                                }
                            });
                } else {
                    errorMessageMutableLiveData.postValue(FirebaseHelper.translateError(task.getException().getMessage()));
                }
        });
    }

    public void signOut(){
        auth.signOut();
        userLoggedMutableLiveData.postValue(true);
    }
}
