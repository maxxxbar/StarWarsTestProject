package com.example.starwars.ui.first;

import com.example.starwars.entries.planets.Result;

import java.util.List;

public interface FirstFragmentView {
    void setPlanetsAdapter(List<Result> resultList);
}
