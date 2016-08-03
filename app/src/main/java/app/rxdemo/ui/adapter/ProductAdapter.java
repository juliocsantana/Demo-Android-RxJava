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
import app.rxdemo.model.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atempa on 26/07/16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>  {

    private List<Product> productItems;

    public ProductAdapter(List<Product> product) {
        this.productItems = product;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Product product = productItems.get(position);
        viewHolder.name.setText(product.getName());
        viewHolder.sizeProduct.setText(product.getSize());
        viewHolder.price.setText("$" + product.getPrice());
        viewHolder.brewer.setText(product.getBrewer());
        viewHolder.country.setText(product.getCountry());
        viewHolder.setPicture(product.getImage_url());
    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public void addProduct(Product productData) {
        productItems.add(productData);
        notifyDataSetChanged();
    }

    public void clear() {
        productItems.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.sizeProduct)
        TextView sizeProduct;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.brewer)
        TextView brewer;
        @BindView(R.id.country)
        TextView country;
        @BindView(R.id.productPicture)
        ImageView productPicture;
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
                    .into(productPicture);
        }
    }
}
