package it_school.sumdu.edu.individualwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;
import java.util.Date;

public class AddVoucherActivity extends AppCompatActivity {
    int BUTTON_START_ACTIVE = 1, BUTTON_END_ACTIVE = 2;
    int[] dateStart = new int[3];
    int[] dateEnd = new int[3];
    int activePickerType;
    Button pickerBtnStart, pickerBtnEnd;

    String descriptionT, titleT, countryT, cityT;
    Double priceN, ratingN;
    private TourismViewModel viewModel;
    private EditText title, description, city, price, rating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voucher);
        final Calendar calendar = Calendar.getInstance();

        viewModel = ViewModelProviders.of(this).get(TourismViewModel.class);
        pickerBtnStart = findViewById(R.id.dateEnd);
        pickerBtnEnd = findViewById(R.id.dateStart);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        city = findViewById(R.id.city);
        price = findViewById(R.id.price);
        rating = findViewById(R.id.rating);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.country);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countryNames, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = parent.getItemAtPosition(position).toString();
                countryT = selectedCountry;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                countryT = null;
            }
        });
    }

    public void onAddClick(View view) {
        titleT = title.getText().toString().trim();
        descriptionT = description.getText().toString().trim();
        cityT = description.getText().toString().trim();
        priceN = Double.parseDouble(price.getText().toString().trim());
        ratingN = Double.parseDouble(rating.getText().toString().trim());
        boolean dateIsValid = validateDate();

        if (!dateIsValid) return;

        String date1 = dateStart[0] + "." + dateStart[1] + "." + dateStart[2];
        String date2 = dateEnd[0] + "." + dateEnd[1] + "." + dateEnd[2];

        Tourism tourism = new Tourism(0, countryT, cityT, titleT, descriptionT, priceN, ratingN, date1, date2);
        viewModel.insert(tourism);
        Toast.makeText(this, "New record added!", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean validateDate() {
        System.out.println(descriptionT +" " + titleT + " " + countryT + " " + cityT + " " + ratingN);
        if (descriptionT == null || titleT == null || countryT == null || cityT == null) {
            showToast("Please, enter all fields!");
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, dateStart[0]);
        calendar1.set(Calendar.MONTH, dateStart[1]);
        calendar1.set(Calendar.DAY_OF_MONTH, dateStart[2]);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, dateEnd[0]);
        calendar2.set(Calendar.MONTH, dateEnd[1]);
        calendar2.set(Calendar.DAY_OF_MONTH, dateEnd[2]);
        int comparisonResult = calendar1.compareTo(calendar2);

        if (comparisonResult > 0){
            showToast("\"Start\" date must be before \"End\" date");
            return false;
        }
        return true;
    };

    public void showDatePickerStart(View view) {
        activePickerType = BUTTON_START_ACTIVE;
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    public void showDatePickerEnd(View view) {
        activePickerType = BUTTON_END_ACTIVE;
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void saveDate(int y, int m, int d) {
        if (activePickerType == BUTTON_START_ACTIVE) {
            dateStart[0] = y;
            dateStart[1] = m;
            dateStart[2] = d;
        } else if (activePickerType == BUTTON_END_ACTIVE) {
            dateEnd[0] = y;
            dateEnd[1] = m;
            dateEnd[2] = d;
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.YEAR, dateStart[0]);
            calendar1.set(Calendar.MONTH, dateStart[1]);
            calendar1.set(Calendar.DAY_OF_MONTH, dateStart[2]);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.YEAR, dateEnd[0]);
            calendar2.set(Calendar.MONTH, dateEnd[1]);
            calendar2.set(Calendar.DAY_OF_MONTH, dateEnd[2]);
            int comparisonResult = calendar1.compareTo(calendar2);

            if (comparisonResult > 0){
                showToast("\"Start\" date must be before \"End\" date");
            }
        }
    }

    private void showToast(String message) {
        System.out.println(message);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
