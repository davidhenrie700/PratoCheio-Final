package tcc.fatec.firebase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import tcc.fatec.firebase.data.repository.AuthenticationRepository;
import tcc.fatec.firebase.model.UserDetailsModel;

public class RegisterViewModel extends AndroidViewModel {

    private AuthenticationRepository repository;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<String> errorMessageData;

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<String> getErrorMessageData() {
        return errorMessageData;
    }

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = new AuthenticationRepository();
        userData = repository.getFirebaseUserMutableLiveData();
        errorMessageData = repository.getErrorMessageMutableLiveData();
    }

    public void register(String email , String password, UserDetailsModel user){
        repository.register(email, password, user);
    }
}