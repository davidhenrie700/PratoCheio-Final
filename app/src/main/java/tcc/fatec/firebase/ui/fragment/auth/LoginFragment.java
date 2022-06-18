package tcc.fatec.firebase.ui.fragment.auth;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import tcc.fatec.firebase.R;
import tcc.fatec.firebase.databinding.FragmentLoginBinding;
import tcc.fatec.firebase.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        mViewModel.getUserData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_menu_graph);
            }
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
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initClick();
    }

    private void initClick() {
        binding.btnLogin.setOnClickListener(v ->  validateDate());
        binding.btnRegister.setOnClickListener(v -> NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_loginFragment_to_registerFragment));
    }

    private void validateDate() {
        var email = binding.editTextEmail.getText().toString().trim();
        var password = binding.editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(binding.getRoot(), "Insira todas as informações", BaseTransientBottomBar.LENGTH_LONG).setBackgroundTint(Color.RED).show();
            return;
        }
        binding.progressBar.setVisibility(View.VISIBLE);
        loginUser(email, password);
    }

    private void loginUser(String email, String password) {
        mViewModel.signIn(email, password);
    }
}