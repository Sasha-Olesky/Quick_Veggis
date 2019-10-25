package com.developer.android.quickveggis.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.db.BankRepo;
import com.developer.android.quickveggis.ui.fragments.BankAccountListFragment;
import com.developer.android.quickveggis.ui.fragments.NoBankFoundFragment;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;

public class PayoutActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, PayoutActivity.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.actvity_check);
        ButterKnife.bind((Activity) this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.order);
        updateAccountFragment();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        onBackPressed();
        return true;
    }

    public void updateAccountFragment() {
        FragmentUtils.popBackStack(this);
        if (BankRepo.getInstance().getCount() > 0) {
            FragmentUtils.changeFragment((FragmentActivity) this, R.id.content, BankAccountListFragment.newInstance(), false);
        } else {
            FragmentUtils.changeFragment((FragmentActivity) this, R.id.content, NoBankFoundFragment.newInstance(), false);
        }
    }
}
