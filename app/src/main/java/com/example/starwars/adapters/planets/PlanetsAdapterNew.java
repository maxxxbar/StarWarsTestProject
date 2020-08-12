package com.example.starwars.adapters.planets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwars.R;
import com.example.starwars.adapters.OnItemCLickListener;
import com.example.starwars.databinding.PlanetsListBinding;
import com.example.starwars.entries.planets.Result;

public class PlanetsAdapterNew extends PagedListAdapter<Result, PlanetsAdapterNew.PlanetsAdapterNewViewHolder>
        implements OnItemCLickListener {

    private OnItemCLickListener onItemCLickListener;

    public PlanetsAdapterNew() {
        super(Result.CALLBACK);
    }


    @NonNull
    @Override
    public PlanetsAdapterNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlanetsListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.planets_list, parent, false);
        return new PlanetsAdapterNewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetsAdapterNewViewHolder holder, int position) {
        Result result = getItem(position);
        holder.binding.setPlanetList(result);
    }

    @Override
    public void OnItemClick(Object object) {

    }


    class PlanetsAdapterNewViewHolder extends RecyclerView.ViewHolder {

        private PlanetsListBinding binding;

        public PlanetsAdapterNewViewHolder(@NonNull PlanetsListBinding planetsListBinding) {
            super(planetsListBinding.getRoot());
            this.binding = planetsListBinding;

            binding.getRoot()
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemCLickListener != null) {
                                onItemCLickListener.OnItemClick(getItem(getAdapterPosition()));
                            }
                        }
                    });


        }
    }

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.onItemCLickListener = onItemCLickListener;
    }
}
