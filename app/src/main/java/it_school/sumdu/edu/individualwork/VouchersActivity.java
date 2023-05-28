package it_school.sumdu.edu.individualwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VouchersActivity extends AppCompatActivity {
    TourismViewModel viewModel;
    VoucherAdapter adapter;
    String country, searchPart = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vouchers);
        searchPart = "";
        country = SelectedHolder.getSelectedCountry();

        int gridCount = getResources().getInteger(R.integer.grid_column_count);

        RecyclerView recyclerView = findViewById(R.id.vouchersRecyclerView);
        adapter = new VoucherAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridCount));

        viewModel = ViewModelProviders.of(this).get(TourismViewModel.class);

        onSearchTourism();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPart = query;
                onSearchTourism();
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

    private void onSearchTourism() {
        System.out.println(country + "    " + searchPart);
        viewModel.getAllTourismByCountry(country, searchPart).observe(this, new Observer<List<Tourism>>() {
            @Override
            public void onChanged(List<Tourism> vouchers) {
                adapter.setVouchersList(vouchers);
            }
        });
    }

    public void openAddActivity() {
        Intent intent = new Intent(this, AddVoucherActivity.class);
        startActivity(intent);
    }
}
