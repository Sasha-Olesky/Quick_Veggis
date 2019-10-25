package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

public class SurveyRadioFragment extends Fragment {
    List<String> answers;
    @Bind(R.id.btnNext)
    View btnNext;
    @Bind(R.id.radioAnswers)
    RadioGroup radioAnswers;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.SurveyRadioFragment.1 */
    class C03201 implements OnClickListener {
        C03201() {
        }

        public void onClick(View v) {
            FragmentUtils.changeFragment(SurveyRadioFragment.this.getActivity(), (int) R.id.content, SurveyCheckFragment.newInstance(), false);
        }
    }

    public SurveyRadioFragment() {
        this.answers = new ArrayList();
    }

    public static SurveyRadioFragment newInstance() {
        Bundle args = new Bundle();
        SurveyRadioFragment fragment = new SurveyRadioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey_radio, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.btnNext.setOnClickListener(new C03201());
        fillTest();
        fillAnswers();
    }

    private void fillTest() {
        this.answers.clear();
        for (int i = 0; i < 4; i++) {
            this.answers.add("Radio Answer " + (i + 1));
        }
    }

    private void fillAnswers() {
        this.radioAnswers.removeAllViews();
        for (String answer : this.answers) {
            RadioButton radioButton = (RadioButton) LayoutInflater.from(getContext()).inflate(R.layout.item_survey_radio, this.radioAnswers, false);
            radioButton.setText(answer);
            this.radioAnswers.addView(radioButton);
        }
    }
}
