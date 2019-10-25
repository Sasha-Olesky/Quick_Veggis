package com.developer.android.quickveggis.ui.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.model.Product;
import com.squareup.picasso.Picasso;
import java.util.List;

public class WishAdapter extends Adapter<WishAdapter.Holder> {
    ActionListener actionListener;
    Context context;
    List<Product> data;

    /* renamed from: com.quickveggies.quickveggies.ui.adapter.WishAdapter.1 */
    class C02731 implements OnClickListener {
        final /* synthetic */ Product val$product;

        C02731(Product product) {
            this.val$product = product;
        }

        public void onClick(View v) {
            WishAdapter.this.actionListener.onRemove(this.val$product);
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.adapter.WishAdapter.2 */
    class C02742 implements OnClickListener {
        final /* synthetic */ Product val$product;

        C02742(Product product) {
            this.val$product = product;
        }

        public void onClick(View v) {
            WishAdapter.this.actionListener.onSelected(this.val$product);
        }
    }

    public interface ActionListener {
        void onRemove(Product product);

        void onSelected(Product product);
    }

    public class Holder extends ViewHolder {
        @Bind(R.id.btnRemove)
        View btnRemove;
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

    public WishAdapter(Context context, List<Product> data, ActionListener listener) {
        this.context = context;
        this.data = data;
        this.actionListener = listener;
    }

    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.item_wish, parent, false));
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
        Picasso.with(getContext()).load(product.getImage()).fit().centerInside().into(holder.imgIcon);
        holder.txtPrice.setText(product.getPrice());
        holder.txtTitle.setText(product.getName());
        holder.btnRemove.setOnClickListener(new C02731(product));
        holder.itemView.setOnClickListener(new C02742(product));
    }

    public Context getContext() {
        return this.context;
    }

    public int getItemCount() {
        return this.data.size();
    }
}
