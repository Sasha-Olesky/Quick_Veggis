package com.developer.android.quickveggis.ui.fragments;

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
import com.developer.android.quickveggis.model.Product;
import com.developer.android.quickveggis.ui.activity.MainActivity;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration.Builder;
import java.util.ArrayList;
import java.util.List;

public class WishlistFragment extends Fragment implements WishAdapter.ActionListener, MainActivity.MenuController {
    WishAdapter adapter;
    List<Product> data;
    @Bind(R.id.rv)
    RecyclerView rv;

    public WishlistFragment() {
        this.data = new ArrayList();
    }

    public static WishlistFragment newInstance() {
        Bundle args = new Bundle();
        WishlistFragment fragment = new WishlistFragment();
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
        getActivity().setTitle(R.string.wishlist);
        this.data = TestData.getProducts();
        this.rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.adapter = new WishAdapter(getContext(), this.data, this);
        this.rv.setAdapter(this.adapter);
        this.rv.addItemDecoration(((Builder) ((Builder) new Builder(getActivity()).color(getResources().getColor(17170445))).sizeResId(R.dimen.divider_products)).build());
        this.rv.addItemDecoration(((HorizontalDividerItemDecoration.Builder) ((HorizontalDividerItemDecoration.Builder) new HorizontalDividerItemDecoration.Builder(getActivity()).color(getResources().getColor(17170445))).sizeResId(R.dimen.divider_products)).build());
    }

    public void onSelected(Product product) {
        FragmentUtils.changeFragment(getActivity(), (int) R.id.content, ProductFragment.newInstance(), true);
    }

    public void onRemove(Product product) {
        this.data.remove(product);
        this.adapter.notifyDataSetChanged();
    }

    public int getMenuVisibility() {
        return 0;
    }
}
