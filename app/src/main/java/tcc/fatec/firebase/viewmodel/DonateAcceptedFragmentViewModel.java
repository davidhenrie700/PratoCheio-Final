package tcc.fatec.firebase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.firebase.database.DataSnapshot;

import tcc.fatec.firebase.data.repository.OnGetDataListener;
import tcc.fatec.firebase.data.repository.UserRepository;

public class DonateAcceptedFragmentViewModel extends AndroidViewModel {

    private UserRepository repository;

    public DonateAcceptedFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void teste(String id) {
        repository.getUserDonatario(id, new OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot snapshot) {

            }
        });
    }


}
