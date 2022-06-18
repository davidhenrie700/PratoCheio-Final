package tcc.fatec.firebase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import tcc.fatec.firebase.data.repository.AuthenticationRepository;
import tcc.fatec.firebase.model.UserDetailsModel;

public class HomeViewModel extends AndroidViewModel {

    private AuthenticationRepository repository;

    private MutableLiveData<Boolean> loggedStatus;
    private MutableLiveData<UserDetailsModel> userDetailsData;

    public MutableLiveData<UserDetailsModel> getUserDetailsData() {
        return userDetailsData;
    }

    public MutableLiveData<Boolean> getLoggedStatus() {
        return loggedStatus;
    }

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new AuthenticationRepository();

        loggedStatus = repository.getUserLoggedMutableLiveData();
        userDetailsData = repository.getUserDetailMutableLiveData();
    }

    public void signOut(){
        repository.signOut();
    }
}