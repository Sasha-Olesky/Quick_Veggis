package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.activity.SigninActivity;
import com.developer.android.quickveggis.ui.activity.SignupActivity;
import com.developer.android.quickveggis.ui.activity.TutorialActivity;
import com.squareup.picasso.Picasso;

public class LoginFragment extends Fragment {
    @Bind(R.id.btnFacebook)
    View btnFacebook;
    @Bind(R.id.btnGoogle)
    View btnGoogle;
    @Bind(R.id.btnSignIn)
    View btnSignIn;
    @Bind(R.id.btnSignUp)
    View btnSignUp;
    @Bind(R.id.imgBg)
    ImageView imgBg;
    @Bind(R.id.imgLogo)
    ImageView imgLogo;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_login, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Picasso.with(getContext()).load(R.drawable.logo).fit().centerInside().into(this.imgLogo);
        Picasso.with(getContext()).load(R.drawable.splash).fit().centerCrop().into(this.imgBg);
        this.btnFacebook.setOnClickListener(mOnClickListener);
        this.btnGoogle.setOnClickListener(mOnClickListener);
        this.btnSignIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SigninActivity.getStartIntent(getContext()));
            }
        });
        this.btnSignUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SignupActivity.getStartIntent(getContext()));
            }
        });
    }

    private void login() {
        startActivity(TutorialActivity.getStartIntent(getContext()));
    }

    OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            login();
        }
    };
}
