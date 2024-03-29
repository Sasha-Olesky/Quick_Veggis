package com.developer.android.quickveggis.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.fragments.SigninFragment;
import com.developer.android.quickveggis.ui.fragments.SignupFragment;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;

public class SignupActivity extends AppCompatActivity {
    public static Intent getStartIntent(Context context) {
        return new Intent(context, SignupActivity.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        FragmentUtils.changeFragment(this, R.id.content, SignupFragment.newInstance(), false);
    }
}
