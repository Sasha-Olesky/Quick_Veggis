package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.developer.android.quickveggis.ui.utils.RecyclerItemClickListener;
import com.squareup.picasso.Picasso;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration.Builder;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;
import java.util.ArrayList;
import java.util.List;

public class SurveyImageChooserFragment extends Fragment {
    ImageAdapter adapter;
    List<String> answers;
    @Bind(R.id.btnNext)
    View btnNext;
    @Bind(R.id.rv)
    RecyclerView rv;
    int selected;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.SurveyImageChooserFragment.1 */
    class C03191 implements OnClickListener {
        C03191() {
        }

        public void onClick(View v) {
            FragmentUtils.changeFragment(SurveyImageChooserFragment.this.getActivity(), (int) R.id.content, SurveyRadioFragment.newInstance(), false);
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.SurveyImageChooserFragment.2 */
    class C05722 implements RecyclerItemClickListener.OnItemClickListener {
        C05722() {
        }

        public void onItemClick(View view, int position) {
            SurveyImageChooserFragment.this.selected = position;
            SurveyImageChooserFragment.this.adapter.notifyDataSetChanged();
        }
    }

    public class ImageAdapter extends Adapter<ImageAdapter.Holder> {

        public class Holder extends ViewHolder {
            @Bind(R.id.blockSelection)
            View blockSelection;
            @Bind(R.id.img)
            ImageView image;

            public Holder(View itemView) {
                super(itemView);
                ButterKnife.bind((Object) this, itemView);
            }
        }

        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(SurveyImageChooserFragment.this.getContext()).inflate(R.layout.item_survey_image, parent, false));
        }

        public void onBindViewHolder(Holder holder, int position) {
            Picasso.with(SurveyImageChooserFragment.this.getContext()).load((String) SurveyImageChooserFragment.this.answers.get(position)).fit().centerInside().into(holder.image);
            if (SurveyImageChooserFragment.this.selected == position) {
                holder.blockSelection.setVisibility(View.VISIBLE);
            } else {
                holder.blockSelection.setVisibility(View.INVISIBLE);
            }
        }

        public int getItemCount() {
            return SurveyImageChooserFragment.this.answers.size();
        }
    }

    public SurveyImageChooserFragment() {
        this.selected = -1;
        this.answers = new ArrayList();
    }

    public static SurveyImageChooserFragment newInstance() {
        Bundle args = new Bundle();
        SurveyImageChooserFragment fragment = new SurveyImageChooserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey_choose_image, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.btnNext.setOnClickListener(new C03191());
        this.rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.rv.addItemDecoration(((Builder) ((Builder) new Builder(getActivity()).color(getResources().getColor(17170445))).sizeResId(R.dimen.divider_survey_image)).build());
        this.rv.addItemDecoration(((VerticalDividerItemDecoration.Builder) ((VerticalDividerItemDecoration.Builder) new VerticalDividerItemDecoration.Builder(getActivity()).color(getResources().getColor(17170445))).sizeResId(R.dimen.divider_survey_image)).build());
        this.rv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new C05722()));
        fillTestData();
        this.adapter = new ImageAdapter();
        this.rv.setAdapter(this.adapter);
    }

    private void fillTestData() {
        this.answers.clear();
        this.answers.add("http://weknowyourdreamz.com/images/apple/apple-01.jpg");
        this.answers.add("http://images.apple.com/apple-events/static/apple-events/september-2013/video/poster_large.jpg");
        this.answers.add("https://www.hongkongfp.com/wp-content/uploads/2015/10/china-flag-apple-logo1.jpg");
        this.answers.add("http://images.clipartpanda.com/apple-clipart-apple5.png");
    }
}
