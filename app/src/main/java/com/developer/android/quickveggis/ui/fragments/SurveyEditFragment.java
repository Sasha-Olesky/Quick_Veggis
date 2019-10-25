package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.activity.UnlockedActivity;

public class SurveyEditFragment extends Fragment {
    @Bind(R.id.btnNext)
    View btnNext;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.SurveyEditFragment.1 */
    class C03181 implements OnClickListener {
        C03181() {
        }

        public void onClick(View v) {
            SurveyEditFragment.this.getActivity().startActivity(UnlockedActivity.getStartIntent(SurveyEditFragment.this.getContext()));
            SurveyEditFragment.this.getActivity().finish();
        }
    }

    public static SurveyEditFragment newInstance() {
        Bundle args = new Bundle();
        SurveyEditFragment fragment = new SurveyEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey_memo, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.btnNext.setOnClickListener(new C03181());
    }
}
