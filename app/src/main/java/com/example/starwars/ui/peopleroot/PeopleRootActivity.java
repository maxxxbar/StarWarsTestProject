package com.example.starwars.ui.peopleroot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwars.MainActivity;
import com.example.starwars.R;
import com.example.starwars.adapters.DefaultAdapter;

import java.util.List;

public class PeopleRootActivity extends AppCompatActivity
        implements PeopleRootActivityView {

    private PeopleRootActivityPresenter presenter;
    private RecyclerView recyclerViewPeopleRoot;
    private DefaultAdapter peopleAdapter;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int page = 1;
    private LinearLayoutManager linearLayoutManager;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_root);
        View view = findViewById(android.R.id.content).getRootView();
        recyclerViewPeopleRoot = findViewById(R.id.recyclerViewPeopleRoot);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                return insets;
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(recyclerViewPeopleRoot, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                v.setPadding(insets.getStableInsetLeft(), insets.getStableInsetTop(), insets.getStableInsetRight(), insets.getStableInsetBottom());
                return insets;
            }
        });
        presenter = new PeopleRootActivityPresenter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewPeopleRoot.setLayoutManager(linearLayoutManager);
        peopleAdapter = new DefaultAdapter();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ID")) {
            id = intent.getIntExtra("ID", 0);
        }

        switch (id) {
            case (0):
                getPeopleRoot();
                break;
            case (1):
                getPlanetsRoot();
                break;
            case (2):
                getFilmsRoot();
                break;
            case (3):
                getSpeciesRoot();
                break;
            case (4):
                getVehiclesRoot();
                break;
            case (5):
                getStatshipsRoot();
                break;
            default:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void getPeopleRoot() {
        presenter.getPeople(page);
        recyclerViewPeopleRoot.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (((visibleItemCount + firstVisibleItem) >= totalItemCount) && !presenter.isLastPage()) {
                    presenter.getPeople(++page);
                }
            }
        });
    }

    private void getPlanetsRoot() {
        presenter.getPlanetsList(page);
        recyclerViewPeopleRoot.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (((visibleItemCount + firstVisibleItem) >= totalItemCount) && !presenter.isLastPage()) {
                    presenter.getPlanetsList(++page);
                }
            }
        });
    }

    private void getFilmsRoot() {
        presenter.getFilms();

    }

    private void getSpeciesRoot() {
        presenter.getSpecies(page);
        recyclerViewPeopleRoot.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (((visibleItemCount + firstVisibleItem) >= totalItemCount) && !presenter.isLastPage()) {
                    presenter.getSpecies(++page);
                }
            }
        });
    }

    private void getVehiclesRoot() {
        presenter.getVehicles(page);
        recyclerViewPeopleRoot.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (((visibleItemCount + firstVisibleItem) >= totalItemCount) && !presenter.isLastPage()) {
                    presenter.getVehicles(++page);
                }
            }
        });
    }

    private void getStatshipsRoot() {
        presenter.getStarships(page);
        recyclerViewPeopleRoot.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (((visibleItemCount + firstVisibleItem) >= totalItemCount) && !presenter.isLastPage()) {
                    presenter.getStarships(++page);
                    Log.d("TAG", "onScrollChange: " + page);
                }
            }
        });
    }

    @Override
    public void setPeopleAdapter(List<String> peopleList) {
        peopleAdapter.setPeopleList(peopleList);
        recyclerViewPeopleRoot.setAdapter(peopleAdapter);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        presenter.setLastPage(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.compositeDispos();
    }
}