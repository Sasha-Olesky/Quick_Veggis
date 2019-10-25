package com.developer.android.quickveggis.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends Adapter<ProductAdapter.Holder> {
    Context context;
    List<Product> data;

    public class Holder extends ViewHolder {
        @Bind(R.id.imgIcon)
        ImageView imgIcon;
        @Bind(R.id.topLine)
        View topLine;
        @Bind(R.id.txtPrice)
        TextView txtPrice;
        @Bind(R.id.txtTitle)
        TextView txtTitle;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind((Object) this, itemView);
        }
    }

    public ProductAdapter(Context context, List<Product> data) {
        this.context = context;
        this.data = data;
    }

    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false));
    }

    public void onBindViewHolder(Holder holder, int position) {
        Product product = (Product) this.data.get(position);
        if ((position / 2) % 2 == 0) {
            if (position % 2 == 0) {
                holder.topLine.setBackgroundResource(R.drawable.product_top_yellow);
            } else {
                holder.topLine.setBackgroundResource(R.drawable.product_top_red);
            }
        } else if (position % 2 == 0) {
            holder.topLine.setBackgroundResource(R.drawable.product_top_red);
        } else {
            holder.topLine.setBackgroundResource(R.drawable.product_top_yellow);
        }
        if (product.getImage() != null && !product.getImage().isEmpty()) {
            Picasso.with(getContext()).load(product.getImage()).fit().centerInside().into(holder.imgIcon);
        } else {
//            Picasso.with(getContext()).load(product.getImageId()).fit().centerInside().into(holder.imgIcon);
            holder.imgIcon.setImageResource(product.getImageId());
        }
        holder.txtPrice.setText(product.getPrice());
        holder.txtTitle.setText(product.getName());
    }

    public Context getContext() {
        return this.context;
    }

    public int getItemCount() {
        return this.data.size();
    }
}
