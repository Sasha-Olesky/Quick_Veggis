package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.App;
import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.TestData;
import com.developer.android.quickveggis.model.Product;
import com.developer.android.quickveggis.ui.activity.MainActivity;
import com.developer.android.quickveggis.ui.activity.OfflineRedeemActivity;
import com.developer.android.quickveggis.ui.activity.OrderActivity;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.developer.android.quickveggis.ui.utils.PriceFormat;
import com.squareup.picasso.Picasso;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CartFragment extends Fragment implements MainActivity.MenuController {
    public static int MODE_OFFLINE;
    public static int MODE_WE_DELIVER;
    ProductAdapter adapter;
    @Bind(R.id.blockCartTotal)
    View blockCartTotal;
    @Bind(R.id.header)
    View blockHeader;
    @Bind(R.id.blockShippingTotal)
    View blockShippingTotal;
    @Bind(R.id.btnNext)
    Button btnNext;
    Map<Product, Integer> counts;
    List<Product> data;
    float deliveryPrice;
    @Bind(R.id.imgSponsor)
    ImageView imgSponsor;
    int mode;
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.totalLabel)
    TextView totalLabel;
    @Bind(R.id.txtCartTotal)
    TextView txtCartTotal;
    @Bind(R.id.txtShipping)
    TextView txtShipping;
    @Bind(R.id.txtTotal)
    TextView txtTotal;
    @Bind(R.id.fullfilled)
    TextView txtFull;
    @Bind(R.id.imgHandle)
    ImageView imgHandle;
    @Bind(R.id.btnTop)
    View btnTop;

    public class ProductAdapter extends Adapter<ProductAdapter.Holder> {

        /* renamed from: com.quickveggies.quickveggies.ui.fragment.CartFragment.ProductAdapter.1 */
        class C02901 implements OnClickListener {
            final /* synthetic */ Product val$product;

            C02901(Product product) {
                this.val$product = product;
            }

            public void onClick(View v) {
                CartFragment.this.onMinusCount(this.val$product);
            }
        }

        /* renamed from: com.quickveggies.quickveggies.ui.fragment.CartFragment.ProductAdapter.2 */
        class C02912 implements OnClickListener {
            final /* synthetic */ Product val$product;

            C02912(Product product) {
                this.val$product = product;
            }

            public void onClick(View v) {
                CartFragment.this.onAddCount(this.val$product);
            }
        }

        public class Holder extends ViewHolder {
            @Bind(R.id.blockCount)
            View blockCount;
            @Bind(R.id.imgIcon)
            ImageView imgIcon;
            @Bind(R.id.txtCount)
            TextView txtCount;
            @Bind(R.id.txtMinus)
            TextView txtMinus;
            @Bind(R.id.txtName)
            TextView txtName;
            @Bind(R.id.txtPlus)
            TextView txtPlus;
            @Bind(R.id.txtPrice)
            TextView txtPrice;
            @Bind(R.id.txtWeight)
            TextView txtWeight;

            public Holder(View itemView) {
                super(itemView);
                ButterKnife.bind((Object) this, itemView);
            }
        }

        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mode == MODE_WE_DELIVER) {
                return new Holder(LayoutInflater.from(CartFragment.this.getContext()).inflate(R.layout.item_cart_item, parent, false));
            } else {
                return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.item_cart_item_new, parent, false));
            }
        }

        public void onBindViewHolder(Holder holder, int position) {
            Product product = (Product) CartFragment.this.data.get(position);
//            Picasso.with(CartFragment.this.getContext()).load(product.getImage()).fit().centerInside().into(holder.imgIcon);
            holder.imgIcon.setImageResource(product.getImageId());
            holder.txtPrice.setText(PriceFormat.format(product.getPriceD() * ((double) ((Integer) CartFragment.this.counts.get(product)).intValue())));
            holder.txtName.setText(product.getName());
            if (mode == MODE_OFFLINE) {
                holder.txtWeight.setText(product.getDesc());
            }
            if (CartFragment.this.mode == CartFragment.MODE_WE_DELIVER) {
                holder.txtCount.setText(String.valueOf(CartFragment.this.counts.get(product)));
                holder.txtMinus.setOnClickListener(new C02901(product));
                holder.txtPlus.setOnClickListener(new C02912(product));
                holder.txtWeight.setText("500g");
                return;
            }
            holder.blockCount.setVisibility(8);
        }

        public int getItemCount() {
            return CartFragment.this.data.size();
        }
    }

    public CartFragment() {
        this.deliveryPrice = 20.0f;
        this.data = new ArrayList();
        this.counts = new HashMap();
    }

    static {
        MODE_WE_DELIVER = 1;
        MODE_OFFLINE = 2;
    }

    public static CartFragment newInstance(int mode) {
        Bundle args = new Bundle();
        args.putInt("mode", mode);
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mode = getArguments().getInt("mode");
        updateProducts();
        this.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new ProductAdapter();
        this.rv.setAdapter(this.adapter);
//        this.rv.addItemDecoration(((Builder) ((Builder) new Builder(getActivity()).color(getResources().getColor(17170445))).sizeResId(R.dimen.divider_cart)).build());
        if (this.mode == MODE_WE_DELIVER) {
            Picasso.with(getContext()).load("http://cdn.corporate.walmart.com/resource/assets-bsp3/images/corp/walmart-logo.64968e7648c4bbc87f823a1eff1d6bc7.png").fit().centerInside().into(this.imgSponsor);
            getActivity().setTitle(R.string.we_deliver);
            this.btnNext.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(OrderActivity.getStartIntent(CartFragment.this.getContext()));
                }
            });
        } else {
            this.deliveryPrice = 0.0f;
            this.blockCartTotal.setVisibility(8);
            this.blockShippingTotal.setVisibility(8);
            this.totalLabel.setText(R.string.total_savings);
            this.blockHeader.setVisibility(View.VISIBLE);
            this.txtFull.setTextColor(getResources().getColor(R.color.mainGreen));
            this.txtFull.setText(R.string.cart_desc);
            this.imgHandle.setImageResource(R.drawable.img_handle);
            this.btnTop.setVisibility(View.GONE);
            getActivity().setTitle(R.string.cart);
            this.btnNext.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(OfflineRedeemActivity.getStartIntent(CartFragment.this.getContext()));
//                    FragmentUtils.changeFragment(getActivity(), R.id.content, SubmitFragment.newInstance(null, null), false);
                }
            });
            this.blockHeader.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentUtils.changeFragment((FragmentActivity) getActivity(), R.id.content, CartFragment.newInstance(1), false);//2 = offlinemode
                }
            });

