package tcc.fatec.firebase.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tcc.fatec.firebase.model.DonateModel;
import tcc.fatec.firebase.util.StatusDonateEnum;

public class DonateRepository {

    public void mReadDataOnce(String child, StatusDonateEnum status, final OnGetDataListener listener) {
        listener.onStart();

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child(child)
                .orderByChild("status")
                .equalTo(status.getCodigo())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        listener.onSuccess(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println(databaseError.getMessage() + " aqui");
                    }
                });
    }

    public void updateStatusAccept(DonateModel donateModel) {
        donateModel.setId("2");
        FirebaseDatabase.getInstance().getReference()
                .child("doacao")
                .child(donateModel.getId())
                .setValue(donateModel)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) System.out.println("Sucesso");
                    else System.out.println("Error " + task.getException().getMessage());
                });
    }
}