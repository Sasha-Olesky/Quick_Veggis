package com.developer.android.quickveggis.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.TestData;
import com.developer.android.quickveggis.config.Config;
import com.developer.android.quickveggis.model.Product;
import com.developer.android.quickveggis.ui.activity.MainActivity;
import com.developer.android.quickveggis.ui.activity.ProductActivity;
import com.developer.android.quickveggis.ui.adapter.ProductAdapter;
import com.developer.android.quickveggis.ui.utils.RecyclerItemClickListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration.Builder;
import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {
    ProductAdapter adapter;
    List<Product> data;
    @Bind(R.id.rv)
    RecyclerView rv;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.ProductsFragment.1 */
    class C05681 implements RecyclerItemClickListener.OnItemClickListener {
        C05681() {
        }

        public void onItemClick(View view, int position) {
//            Intent intent = ProductActivity.getStartIntent(getContext());
            Intent intent = MainActivity.getStartIntent(getContext(), Config.PRODUCT_MODE);
            intent.putExtra("item", position);
            startActivityForResult(intent, 1);
        }
    }

    public ProductsFragment() {
        this.data = new ArrayList();
    }

    public static ProductsFragment newInstance() {
        Bundle args = new Bundle();
        ProductsFragment fragment = new ProductsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Food mart");
        this.data = TestData.getNewProducts();
        this.rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.adapter = new ProductAdapter(getContext(), this.data);
        this.rv.setAdapter(this.adapter);
//        this.rv.addItemDecoration(((Builder) ((Builder) new Builder(getActivity()).color(getResources().getColor(17170445))).sizeResId(R.dimen.divider_products)).build());
//        this.rv.addItemDecoration(((HorizontalDividerItemDecoration.Builder) ((HorizontalDividerItemDecoration.Builder) new HorizontalDividerItemDecoration.Builder(getActivity()).color(getResources().getColor(17170445))).sizeResId(R.dimen.divider_products)).build());
        this.rv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new C05681()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 513){
            getActivity().finish();
        }
    }
}
