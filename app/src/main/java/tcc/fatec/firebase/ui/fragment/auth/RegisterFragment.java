package tcc.fatec.firebase.ui.fragment.auth;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import tcc.fatec.firebase.R;
import tcc.fatec.firebase.databinding.FragmentRegisterBinding;
import tcc.fatec.firebase.model.EnderecoModel;
import tcc.fatec.firebase.model.UserDetailsModel;
import tcc.fatec.firebase.util.Messages;
import tcc.fatec.firebase.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    private RegisterViewModel mViewModel;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        mViewModel.getUserData().observe(this, firebaseUser -> {
            if (firebaseUser != null) NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_menu_graph);
        });
        mViewModel.getErrorMessageData().observe(this, errorMessage -> {
            if (errorMessage != null) {
                binding.progressBar.setVisibility(View.INVISIBLE);
                Snackbar.make(getView(), errorMessage, Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initClicks();
    }

    private void initClicks() {
        binding.btnRegister.setOnClickListener(v -> validateDate());
    }

    private void validateDate() {
        var endereco = new EnderecoModel();
        endereco.setLogradouro(binding.editTextAdress.getText().toString().trim());
        endereco.setNumero(binding.editTextNumber.getText().toString().trim());
        endereco.setCep(binding.editTextCep.getText().toString().trim());
        endereco.setBairro(binding.editTextNeighborhood.getText().toString().trim());
        endereco.setCidade(binding.editTextCity.getText().toString().trim());
        endereco.setEstado(binding.editTextState.getText().toString().trim());

        var user = new UserDetailsModel();
        user.setNome(binding.editTextName.getText().toString().trim());
        user.setEndereco(endereco.toString());

        var email = binding.editTextEmail.getText().toString().trim();
        var password = binding.editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(binding.getRoot(), Messages.EMPTY_FIELD, BaseTransientBottomBar.LENGTH_LONG).setBackgroundTint(Color.RED).show();
            return;
        }

        binding.progressBar.setVisibility(View.VISIBLE);
        registerUser(email, password, user);
    }

    private void registerUser(String email, String password, UserDetailsModel user) {
        mViewModel.register(email, password, user);
    }
}