//            ((MainActivity)getActivity()).txtOffline.setVisibility(View.VISIBLE);
        }
        updatePrice();
    }

    private void updateProducts() {
        this.data = TestData.getCartProducts();
        for (Product product : this.data) {
            this.counts.put(product, Integer.valueOf(1));
        }
    }

    private void updatePrice() {
        float finalPrice = 0.0f;
        for (Entry<Product, Integer> entry : this.counts.entrySet()) {
            int count = ((Integer) entry.getValue()).intValue();
            if (count > 0) {
                finalPrice = (float) (((double) finalPrice) + (((double) count) * ((Product) entry.getKey()).getPriceD()));
            }
        }
        this.txtCartTotal.setText(PriceFormat.format(finalPrice));
        this.txtShipping.setText(PriceFormat.format(this.deliveryPrice));
        this.txtTotal.setText(PriceFormat.format(this.deliveryPrice + finalPrice));
    }

    public void onMinusCount(Product product) {
        Integer count = (Integer) this.counts.get(product);
        if (count.intValue() > 0) {
            onChangeCount(product, count.intValue() - 1);
        }
    }

    public void onAddCount(Product product) {
        onChangeCount(product, ((Integer) this.counts.get(product)).intValue() + 1);
    }

    public void onChangeCount(Product product, int count) {
        this.counts.put(product, Integer.valueOf(count));
        this.adapter.notifyDataSetChanged();
        updatePrice();
    }

    @Override
    public int getMenuVisibility() {
        return 1;
    }
}
