package com.example.starwars.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwars.R;

import java.util.ArrayList;
import java.util.List;

public class RootAdapter extends RecyclerView.Adapter<RootAdapter.RootAdapterViewHolder> {

    private List<String> rootList = new ArrayList<>();
    private OnItemCLickListener onItemCLickListener;


    public interface OnItemCLickListener {
        void OnItemCLick(int position);
    }


    @NonNull
    @Override
    public RootAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.root_list, parent, false);
        return new RootAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RootAdapterViewHolder holder, int position) {

        holder.textViewRoot.setText(rootList.get(position));

    }

    @Override
    public int getItemCount() {
        return rootList.size();
    }

    class RootAdapterViewHolder extends RecyclerView.ViewHolder {

        private com.google.android.material.textview.MaterialTextView textViewRoot;

        public RootAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRoot = itemView.findViewById(R.id.textViewRoot);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemCLickListener != null) {
                        onItemCLickListener.OnItemCLick(getAdapterPosition());
                    }
                }
            });

        }
    }

    public void setRootList(List<String> rootList) {
        this.rootList = rootList;
        notifyDataSetChanged();
    }

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.onItemCLickListener = onItemCLickListener;
    }
}
