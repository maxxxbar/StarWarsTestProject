package com.example.starwars.ui.planetactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.starwars.R;
import com.example.starwars.databinding.ActivityPlanetBinding;
import com.example.starwars.entries.planets.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class PlanetActivity extends AppCompatActivity
        implements PlanetActivityView {

    private com.google.android.material.textview.MaterialTextView textViewResidents;
    private com.google.android.material.textview.MaterialTextView textViewFilms;

    private PlanetActivityPresenter presenter;
    private ActivityPlanetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_planet);
        View view = binding.getRoot().getRootView();
        textViewResidents = findViewById(R.id.textViewResidents);
        textViewFilms = findViewById(R.id.textViewFilms);

        presenter = new PlanetActivityPresenter(this);
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
            Type type = new TypeToken<Result>() {}.getType();
            Result result = new Gson().fromJson(s, type);
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
                binding.setActivityPlanet(result);
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