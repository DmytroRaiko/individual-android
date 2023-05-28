package it_school.sumdu.edu.individualwork;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<String> countriesList;

    CountryAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.country_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (countriesList != null) {
            String country = countriesList.get(position);
            holder.bind(country);
        }
    }

    @Override
    public int getItemCount() {
        if (countriesList != null) {
            return countriesList.size();
        }
        else {
            return 0;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    void setCountriesList (List<String> countries) {
        countriesList = countries;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView countryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryName);
        }

        public void bind(String country) {
            countryName.setText(country);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SelectedHolder.setSelectedCountry(country);

                    Intent intent = new Intent(itemView.getContext(), VouchersActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}

