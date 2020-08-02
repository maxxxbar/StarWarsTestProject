package com.example.starwars.ui.planetactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.starwars.R;
import com.example.starwars.entries.planets.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class PlanetActivity extends AppCompatActivity
        implements PlanetActivityView {


    private com.google.android.material.textview.MaterialTextView textViewRotationPeriod;
    private com.google.android.material.textview.MaterialTextView textViewOrbitalPeriod;
    private com.google.android.material.textview.MaterialTextView textViewDiameter;
    private com.google.android.material.textview.MaterialTextView textViewPlanetClimate;
    private com.google.android.material.textview.MaterialTextView textViewGravity;
    private com.google.android.material.textview.MaterialTextView textViewTerrain;
    private com.google.android.material.textview.MaterialTextView textViewSurfaceWater;
    private com.google.android.material.textview.MaterialTextView textViewPopulation;
    private com.google.android.material.textview.MaterialTextView textViewResidents;
    private com.google.android.material.textview.MaterialTextView textViewFilms;

    private PlanetActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        textViewRotationPeriod = findViewById(R.id.textViewRotationPeriod);
        textViewOrbitalPeriod = findViewById(R.id.textViewOrbitalPeriod);
        textViewDiameter = findViewById(R.id.textViewDiameter);
        textViewPlanetClimate = findViewById(R.id.textViewPlanetClimate);
        textViewGravity = findViewById(R.id.textViewGravity);
        textViewTerrain = findViewById(R.id.textViewTerrain);
        textViewSurfaceWater = findViewById(R.id.textViewSurfaceWater);
        textViewPopulation = findViewById(R.id.textViewPopulation);
        textViewResidents = findViewById(R.id.textViewResidents);
        textViewFilms = findViewById(R.id.textViewFilms);

        presenter = new PlanetActivityPresenter(this);
        View view = findViewById(android.R.id.content).getRootView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                return insets;
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(view, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                v.setPadding(insets.getStableInsetLeft(), insets.getStableInsetTop(), insets.getStableInsetRight(), insets.getStableInsetBottom());
                return insets;
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String s = intent.getStringExtra("PLANET");
            Type type = new TypeToken<Result>() {
            }.getType();
            Gson gson = new Gson();
            Result result = gson.fromJson(s, type);
            if (result != null) {
                for (int i = 0; i < result.getFilms().size(); i++) {
                    String films = result.getFilms().get(i).trim();
                    int filmsID = Integer.parseInt(films.substring(27, 28));
                    presenter.getFilms(filmsID);
                }

                for (int i = 0; i < result.getResidents().size(); i++) {
                    String resident = result.getResidents().get(i).trim();
                    if (resident.length() == 31) {
                        int residentID = Integer.parseInt(result.getResidents().get(i).substring(28, 30));
                        presenter.getResidents(residentID);
                    } else if (resident.length() == 30) {
                        int residentID = Integer.parseInt(result.getResidents().get(i).substring(28, 29));
                        presenter.getResidents(residentID);
                    }
                }
                textViewRotationPeriod.setText(result.getRotationPeriod());
                textViewOrbitalPeriod.setText(result.getOrbitalPeriod());
                textViewDiameter.setText(result.getDiameter());
                textViewPlanetClimate.setText(result.getClimate());
                textViewGravity.setText(result.getGravity());
                textViewTerrain.setText(result.getTerrain());
                textViewSurfaceWater.setText(result.getSurfaceWater());
                textViewPopulation.setText(result.getPopulation());
            }
        }


    }

    @Override
    public void setTextViewFilms(StringBuilder textViewFilms) {
        this.textViewFilms.setText(textViewFilms);
    }

    @Override
    public void setTextViewResidents(StringBuilder textViewResidents) {
        this.textViewResidents.setText(textViewResidents);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.compositeDispos();
    }
}