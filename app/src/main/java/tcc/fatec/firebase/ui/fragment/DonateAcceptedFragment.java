package tcc.fatec.firebase.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import tcc.fatec.firebase.R;
import tcc.fatec.firebase.data.repository.DonateRepository;
import tcc.fatec.firebase.databinding.FragmentDonateAcceptedBinding;
import tcc.fatec.firebase.model.DonateModel;
import tcc.fatec.firebase.util.StatusDonateEnum;
import tcc.fatec.firebase.viewmodel.DonateAcceptedFragmentViewModel;

public class DonateAcceptedFragment extends BottomSheetDialog {

    FragmentDonateAcceptedBinding binding;
    DonateRepository repository;

    public DonateAcceptedFragment(@NonNull Context context, DonateModel donateModel) {
        super(context);

        repository = new DonateRepository();

        binding = FragmentDonateAcceptedBinding.inflate(getLayoutInflater());
        binding.txtName.setText(donateModel.getUser().getNome());
        binding.textFood.setText(donateModel.getAlimento());
        binding.txtAddress.setText(donateModel.getUser().getEndereco());

        var dialog = new BottomSheetDialog(context, R.style.BottomSheetDialog);
        dialog.setContentView(binding.getRoot());
        dialog.show();

        binding.btnAccept.setOnClickListener(v -> {
            donateModel.setStatus(StatusDonateEnum.ACEITO.getCodigo());
            repository.updateStatusAccept(donateModel);
        });
    }

}