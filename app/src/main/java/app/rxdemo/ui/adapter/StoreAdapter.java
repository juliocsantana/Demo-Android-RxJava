package app.rxdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.rxdemo.R;
import app.rxdemo.model.Store;
import app.rxdemo.ui.FirstMapActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atempa on 31/07/16.
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    private List<Store> storeItems;
    private View v;
    private ViewHolder viewHolder;
    private Store store;

    public StoreAdapter(List<Store> stores) {
        this.storeItems = stores;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.store_item, viewGroup, false);

        viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        store = storeItems.get(position);
        viewHolder.name.setText(store.getName());
        viewHolder.address.setText(store.getAddress());
        viewHolder.postal_code.setText(store.getPostal_code());
        viewHolder.phone.setText(store.getPhone());
        viewHolder.city.setText(store.getCity());
    }

    @Override
    public int getItemCount() {
        return storeItems.size();
    }

    public void addStore(Store storeData) {
        storeItems.add(storeData);
        notifyDataSetChanged();
    }

    public void clear() {
        storeItems.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.postal_code)
        TextView postal_code;
        @BindView(R.id.phone)
        TextView phone;
        @BindView(R.id.city)
        TextView city;
        Intent intent;
        Bundle bundle;
        Context context;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            context = view.getContext();
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            store = storeItems.get(getPosition());
            intent = new Intent(context, FirstMapActivity.class);
            bundle = new Bundle();
            bundle.putString("name", store.getName());
            bundle.putString("address", store.getAddress());
            bundle.putString("phone", store.getPhone());
            bundle.putFloat("latitude", store.getLatitude());
            bundle.putFloat("longitude", store.getLongitude());
            bundle.putString("monday_open", store.getMonday_open());
            bundle.putString("tuesday_open", store.getTuesday_open());
            bundle.putString("wednesday_open", store.getWednesday_open());
            bundle.putString("thursday_open", store.getTuesday_open());
            bundle.putString("friday_open", store.getFriday_open());
            bundle.putString("saturday_open", store.getSaturday_open());
            bundle.putString("sunday_open", store.getSunday_open());
            bundle.putString("monday_close", store.getMonday_close());
            bundle.putString("tuesday_close", store.getTuesday_close());
            bundle.putString("wednesday_close", store.getWednesday_close());
            bundle.putString("thursday_close", store.getTuesday_close());
            bundle.putString("friday_close", store.getFriday_close());
            bundle.putString("saturday_close", store.getSaturday_close());
            bundle.putString("sunday_close", store.getSunday_close());
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}