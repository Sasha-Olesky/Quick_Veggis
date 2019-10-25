package com.developer.android.quickveggis.ui.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

import com.developer.android.quickveggis.App;
import com.developer.android.quickveggis.R;
import com.felipecsl.gifimageview.library.GifImageView;

import java.io.InputStream;

public class DialogUtils {

    /* renamed from: com.quickveggies.quickveggies.ui.utils.DialogUtils.1 */
    static class C03361 implements OnClickListener {
        C03361() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    public static ProgressDialog showProgressDialog(Activity activity, int message) {
        return showProgressDialog(activity, App.getContext().getString(message));
    }

    public static ProgressDialog showProgressDialog(Activity activity, String message) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    public static void showAlertDialog(Context context, String message) {
        new Builder(context).setMessage((CharSequence) message).setPositiveButton((CharSequence) "OK", new C03361()).create().show();
    }

    public static void showAlertDialog(Context context, int message) {
        showAlertDialog(context, App.getContext().getString(message));
    }

    public static void showConfirmDialog(Context context, int message) {
    }

    public static void showAlertUnLockDialog(Context context) {
        Dialog dlg = new Dialog(context);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dlg.setContentView(R.layout.unlock_dialog);
        GifImageView gifView = (GifImageView) dlg.findViewById(R.id.gifView);
        gifView.setBackgroundColor(Color.TRANSPARENT);

        try {
            InputStream is = context.getAssets().open("Unlock_to_redeem.gif");
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            is.close();

            gifView.setBytes(bytes);
            gifView.startAnimation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dlg.show();
    }
}
