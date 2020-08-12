package com.example.starwars.ui.first;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwars.adapters.OnItemCLickListener;
import com.example.starwars.adapters.planets.PlanetsAdapterNew;
import com.example.starwars.databinding.FragmentFirstBinding;
import com.example.starwars.entries.planets.Result;
import com.example.starwars.ui.planetactivity.PlanetActivity;
import com.google.gson.Gson;

public class FirstFragment extends Fragment {

    private FirstFragmentViewModel viewModel;
    private FragmentFirstBinding binding;
    private PlanetsAdapterNew planetsAdapterNew;
    private RecyclerView recyclerView;
    private final String TAG = getClass().getSimpleName();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getActivity().getApplication())
                .create(FirstFragmentViewModel.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        LinearSnapHelper snapHelper = new LinearSnapHelper();

        recyclerView = binding.recyclerViewPlanets;
        recyclerView.setLayoutManager(linearLayoutManager);
        snapHelper.attachToRecyclerView(recyclerView);
        planetsAdapterNew = new PlanetsAdapterNew();
        viewModel.getPagedListLiveData().observe(getViewLifecycleOwner(), new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                planetsAdapterNew.submitList(results);
            }
        });
        planetsAdapterNew.setOnItemCLickListener(new OnItemCLickListener() {
            @Override
            public void OnItemClick(Object object) {
                Intent intent = new Intent(root.getContext(), PlanetActivity.class);
                String jsonPlanet = new Gson().toJson(object);
                intent.putExtra("PLANET", jsonPlanet);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(planetsAdapterNew);

        return root;
    }

}