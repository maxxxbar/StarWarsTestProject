package com.example.starwars.ui.second;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwars.MainActivity;
import com.example.starwars.R;
import com.example.starwars.adapters.RootAdapter;
import com.example.starwars.ui.peopleroot.PeopleRootActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private RecyclerView recyclerViewRoot;
    private RootAdapter rootAdapter;
    private List<String> rootList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_second, container, false);

        rootArray();
        recyclerViewRoot = root.findViewById(R.id.recyclerViewRoot);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerViewRoot.setLayoutManager(linearLayoutManager);
        rootAdapter = new RootAdapter();
        rootAdapter.setRootList(rootList);
        recyclerViewRoot.setAdapter(rootAdapter);
        Intent intent = new Intent(root.getContext(), PeopleRootActivity.class);
        rootAdapter.setOnItemCLickListener(new RootAdapter.OnItemCLickListener() {
            @Override
            public void OnItemCLick(int position) {
                switch (position) {
                    case (0):
                        intent.putExtra("ID", 0);
                        startActivity(intent);
                        break;
                    case (1):
                        intent.putExtra("ID", 1);
                        startActivity(intent);
                        break;
                    case (2):
                        intent.putExtra("ID", 2);
                        startActivity(intent);
                        break;
                    case (3):
                        intent.putExtra("ID", 3);
                        startActivity(intent);
                        break;
                    case (4):
                        intent.putExtra("ID", 4);
                        startActivity(intent);
                        break;
                    case (5):
                        intent.putExtra("ID", 5);
                        startActivity(intent);
                        break;
                    default:
                        startActivity(new Intent(root.getContext(), MainActivity.class));
                        break;
                }
            }

        });

        return root;
    }

    private void rootArray() {
        rootList = new ArrayList<>();
        rootList.add("People");
        rootList.add("Planets");
        rootList.add("Films");
        rootList.add("Species");
        rootList.add("Vehicles");
        rootList.add("Starships");


    }


}