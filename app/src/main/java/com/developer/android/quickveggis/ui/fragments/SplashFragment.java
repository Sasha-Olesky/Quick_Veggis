package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.developer.android.quickveggis.R;
import com.squareup.picasso.Picasso;

public class SplashFragment extends Fragment {
    @Bind(R.id.imgBg)
    ImageView imgBg;
    @Bind({R.id.imgLogo})
    ImageView imgLogo;

    public static SplashFragment newInstance() {
        Bundle args = new Bundle();
        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_splash, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Picasso.with(getContext()).load((int) R.drawable.logo).fit().centerInside().into(this.imgLogo);
        Picasso.with(getContext()).load((int) R.drawable.splash).fit().centerCrop().into(this.imgBg);
    }
}
