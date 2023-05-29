package it_school.sumdu.edu.individualwork;

import androidx.appcompat.app.AlertDialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

public class VoucherActivity extends AppCompatActivity {
    Tourism voucher;
    TourismViewModel vm;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        vm = ViewModelProviders.of(this).get(TourismViewModel.class);

        voucher = SelectedHolder.getSelectedTourism();

        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);
        TextView country = findViewById(R.id.country);
        TextView price = findViewById(R.id.price);
        TextView rating = findViewById(R.id.rating);
        TextView dates = findViewById(R.id.dates);

        String countryStr = voucher.getCountry();
        String city = voucher.getCity();
        if (city != null && !city.equals("")) {
            countryStr += ", " + city;
        }
        title.setText(voucher.getTitle());
        description.setText(voucher.getDescription());
        country.setText(countryStr);
        price.setText("UAH " + voucher.getPrice());
        rating.setText(Double.toString(voucher.getRating()));
        dates.setText(voucher.getDateStart() + " - " + voucher.getDateEnd());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    public void onDelete(View view) {
        showDeleteConfirmationDialog();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm the action!")
                .setMessage("Are you sure you want to delete?")
                .setPositiveButton("Yes", (d, w) -> deleteItem())
                .setNegativeButton("No", (d, w) -> {})
                .show();
    }

    private void deleteItem() {
        AsyncTask.execute(() -> vm.delete(voucher));
        Toast.makeText(this, "Record deleted!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
