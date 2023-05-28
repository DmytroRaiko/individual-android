package it_school.sumdu.edu.individualwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TourismViewModel viewModel;
    CountryAdapter adapter;
    String country = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        country = "";
        int gridCount = getResources().getInteger(R.integer.grid_column_count);

        RecyclerView recyclerView = findViewById(R.id.countryRecyclerView);
        adapter = new CountryAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridCount));

        viewModel = ViewModelProviders.of(this).get(TourismViewModel.class);
        onSearchQuery();

        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                country = query;
                onSearchQuery();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });
    }

    private void onSearchQuery() {
        viewModel.getCountries(country).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> countries) {
                adapter.setCountriesList(countries);
            }
        });
    }

    public void openAddActivity() {
        Intent intent = new Intent(this, AddVoucherActivity.class);
        startActivity(intent);
    }
}