package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.App;
import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.TestData;
import com.developer.android.quickveggis.ui.activity.MainActivity;
import com.developer.android.quickveggis.ui.activity.SurveyVideoActivity;
import com.developer.android.quickveggis.ui.activity.TaskActivity;
import com.developer.android.quickveggis.ui.utils.DialogUtils;
import com.quickveggies.quickveggies.ui.custom.SlideButton;
import com.quickveggies.quickveggies.ui.custom.SlideButton.SlideButtonListener;
import com.squareup.picasso.Picasso;

import org.joda.time.MutableDateTime;
//import xyz.hanks.library.SmallBang;

public class ProductFragment extends Fragment implements SlideButtonListener , MainActivity.MenuController{
    static final int TAB_DESCRIPTION = 2;
    static final int TAB_TC = 1;
    @Bind(R.id.blockDescription)
    View blockDescription;
    @Bind(R.id.blockTC)
    View blockTC;
    @Bind(R.id.btnLike)
    ImageView btnLike;
    @Bind(R.id.btnShare)
    View btnShare;
    @Bind(R.id.btn_slide)
    SlideButton btnSlide;
    @Bind(R.id.btnSurvey)
    View btnSurvey;
    @Bind(R.id.btnVideo)
    View btnVideo;
    @Bind(R.id.img)
    ImageView image;
    //    SmallBang mSmallBang;
    View prevTab;
    @Bind(R.id.tabDescription)
    View tabDescription;
    @Bind(R.id.tabTC)
    View tabTc;
    @Bind(R.id.txtPriceShare)
    TextView txtPriceShare;
    @Bind(R.id.txtPriceSurvey)
    TextView txtPriceSurvey;
    @Bind(R.id.txtPriceVideo)
    TextView txtPriceVideo;

    @Override
    public int getMenuVisibility() {
        return 3;
    }

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.ProductFragment.1 */
    class C03081 implements OnClickListener {
        C03081() {
        }

        public void onClick(View v) {
//            ProductFragment.this.startActivity(SurveyActivity.getStartIntent(ProductFragment.this.getContext())); // origin
            startActivity(TaskActivity.getStartIntent(getContext())); // new
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.ProductFragment.2 */
    class C03092 implements OnClickListener {
        C03092() {
        }

        public void onClick(View v) {
            ProductFragment.this.startActivity(SurveyVideoActivity.getStartIntent(ProductFragment.this.getContext()));
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.ProductFragment.3 */
    class C03103 implements OnClickListener {
        C03103() {
        }

        public void onClick(View v) {
            ProductFragment.this.onTabSelected(ProductFragment.TAB_TC);
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.ProductFragment.4 */
    class C03114 implements OnClickListener {
        C03114() {
        }

        public void onClick(View v) {
            ProductFragment.this.onTabSelected(ProductFragment.TAB_DESCRIPTION);
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.ProductFragment.5 */
    class C03125 implements OnClickListener {
        C03125() {
        }

        public void onClick(View v) {
//            ProductFragment.this.mSmallBang.bang(ProductFragment.this.btnLike);
        }
    }

    public static ProductFragment newInstance() {
        Bundle args = new Bundle();
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductFragment newInstance(Bundle args) {
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.kikbac);
        int pos = getActivity().getIntent().getIntExtra("item", -1);
        if (pos == -1) {
            Picasso.with(getContext()).load("http://www.healthline.com/hlcmsresource/images/topic_centers/Food-Nutrition/642x361-2-Dairy_Milk-Almond_Milk_vs_Cow_Milk_vs_Soy_Milk_0.jpg").fit().centerInside().into(this.image);
        } else {
//            Picasso.with(getContext()).load(TestData.getNewProducts().get(pos).getImageId()).fit().centerInside().into(this.image);
            this.image.setImageResource(R.drawable.img_back);
        }


        updateTab(TAB_TC);
        this.tabTc.setSelected(true);
        this.prevTab = this.tabTc;
        this.btnSurvey.setOnClickListener(new C03081());
        this.btnVideo.setOnClickListener(new C03092());
        this.tabTc.setOnClickListener(new C03103());
        this.tabDescription.setOnClickListener(new C03114());
        setLockedState(true, this.btnShare, this.txtPriceShare);
        this.btnLike.setOnClickListener(new C03125());
//        this.mSmallBang = SmallBang.attach2Window(getActivity());
    }

    private void setLockedState(boolean state, View btn, TextView txt) {
        btn.setSelected(state);
        txt.setSelected(state);
    }

    public void onTabSelected(int i) {
        if (i == TAB_TC) {
            updateTab(TAB_TC);
        } else {
            updateTab(TAB_DESCRIPTION);
        }
    }

    private void updateTab(int tab) {
        if (tab == TAB_TC) {
            this.btnSlide.startAnimation(TAB_TC);
            this.tabTc.setSelected(true);
            this.tabDescription.setSelected(false);
//            this.blockDescription.setVisibility(8);
            this.blockTC.setVisibility(0);
            return;
        }
        this.btnSlide.startAnimation(100);
        this.tabTc.setSelected(false);
        this.tabDescription.setSelected(true);
        this.blockDescription.setVisibility(0);
        this.blockTC.setVisibility(8);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.getTask()){
            DialogUtils.showAlertUnLockDialog(getActivity());
            App.setTask(true);
        }
    }
}
