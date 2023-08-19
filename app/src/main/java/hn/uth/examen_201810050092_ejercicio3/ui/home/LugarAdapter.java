package hn.uth.examen_201810050092_ejercicio3.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hn.uth.examen_201810050092_ejercicio3.OnItemClickListener;
import hn.uth.examen_201810050092_ejercicio3.databinding.LugarItemBinding;
import hn.uth.examen_201810050092_ejercicio3.entitys.Lugar;

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolder> {
    List<Lugar> dataset;
    private OnItemClickListener<Lugar> manejadorEventoClick;
    public LugarAdapter(List<Lugar>dataset,  OnItemClickListener<Lugar> manejadorEventoClick)  {
        this.dataset=dataset;
        this.manejadorEventoClick = manejadorEventoClick;

    }

    @NonNull
    @Override
    public LugarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LugarItemBinding binding = LugarItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LugarAdapter.ViewHolder holder, int position) {
        Lugar lugar = dataset.get(position);
        holder.binding.txtNombreLugar.setText(lugar.getLugar());
        holder.binding.txtdescripcion.setText(lugar.getDescripcion());
        holder.setOnClickListener(lugar,manejadorEventoClick);


    }

    @Override
    public int getItemCount() {

        return dataset.size();

    }

    public void setItems(List<Lugar> lugares){
        this.dataset = lugares;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        LugarItemBinding binding;


        public ViewHolder(@NonNull  LugarItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void  setOnClickListener(Lugar nombreLugar, OnItemClickListener<Lugar> clickListener){
            this.binding.verLugar.setOnClickListener(v -> clickListener.onItemClick(nombreLugar,0));


        }


    }


}