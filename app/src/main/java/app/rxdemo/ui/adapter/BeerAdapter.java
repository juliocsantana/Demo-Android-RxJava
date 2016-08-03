package app.rxdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.rxdemo.R;
import app.rxdemo.model.Beer;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atempa on 24/07/16.
 */
public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {
    private List<Beer> beerItems;

    public BeerAdapter(List<Beer> beer) {
        this.beerItems = beer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.beer_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Beer beer = beerItems.get(position);
        viewHolder.name.setText(beer.getName());
        viewHolder.type.setText(beer.getType());
        viewHolder.category.setText(beer.getCategory());
        viewHolder.country.setText(beer.getCountry());
        viewHolder.setPicture(beer.getImage_url());
    }

    @Override
    public int getItemCount() {
        return beerItems.size();
    }

    public void addBeer(Beer beerData) {
        beerItems.add(beerData);
        notifyDataSetChanged();
    }

    public void clear() {
        beerItems.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.category)
        TextView category;
        @BindView(R.id.country)
        TextView country;
        @BindView(R.id.beerPicture)
        ImageView beerPicture;
        Context context;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            context = view.getContext();
        }

        private void setPicture(String url) {
            Picasso.with(context)
                    .load(url)
                    .resize(60, 100)
                    .centerCrop()
                    .into(beerPicture);
        }
    }
}