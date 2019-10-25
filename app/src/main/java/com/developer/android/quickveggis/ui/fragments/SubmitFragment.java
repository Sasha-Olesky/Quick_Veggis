package com.developer.android.quickveggis.ui.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.api.ApiRequest;
import com.developer.android.quickveggis.api.model.OCRData;
import com.developer.android.quickveggis.api.model.RedeemData;
import com.developer.android.quickveggis.api.response.OCRResponse;
import com.developer.android.quickveggis.ui.dialog.ActionListener;
import com.developer.android.quickveggis.ui.dialog.WeGotITDialog;
import com.developer.android.quickveggis.ui.utils.AndroidUriUtils;
//import com.quickveggies.quickveggies.api.model.OCRData;
//import com.quickveggies.quickveggies.api.model.RedeemData;
//import com.quickveggies.quickveggies.api.response.OCRResponse;
//import com.quickveggies.quickveggies.ui.dialog.ActionListener;
//import com.quickveggies.quickveggies.ui.dialog.WeGotITDialog;
//import com.quickveggies.quickveggies.ui.utils.AndroidUriUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class SubmitFragment extends Fragment
        implements ActionListener {

    boolean isCancel = false;

    @Bind(R.id.btnCancel)
    View btnCancel;
    ArrayList<String> checks;
    ArrayList<String> group;

    @Bind(R.id.progressBar)
    View progressBar;
    Observable<OCRResponse> subscription;

    @Bind(R.id.txtProgress)
    TextView txtProgress;

    private Observable<List<String>> convertImagesCall(List<String> paramList) {
        return Observable.defer(new Func0() {
            public Observable<List<String>> call() {
//                List localList = SubmitFragment.this.convertImagesToBase64(paramList);
//                if ((localList != null) && (localList.size() > 0)) ;
//                for (Observable localObservable = Observable.just(localList); ; localObservable = null)
//                    return localObservable;
                return null;//test
            }
        });

    }

    private void gotItDialog() {
        if (isCancel){
            return;
        }
        setSentState();
        WeGotITDialog localWeGotITDialog = WeGotITDialog.newInstance(0);
        localWeGotITDialog.show(getChildFragmentManager(), "dialog");
        localWeGotITDialog.setListener(this);
    }

    public static SubmitFragment newInstance(ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2) {
        Bundle localBundle = new Bundle();
        localBundle.putStringArrayList("checks", paramArrayList1);
        localBundle.putStringArrayList("group", paramArrayList2);
        SubmitFragment localSubmitFragment = new SubmitFragment();
        localSubmitFragment.setArguments(localBundle);
        return localSubmitFragment;
    }

    private void setSendingState() {
        this.btnCancel.setVisibility(0);
        this.txtProgress.setVisibility(0);
        this.progressBar.setVisibility(0);
    }

    private void setSentState() {
        this.btnCancel.setVisibility(8);
        this.txtProgress.setVisibility(8);
        this.progressBar.setVisibility(8);
    }

    public List<String> convertImagesToBase64(List<String> paramList) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            String str1 = (String) localIterator.next();
            try
            {
                String str2 = Base64.encodeToString(AndroidUriUtils.getBytes(getContext().getContentResolver().openInputStream(Uri.parse(str1))), 0);
                localArrayList.add("data:image/jpeg;base64," + str2);
            }
            catch (IOException localIOException)
            {
                localIOException.printStackTrace();
            }
        }
        return localArrayList;
    }

    public void executeApi(List<String> paramList) {
        OCRData localOCRData = new OCRData();
        localOCRData.setImages(paramList);
        localOCRData.setOrderID(Long.valueOf(1L));
        localOCRData.setUserID(Long.valueOf(1L));
        RedeemData localRedeemData = new RedeemData();
        localRedeemData.setOcrData(localOCRData);
//        ApiRequest.getInstance().getApi().sendOCR(localRedeemData).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {
//            public void onCompleted() {
//            }
//
//            public void onError(Throwable paramThrowable) {
//                paramThrowable.printStackTrace();
//                if (SubmitFragment.this.getActivity() != null) {
//                    SubmitFragment.this.setSentState();
//                    SubmitFragment.this.showTryAgainDialog(SubmitFragment.this.getContext());
//                }
//            }
//
//            public void onNext(OCRResponse paramOCRResponse) {
//                if (SubmitFragment.this.getActivity() != null)
//                    SubmitFragment.this.gotItDialog();
//            }
//        });
    }

    public void onActivityCreated(@Nullable Bundle paramBundle) {
        super.onActivityCreated(paramBundle);
        getActivity().setTitle(R.string.submit);
        Bundle localBundle = getArguments();
        this.checks = localBundle.getStringArrayList("checks");
        this.group = localBundle.getStringArrayList("group");
        sendData();
        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                isCancel = true;
                SubmitFragment.this.getActivity().finish();
            }
        });
    }

    public void onContinueClicked(int paramInt) {
        getActivity().finish();
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View localView = paramLayoutInflater.inflate(R.layout.fragment_submit, paramViewGroup, false);
        ButterKnife.bind(this, localView);
        return localView;
    }

    private void testest() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotItDialog();
            }
        }, 3000);
    }

    public void sendData() {
        setSendingState();
        //testestteset???
        testest();

//        convertImagesCall(this.checks).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {
//            public void onCompleted() {
//            }
//
//            public void onError(Throwable paramThrowable) {
//                SubmitFragment.this.showTryAgainDialog(SubmitFragment.this.getContext());
//                paramThrowable.printStackTrace();
//            }
//
//            public void onNext(List<String> paramList) {
//                SubmitFragment.this.executeApi(paramList);
//            }
//        });
    }

    public void showTryAgainDialog(Context paramContext) {
        setSentState();
        new AlertDialog.Builder(paramContext).setMessage(2131099857).setPositiveButton(2131099878, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                SubmitFragment.this.sendData();
            }
        }).setNegativeButton(2131099782, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                SubmitFragment.this.getActivity().finish();
            }
        }).create().show();
    }

}