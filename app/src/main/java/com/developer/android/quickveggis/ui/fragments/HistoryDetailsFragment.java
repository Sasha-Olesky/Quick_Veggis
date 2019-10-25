package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.activity.MainActivity;
import com.developer.android.quickveggis.ui.activity.TrackingActivity;

public class HistoryDetailsFragment extends Fragment implements MainActivity.MenuController {
    @Bind(R.id.blockPaymentAddress)
    View blockAddress;
    @Bind(R.id.blockItems)
    LinearLayout blockItems;
    @Bind(R.id.blockPaymentMethod)
    View blockMethod;
    @Bind(R.id.blockPaymentStatus)
    View blockStatus;
    @Bind(R.id.btnTrack)
    View btnTrack;
    @Bind(R.id.txtTotal)
    TextView txtTotal;
    int type;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.HistoryDetailsFragment.1 */
    class C02981 implements OnClickListener {
        C02981() {
        }

        public void onClick(View v) {
            HistoryDetailsFragment.this.startActivity(TrackingActivity.getStartIntent(HistoryDetailsFragment.this.getContext()));
        }
    }

    public static HistoryDetailsFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        HistoryDetailsFragment fragment = new HistoryDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_details, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.type = getArguments().getInt("type");
        fillAnswers();
        updateUiState();
        generateTotalText();
        if (this.type == 1) {
            this.btnTrack.setVisibility(8);
        }
        this.btnTrack.setOnClickListener(new C02981());
    }

    private void generateTotalText() {
        if (this.type == 2) {
            this.txtTotal.setText(genTotalDeliver("Rs 92.00", "Rs 32.00", "Rs 98.23"));
        } else {
            this.txtTotal.setText(genTotalRedeem("Rs 23.45"));
        }
    }

    private SpannableString genTotalDeliver(String subtotal, String shipping, String total) {
//        SpannableString sp = new SpannableString(getString(R.string.history_temp_total_delivery, subtotal, shipping, total)); ///??? - this is origin
        SpannableString sp = new SpannableString(getString(R.string.history_temp_total_delivery, total));
        String result = sp.toString();
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.blue)), result.lastIndexOf(total), result.length(), 0);
        return sp;
    }

    private SpannableString genTotalRedeem(String total) {
        SpannableString sp = new SpannableString(getString(R.string.history_temp_total_redeem, total));
        String result = sp.toString();
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.blue)), result.lastIndexOf(total), result.length(), 0);
        return sp;
    }

    public void updateUiState() {
        if (this.type == 2) {
            this.blockMethod.setVisibility(0);
            this.blockAddress.setVisibility(0);
            this.blockStatus.setVisibility(8);
            return;
        }
        this.blockMethod.setVisibility(8);
        this.blockAddress.setVisibility(8);
        this.blockStatus.setVisibility(0);
    }

    private void fillAnswers() {
        this.blockItems.removeAllViews();
        for (int i = 0; i < 4; i++) {
            this.blockItems.addView(LayoutInflater.from(getContext()).inflate(R.layout.item_history_item, this.blockItems, false));
        }
    }

    public int getMenuVisibility() {
        return 1;
    }
}
