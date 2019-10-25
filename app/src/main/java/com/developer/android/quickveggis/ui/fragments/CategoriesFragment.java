package com.developer.android.quickveggis.ui.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.App;
import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.TestData;
import com.developer.android.quickveggis.model.Category;
import com.developer.android.quickveggis.ui.activity.MainActivity;
import com.developer.android.quickveggis.ui.activity.ProductActivity;
import com.developer.android.quickveggis.ui.activity.ProductsActivity;
import com.developer.android.quickveggis.ui.utils.RecyclerItemClickListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment implements MainActivity.MenuController {
    CategoryAdapter adapter;
    List<Category> data;
    List<Bundle> search_data;
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.myPager)
    ViewPager mPager;
    @Bind(R.id.txtSearch)
    AutoCompleteTextView txtSearch;
    @Bind(R.id.indicator)
    CirclePageIndicator indicator;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.CategoriesFragment.1 */
    class C05621 implements RecyclerItemClickListener.OnItemClickListener {
        C05621() {
        }

        public void onItemClick(View view, int position) {
            CategoriesFragment.this.startActivity(ProductsActivity.getStartIntent(CategoriesFragment.this.getContext()));
            getActivity().finish();
        }
    }

    public class CategoryAdapter extends Adapter<CategoryAdapter.Holder> {

        public class Holder extends ViewHolder {
            @Bind(R.id.imgIcon)
            ImageView imgIcon;
            @Bind(R.id.txtTitle)
            TextView txtTitle;

            public Holder(View itemView) {
                super(itemView);
                ButterKnife.bind((Object) this, itemView);
            }
        }

        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(CategoriesFragment.this.getContext()).inflate(R.layout.item_category, parent, false));
        }

        public void onBindViewHolder(Holder holder, int position) {
            Category category = (Category) CategoriesFragment.this.data.get(position);
            holder.txtTitle.setText(category.getTitle());
            holder.imgIcon.setImageResource(category.getIcon());
        }

        public int getItemCount() {
            return CategoriesFragment.this.data.size();
        }
    }

    public CategoriesFragment() {
        this.data = new ArrayList();
        this.search_data = new ArrayList();
    }

    public static CategoriesFragment newInstance() {
        Bundle args = new Bundle();
        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind((Object) this, view);
        if (getActivity() == null) {
            mPager.getAdapter().notifyDataSetChanged();
        }
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.rv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle(R.string.kikbac);

        this.data = TestData.getCategories();
        this.adapter = new CategoryAdapter();
        this.rv.setNestedScrollingEnabled(false);
        this.rv.setHasFixedSize(false);
        this.rv.setAdapter(this.adapter);
        this.rv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new C05621()));

        String[] languages = {"Android ", "rebates", "ans", "SQL", "JDBC", "Web services"};
        this.search_data = TestData.getSearchResult();
        MySearchAdapter adpt = new MySearchAdapter(getActivity(), languages);
        this.txtSearch.setAdapter(adpt);

        mPager.setAdapter(getPagerAdapter(activity.getSupportFragmentManager()));
        indicator.setViewPager(mPager);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private FragmentStatePagerAdapter getPagerAdapter(FragmentManager fm) { // this must be upgrade with apis
        FragmentStatePagerAdapter adp = new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Fragment frgment = null;
                switch (position) {
                    case 0:
                        frgment = new CategoryDetailPagerFragment();
                        break;
                    case 1:
                        frgment = new CategoryDetailPagerFragment2();
                        break;
                }
                return frgment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        return adp;
    }

    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity) getActivity()).setExpanded(false);
    }

    public int getMenuVisibility() {
        return 0;
    }

    public class MySearchAdapter extends ArrayAdapter<String> {

        private LayoutInflater mInflater = null;
        private Activity activity;

        public MySearchAdapter(Activity a, String[] items) {
            super(a, 0, items);
            activity = a;
//            mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mInflater = LayoutInflater.from(activity);
        }

        public class ViewHolder {
            public TextView name;
            public TextView category;
            public TextView size;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_search_result,
                        parent, false);
                holder.name = (TextView) convertView.findViewById(R.id.txtName);
                holder.category = (TextView) convertView.findViewById(R.id.txtCategory);
                holder.size = (TextView) convertView.findViewById(R.id.txtSize);

                Bundle dta = search_data.get(0);
                holder.name.setText(dta.getString("name"));
                holder.category.setText(dta.getString("variety"));
                holder.size.setText(dta.getString("size"));

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }
    }
}
