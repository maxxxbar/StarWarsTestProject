package com.example.starwars.ui.first;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwars.R;
import com.example.starwars.adapters.PlanetsAdapter;
import com.example.starwars.entries.planets.Result;
import com.example.starwars.ui.planetactivity.PlanetActivity;
import com.google.gson.Gson;

import java.util.List;

public class FirstFragment extends Fragment
        implements FirstFragmentView {

    private FirstFragmentPresenter presenter;
    private RecyclerView recyclerView;
    private PlanetsAdapter planetsAdapter;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int page = 1;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewPlanets);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        presenter = new FirstFragmentPresenter(this);
        planetsAdapter = new PlanetsAdapter();
        planetsAdapter.setOnItemCLickListener(new PlanetsAdapter.OnItemCLickListener() {
            @Override
            public void OnItemCLick(int position) {
                Intent intent = new Intent(root.getContext(), PlanetActivity.class);
                Gson gson = new Gson();
                String jsonPlanet = gson.toJson(presenter.getPlanetItem(position));
                intent.putExtra("PLANET", jsonPlanet);
                startActivity(intent);
            }
        });
        presenter.getPlanetsList(page);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (((visibleItemCount + firstVisibleItem) >= totalItemCount) && !presenter.isLastPage()) {
                    presenter.getPlanetsList(++page);
                }
            }
        });
        return root;
    }

    @Override
    public void setPlanetsAdapter(List<Result> resultList) {
        planetsAdapter.setResultList(resultList);
        recyclerView.setAdapter(planetsAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.compositeDispos();
    }

}