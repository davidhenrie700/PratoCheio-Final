package tcc.fatec.firebase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import tcc.fatec.firebase.data.repository.AuthenticationRepository;
import tcc.fatec.firebase.model.UserDetailsModel;

public class LoginViewModel extends AndroidViewModel {

    private AuthenticationRepository repository;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<String> errorMessageData;
    private MutableLiveData<UserDetailsModel> userDetailsData;

    public MutableLiveData<UserDetailsModel> getUserDetailsData() {
        return userDetailsData;
    }

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<String> getErrorMessageData() {
        return errorMessageData;
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new AuthenticationRepository();
        userData = repository.getFirebaseUserMutableLiveData();
        errorMessageData = repository.getErrorMessageMutableLiveData();
        userDetailsData = repository.getUserDetailMutableLiveData();
    }

    public void signIn(String email , String pass){
        repository.login(email, pass);
    }
}