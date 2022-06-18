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

import tcc.fatec.firebase.R;
import tcc.fatec.firebase.model.DonateModel;
import tcc.fatec.firebase.ui.fragment.DonateAcceptedFragment;

public class DelieveryAdapter extends RecyclerView.Adapter<DelieveryAdapter.MyViewHolder> {

    private List<DonateModel> donateListSearch;

    public DelieveryAdapter() {
        this.donateListSearch = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donate_delivery, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonateModel donate = donateListSearch.get(position);
        holder.txtAlimento.setText(donate.getAlimento());
        holder.endereco.setText(donate.getUser().getEndereco());
        holder.txtNome.setText(donate.getUser().getNome());
    }

    public void updateDonateList(final List<DonateModel> donateList) {
        this.donateListSearch.clear();
        this.donateListSearch = donateList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return donateListSearch.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome;
        TextView txtAlimento;
        TextView txtData;
        TextView endereco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtNome = itemView.findViewById(R.id.nomeHistorico);
            this.txtAlimento = itemView.findViewById(R.id.alimentoHistorico);
            this.endereco = itemView.findViewById(R.id.enderecoHistorico);
        }
    }
}