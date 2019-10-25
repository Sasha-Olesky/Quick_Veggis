package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.activity.ProductsActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration.Builder;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterFragment extends Fragment {
    FIlterAdapter adapter;
    List<String> brands;
    @Bind(R.id.btnDone)
    View btnDone;
    @Bind(R.id.btnReset)
    View btnReset;
    @Bind(R.id.rv)
    RecyclerView rv;
    Map<String, Boolean> selected;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.FilterFragment.1 */
    class C02951 implements OnClickListener {
        C02951() {
        }

        public void onClick(View v) {
            FilterFragment.this.reset();
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.FilterFragment.2 */
    class C02962 implements OnClickListener {
        C02962() {
        }

        public void onClick(View v) {
            ((ProductsActivity) FilterFragment.this.getActivity()).toggleMenu();
        }
    }

    public class FIlterAdapter extends Adapter<FIlterAdapter.Holder> {

        /* renamed from: com.quickveggies.quickveggies.ui.fragment.FilterFragment.FIlterAdapter.1 */
        class C02971 implements OnClickListener {
            final /* synthetic */ boolean val$isSelected;
            final /* synthetic */ String val$item;

            C02971(String str, boolean z) {
                this.val$item = str;
                this.val$isSelected = z;
            }

            public void onClick(View v) {
                FilterFragment.this.updateSelected(this.val$item, !this.val$isSelected);
            }
        }

        public class Holder extends ViewHolder {
            @Bind(R.id.text)
            TextView text;

            public Holder(View itemView) {
                super(itemView);
                ButterKnife.bind((Object) this, itemView);
            }
        }

        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_filter, parent, false);
//            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            itemView.setLayoutParams(lp);
            return new Holder(LayoutInflater.from(getActivity()).inflate(R.layout.item_filter, parent, false));
//            return new Holder(itemView);
        }

        public void onBindViewHolder(Holder holder, int position) {
            String item = (String) FilterFragment.this.brands.get(position);
            boolean isSelected = ((Boolean) FilterFragment.this.selected.get(item)).booleanValue();
            holder.text.setText(item);
            holder.text.setSelected(isSelected);
            holder.text.setOnClickListener(new C02971(item, isSelected));
        }

        public int getItemCount() {
            return FilterFragment.this.brands.size();
        }
    }

    public FilterFragment() {
        this.brands = new ArrayList();
        this.selected = new HashMap();
    }

    public static FilterFragment newInstance() {
        Bundle args = new Bundle();
        FilterFragment fragment = new FilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.rv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        fillBrands();
//        this.rv.addItemDecoration(((Builder) ((Builder) new Builder(getActivity()).color(ContextCompat.getColor(getContext(), 17170445))).sizeResId(R.dimen.divider_filter_v)).build());
//        this.rv.addItemDecoration(((VerticalDividerItemDecoration.Builder) ((VerticalDividerItemDecoration.Builder) new VerticalDividerItemDecoration.Builder(getActivity()).color(ContextCompat.getColor(getContext(), 17170445))).sizeResId(R.dimen.divider_filter)).build());
        this.adapter = new FIlterAdapter();
        this.btnReset.setOnClickListener(new C02951());
        this.btnDone.setOnClickListener(new C02962());
        this.rv.setAdapter(this.adapter);
    }

    private void reset() {
        for (String item : this.brands) {
            this.selected.put(item, Boolean.valueOf(false));
        }
        this.adapter.notifyDataSetChanged();
    }

    private void fillBrands() {
        this.brands.clear();
        this.brands.add("Nestle");
        this.brands.add("P&G");
        this.brands.add("Unilever");
        this.brands.add("Parles");
        this.brands.add("Patanjali");
        this.brands.add("Britania");

        for (String item : this.brands) {
            this.selected.put(item, Boolean.valueOf(false));
        }
    }

    private void updateSelected(String item, boolean select) {
        this.selected.put(item, Boolean.valueOf(select));
        this.adapter.notifyDataSetChanged();
    }
}
