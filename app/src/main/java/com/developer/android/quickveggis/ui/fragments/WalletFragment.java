package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.TestData;
import com.developer.android.quickveggis.model.Card;
import com.developer.android.quickveggis.ui.activity.MainActivity;
import com.developer.android.quickveggis.ui.activity.PayoutActivity;
import com.developer.android.quickveggis.ui.adapter.CardAdapter;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.quickveggies.quickveggies.ui.custom.PagerContainer;
import com.viewpagerindicator.CirclePageIndicator;

public class WalletFragment extends Fragment implements MainActivity.MenuController, CardAdapter.Listener{
    CardAdapter adapter;
    @Bind(R.id.btnPayout)
    View btnPayout;
    @Bind(R.id.pagerContainer)
    PagerContainer pagerContainer;
    @Bind(R.id.indicator)
    CirclePageIndicator indicator;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.WalletFragment.1 */

    public static WalletFragment newInstance() {
        Bundle args = new Bundle();
        WalletFragment fragment = new WalletFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.payout);
        final ViewPager viewPager = pagerContainer.getViewPager();
        adapter = new CardAdapter(getContext(), TestData.getCards(), pagerContainer, this);
        viewPager.setAdapter(adapter);
//        indicator.setViewPager(viewPager);
        viewPager.setCurrentItem(1, true);
        btnPayout.setOnClickListener(mOnClickListener);
    }

    OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(PayoutActivity.getStartIntent(getContext()));
        }
    };

    public int getMenuVisibility() {
        return 0;
    }

    public void onCardSelected(Card card) {
        FragmentUtils.changeFragment(getActivity(), R.id.content, GiftCardConfirmFragment.newInstance(), true);
    }
}
