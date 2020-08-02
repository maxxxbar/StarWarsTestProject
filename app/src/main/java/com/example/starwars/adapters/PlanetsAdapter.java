package com.example.starwars.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwars.R;
import com.example.starwars.entries.planets.Result;

import java.util.ArrayList;
import java.util.List;

public class PlanetsAdapter extends RecyclerView.Adapter<PlanetsAdapter.PlanetsAdapterViewHolder> {

    private List<Result> resultList = new ArrayList<>();
    private OnItemCLickListener onItemCLickListener;


    public interface OnItemCLickListener {
        void OnItemCLick(int position);
    }


    @NonNull
    @Override
    public PlanetsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planets_list, parent, false);
        return new PlanetsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetsAdapterViewHolder holder, int position) {
        holder.textViewPlanetName.setText(resultList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class PlanetsAdapterViewHolder extends RecyclerView.ViewHolder {

        private com.google.android.material.textview.MaterialTextView textViewPlanetName;


        public PlanetsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPlanetName = itemView.findViewById(R.id.textViewPlanetName);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemCLickListener != null) {
                        onItemCLickListener.OnItemCLick(getAbsoluteAdapterPosition());
                    }
                }
            });

        }
    }


    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.onItemCLickListener = onItemCLickListener;
    }
}
