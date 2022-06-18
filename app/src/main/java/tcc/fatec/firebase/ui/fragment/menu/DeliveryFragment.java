package tcc.fatec.firebase.ui.fragment.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.fatec.firebase.R;
import tcc.fatec.firebase.databinding.FragmentDeliveryBinding;
import tcc.fatec.firebase.ui.adapter.DelieveryAdapter;
import tcc.fatec.firebase.viewmodel.DelieveryViewModel;
import tcc.fatec.firebase.viewmodel.SearchViewModel;


public class DeliveryFragment extends Fragment {

    private FragmentDeliveryBinding binding;
    private DelieveryViewModel viewModel;
    private DelieveryAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new DelieveryAdapter();

        viewModel = new ViewModelProvider(this).get(DelieveryViewModel.class);
        viewModel.getDonateLiveData().observe(this, donateModels -> {
            adapter.updateDonateList(donateModels);
        });

   //     viewModel.getProgressBarLiveData().observe(this, progress -> binding.progressBar.setVisibility(progress));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDeliveryBinding.inflate(getLayoutInflater());
        binding.txtDonateDelivery.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.txtDonateDelivery.setHasFixedSize(true);
        binding.txtDonateDelivery.setAdapter(adapter);

        return binding.getRoot();
    }
}