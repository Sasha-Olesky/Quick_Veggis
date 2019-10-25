package com.developer.android.quickveggis.ui.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.config.Config;
import com.developer.android.quickveggis.ui.fragments.SplashFragment;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        FragmentUtils.changeFragment(this, R.id.content, SplashFragment.newInstance(), false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.startActivity(Walk1Activity.getStartIntent(SplashActivity.this));
                SplashActivity.this.finish();
            }
        }, Config.SPLASH_DELAY_TIME);
    }
}
