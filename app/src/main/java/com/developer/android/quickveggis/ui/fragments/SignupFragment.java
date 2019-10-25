package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.developer.android.quickveggis.R;

public class SignupFragment extends Fragment {

    public static SignupFragment newInstance() {
        Bundle args = new Bundle();
        SignupFragment fragment = new SignupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_signup, container, false);
//        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Picasso.with(getContext()).load(R.drawable.logo).fit().centerInside().into(this.imgLogo);
//        Picasso.with(getContext()).load(R.drawable.splash).fit().centerCrop().into(this.imgBg);
//        this.btnFacebook.setOnClickListener(mOnClickListener);
//        this.btnGoogle.setOnClickListener(mOnClickListener);
//        this.btnSignIn.setOnClickListener(mOnClickListener);
//        this.btnSignUp.setOnClickListener(mOnClickListener);
    }

    private void login() {
//        startActivity(TutorialActivity.getStartIntent(getContext()));
//        getActivity().finish();
    }

    OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            login();
        }
    };
}
