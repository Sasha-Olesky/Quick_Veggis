package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.activity.MainActivity;
import com.developer.android.quickveggis.ui.activity.PayoutActivity;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.squareup.picasso.Picasso;

public class GiftCardConfirmFragment extends Fragment implements MainActivity.MenuController {
    @Bind(R.id.img)
    ImageView image;
    @Bind(R.id.txtPrice)
    TextView txtPrice;
    @Bind(R.id.btnNext)
    View btnNext;

    public static GiftCardConfirmFragment newInstance() {
        Bundle args = new Bundle();
        GiftCardConfirmFragment fragment = new GiftCardConfirmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giftcard, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.txtPrice.setText("$20");
        Picasso.with(getContext()).load("http://library.corporate-ir.net/library/17/176/176060/mediaitems/93/a.com_logo_RGB.jpg").fit().centerInside().into(this.image);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.changeFragment(getActivity(), (int) R.id.content, SuccessFragment.newInstance(), false);
            }
        });
    }

    public int getMenuVisibility() {
        return 1;
    }
}
