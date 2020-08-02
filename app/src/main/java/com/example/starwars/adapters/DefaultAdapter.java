package com.example.starwars.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwars.R;

import java.util.List;


public class DefaultAdapter extends RecyclerView.Adapter<DefaultAdapter.DefaultAdapterViewHolder> {

    private List<String> peopleList;
    private OnItemCLickListener onItemCLickListener;


    public interface OnItemCLickListener {
        void OnItemCLick(int position);
    }

    @NonNull
    @Override
    public DefaultAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_root_list, parent, false);
        return new DefaultAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultAdapterViewHolder holder, int position) {
        String s = peopleList.get(position);
        holder.textViewName.setText(s);
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    class DefaultAdapterViewHolder extends RecyclerView.ViewHolder {
        private com.google.android.material.textview.MaterialTextView textViewName;

        public DefaultAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
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

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.onItemCLickListener = onItemCLickListener;
    }

    public void setPeopleList(List<String> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }
}
