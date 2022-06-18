package tcc.fatec.firebase.ui.fragment.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import tcc.fatec.firebase.R;
import tcc.fatec.firebase.data.repository.DonateRepository;
import tcc.fatec.firebase.data.repository.OnGetDataListener;
import tcc.fatec.firebase.databinding.FragmentSearchBinding;
import tcc.fatec.firebase.model.DonateModel;
import tcc.fatec.firebase.ui.adapter.SearchAdapter;
import tcc.fatec.firebase.viewmodel.LoginViewModel;
import tcc.fatec.firebase.viewmodel.SearchViewModel;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private SearchAdapter adapter;
    private SearchViewModel viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        viewModel.getDonateLiveData().observe(this, donateModels ->  adapter.updateDonateList(donateModels));
        viewModel.getProgressBarLiveData().observe(this, progress -> binding.progressBar.setVisibility(progress));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(getLayoutInflater());

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new SearchAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.menu_pesquisa);
        binding.searchView.setMenuItem(item);

        binding.searchView.setOnQueryTextListener(new SimpleSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(@NonNull String query) {
                //adapter.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(@NonNull String newText) {
                adapter.search(newText);
                return true;
            }

            @Override
            public boolean onQueryTextCleared() {
                return false;
            }
        });


    }
}