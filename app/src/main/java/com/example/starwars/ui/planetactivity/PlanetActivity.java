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
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.starwars.R;
import com.example.starwars.databinding.ActivityPlanetBinding;
import com.example.starwars.entries.planets.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class PlanetActivity extends AppCompatActivity {

    private ActivityPlanetBinding binding;
    private PlanetActivityViewModel viewModel;
    private MutableLiveData<Result> resultMutableLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_planet);
        View view = binding.getRoot().getRootView();

        resultMutableLiveData = new MutableLiveData<>();
        viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(PlanetActivityViewModel.class);

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
            Result result = new Gson().fromJson(s, type);
            resultMutableLiveData.setValue(result);
            viewModel.setResultMutableLiveData(resultMutableLiveData);
            viewModel.getResultMutableLiveData().observe(this, new Observer<Result>() {
                @Override
                public void onChanged(Result result) {
                    binding.setActivityPlanet(result);
                }
            });
        }
    }

}