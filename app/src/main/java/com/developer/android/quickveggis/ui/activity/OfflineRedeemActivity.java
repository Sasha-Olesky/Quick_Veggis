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
import com.developer.android.quickveggis.ui.dialog.ActionListener;
import com.developer.android.quickveggis.ui.dialog.NotifyDialog;
import com.developer.android.quickveggis.ui.fragments.SubmitFragment;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class OfflineRedeemActivity extends AppCompatActivity implements ActionListener {
    static final int ACTIVITY_GROUP = 2;
    static final int ACTIVITY_RECEIPT = 1;
    static final int DIALOG_MAKE_SURE = 1;
    static final int DIALOG_NOTIFY = 2;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, OfflineRedeemActivity.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_offline_redeem);
        ButterKnife.bind((Activity) this);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        makeSureDialog();
    }

    private void makeSureDialog() {
        if (NotifyDialog.isShowDialog(this, DIALOG_MAKE_SURE)) {
            NotifyDialog notifyDialog = NotifyDialog.newInstance(DIALOG_MAKE_SURE, R.string.make_sure, new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(R.string.item1), Integer.valueOf(R.string.item1), Integer.valueOf(R.string.item1)})));
            notifyDialog.show(getSupportFragmentManager(), "dialog");
            notifyDialog.setListener(this);
            return;
        }
        onContinueClicked(DIALOG_MAKE_SURE);
    }

    private void verifyDialog() {
        if (NotifyDialog.isShowDialog(this, DIALOG_NOTIFY)) {
            NotifyDialog notifyDialog = NotifyDialog.newInstance(DIALOG_NOTIFY, R.string.verify_purchases, new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(R.string.item1), Integer.valueOf(R.string.item1), Integer.valueOf(R.string.item1)})));
            notifyDialog.show(getSupportFragmentManager(), "dialog");
            notifyDialog.setListener(this);
            return;
        }
        onContinueClicked(DIALOG_NOTIFY);
    }

    public void onContinueClicked(int id) {
        if (id == DIALOG_MAKE_SURE) {
            startActivityForResult(CameraActivity.getStartIntent(this), DIALOG_MAKE_SURE);
        } else {
            startActivityForResult(GroupCameraActivity.getStartIntent(this), DIALOG_NOTIFY);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == -1) {
//            FragmentUtils.changeFragment(this, R.id.content, SubmitFragment.newInstance(null, null), false);
//        }
//        return;///??testestesttest
        if (requestCode == DIALOG_MAKE_SURE) {
            if (resultCode == -1) {
                ArrayList<String> links = data.getStringArrayListExtra("data");
                if (links == null || links.isEmpty()) {
                    finish();
                } else {
                    this.toolbar.setVisibility(0);
                    FragmentUtils.changeFragment((FragmentActivity) this, (int) R.id.content, SubmitFragment.newInstance(links, null), false);
                }
            } else {
                finish();
            }
        }
        if (requestCode != DIALOG_NOTIFY) {
            return;   //testesttes???
        }
        if (resultCode == -1) {
            this.toolbar.setVisibility(0);
            FragmentUtils.changeFragment((FragmentActivity) this, (int) R.id.content, SubmitFragment.newInstance(null, null), false);
            return;
        }
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        onBackPressed();
        return true;
    }
}
