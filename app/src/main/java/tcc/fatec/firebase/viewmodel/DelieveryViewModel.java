package tcc.fatec.firebase.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import tcc.fatec.firebase.data.repository.DonateRepository;
import tcc.fatec.firebase.data.repository.OnGetDataListener;
import tcc.fatec.firebase.model.DonateModel;
import tcc.fatec.firebase.model.UserDetailsModel;
import tcc.fatec.firebase.util.StatusDonateEnum;

public class DelieveryViewModel extends AndroidViewModel {

    MutableLiveData<List<DonateModel>> donateLiveData;
    MutableLiveData<Integer> progressBarLiveData;
    List<DonateModel> donateList;
    List<DonateModel> teste = new ArrayList<>();

    public DelieveryViewModel(@NonNull Application application) {
        super(application);
        donateLiveData = new MutableLiveData<>();
        progressBarLiveData = new MutableLiveData<>();
        populateList();
    }

    public MutableLiveData<List<DonateModel>> getDonateLiveData() {
        populateList();

        System.out.println(donateLiveData.getValue());
        return donateLiveData;
    }

    public MutableLiveData<Integer> getProgressBarLiveData() {
        return progressBarLiveData;
    }

    public void populateList() {
        new DonateRepository().mReadDataOnce("doacao", StatusDonateEnum.ACEITO, new OnGetDataListener() {
            @Override
            public void onStart() {
                progressBarLiveData.postValue(View.VISIBLE);
            }

            @Override
            public void onSuccess(DataSnapshot snapshot) {
                donateList = new ArrayList<>();
                snapshot.getChildren().forEach(snap -> {
                    var user = snap.child("user").getValue(UserDetailsModel.class);
                    var doacao = snap.getValue(DonateModel.class);
                    doacao.setUser(user);
                    donateList.add(doacao);
                    teste.add(doacao);
                });
                donateLiveData.postValue(donateList);
                progressBarLiveData.postValue(View.INVISIBLE);
            }
        });
    }
}
