package tcc.fatec.firebase.ui.fragment.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import tcc.fatec.firebase.R;
import tcc.fatec.firebase.databinding.FragmentNotificationBinding;
import tcc.fatec.firebase.viewmodel.HomeViewModel;

public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding binding;
    private HomeViewModel mViewModel;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mViewModel.getLoggedStatus().observe(this, aBoolean -> {
        });
    //    mViewModel.getUserDetailsData().observe(this, user -> binding.teste.setText(user.getNome()));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSignOut.setOnClickListener(v -> mViewModel.signOut());
    }
}