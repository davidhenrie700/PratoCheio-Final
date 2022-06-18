package tcc.fatec.firebase.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tcc.fatec.firebase.R;
import tcc.fatec.firebase.model.DonateModel;
import tcc.fatec.firebase.ui.fragment.DonateAcceptedFragment;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private List<DonateModel> donateList;
    private List<DonateModel> donateListSearch;

    public SearchAdapter() {
        this.donateList = new ArrayList<>();
        this.donateListSearch = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonateModel donate = donateListSearch.get(position);
        holder.txtAlimento.setText(donate.getAlimento());
        holder.txtData.setText(donate.getData());
        holder.txtNome.setText(donate.getUser().getNome());

        holder.cardView.setOnClickListener(v -> new DonateAcceptedFragment(holder.itemView.getContext(), donate));
    }

    public void updateDonateList(final List<DonateModel> donateList) {
        this.donateListSearch.clear();
        this.donateList.clear();

        this.donateListSearch = donateList;
        this.donateList.addAll(donateList);
        notifyDataSetChanged();
    }

    public boolean search(String query) {
        donateListSearch.clear();
        donateListSearch.addAll(donateList.stream().filter(i -> i.getAlimento().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList()));

        notifyDataSetChanged();
        return donateListSearch.isEmpty();
    }

    @Override
    public int getItemCount() {
        return donateListSearch.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome;
        TextView txtAlimento;
        TextView txtData;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtNome = itemView.findViewById(R.id.txtViewNome);
            this.txtAlimento = itemView.findViewById(R.id.textViewAlimento);
            this.txtData = itemView.findViewById(R.id.textViewData);
            this.cardView = itemView.findViewById(R.id.cardClick);
        }
    }
}