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

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.ViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<Tourism> vouchersList;

    VoucherAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VoucherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.voucher_item, parent, false);
        return new VoucherAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherAdapter.ViewHolder holder, int position) {
        if (vouchersList != null) {
            Tourism vouchers = vouchersList.get(position);
            holder.bind(vouchers);
        }
    }

    @Override
    public int getItemCount() {
        if (vouchersList != null) {
            return vouchersList.size();
        }
        else {
            return 0;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    void setVouchersList (List<Tourism> vouchers) {
        vouchersList = vouchers;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleV, descriptionV, countryV, priceV, ratingV, datesV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleV = itemView.findViewById(R.id.titleItem);
            descriptionV = itemView.findViewById(R.id.descriptionItem);
            countryV = itemView.findViewById(R.id.countryItem);
            priceV = itemView.findViewById(R.id.priceItem);
            ratingV = itemView.findViewById(R.id.ratingItem);
            datesV = itemView.findViewById(R.id.datesItem);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Tourism voucher) {
            titleV.setText(voucher.getTitle());
            descriptionV.setText(voucher.getDescription());
            String countryStr = voucher.getCountry();
            String city = voucher.getCity();
            if (city != null && city != "") {
                countryStr += ", " + city;
            }
            countryV.setText(countryStr);
            priceV.setText(Double.toString(voucher.getPrice()));
            ratingV.setText(Double.toString(voucher.getRating()));
            datesV.setText(voucher.getDateStart() + " - " + voucher.getDateEnd());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SelectedHolder.setSelectedTourism(voucher);

                    Intent intent = new Intent(itemView.getContext(), VoucherActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
