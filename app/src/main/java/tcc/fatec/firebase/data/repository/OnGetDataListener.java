package tcc.fatec.firebase.data.repository;

import com.google.firebase.database.DataSnapshot;

public interface OnGetDataListener {
    public void onStart();
    public void onSuccess(DataSnapshot snapshot);
}
