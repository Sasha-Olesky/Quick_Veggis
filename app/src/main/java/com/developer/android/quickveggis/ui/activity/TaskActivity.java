package com.developer.android.quickveggis.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.fragments.SurveyImageChooserFragment;
import com.developer.android.quickveggis.ui.fragments.TaskFragment;
import com.developer.android.quickveggis.ui.utils.DialogUtils;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TaskActivity extends AppCompatActivity {
    @Bind(R.id.btnClose)
    View btnClose;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, TaskActivity.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_task);
        ButterKnife.bind((Activity) this);
        this.btnClose.setOnClickListener(mOnClickListener);

        FragmentUtils.changeFragment((FragmentActivity) this, (int) R.id.content, TaskFragment.newInstance(), false);
    }

    OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivityForResult(new Intent(TaskActivity.this, ConfirmActivity.class), 1);
//            DialogUtils.showAlertDialog(TaskActivity.this, "test");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            this.finish();
        }
    }
}
