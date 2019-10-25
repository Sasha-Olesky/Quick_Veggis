package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.activity.SignupActivity;
import com.developer.android.quickveggis.ui.activity.TutorialActivity;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SigninFragment extends Fragment {

    @Bind(R.id.txtCreatAccount)
    View txtCreateAccount;

    public static SigninFragment newInstance() {
        Bundle args = new Bundle();
        SigninFragment fragment = new SigninFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_signin, container, false);
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
//        txtCreateAccount.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(SignupActivity.getStartIntent(getContext()));
//                getActivity().finish();
//            }
//        });
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
