package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.db.BankRepo;
import com.developer.android.quickveggis.model.BankAccount;
import com.developer.android.quickveggis.ui.activity.PayoutActivity;
//import com.quickveggies.quickveggies.db.BankRepo;

public class BankAccountFragment extends Fragment {
    BankAccount account;
    @Bind(R.id.btnNext)
    View btnNext;
    @Bind(R.id.edBankBranch)
    EditText edBranch;
    @Bind(R.id.edDeliveryAddress)
    EditText edDeliveryAddress;
    @Bind(R.id.edMobile)
    EditText edMobile;
    @Bind(R.id.edName)
    EditText edName;
    @Bind(R.id.edZip)
    EditText edZip;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.BankAccountFragment.1 */
    class C02831 implements OnClickListener {
        C02831() {
        }

        public void onClick(View v) {
            BankAccountFragment.this.saveAddress();
        }
    }

    public static BankAccountFragment newInstance() {
        Bundle args = new Bundle();
        BankAccountFragment fragment = new BankAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static BankAccountFragment newInstance(BankAccount account) {
        Bundle args = new Bundle();
        args.putSerializable("account", account);
        BankAccountFragment fragment = new BankAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        getActivity().setTitle(R.string.bank_fill_credentials);
        this.account = (BankAccount) bundle.getSerializable("account");
        this.btnNext.setOnClickListener(new C02831());
        fillInfo();
    }

    private void fillInfo() {
        if (this.account != null) {
            this.edName.setText(this.account.getName());
            this.edBranch.setText(this.account.getBranch());
            this.edDeliveryAddress.setText(this.account.getDeliveryAddress());
            this.edMobile.setText(this.account.getMobile());
            this.edZip.setText(this.account.getZip());
        }
    }

    private void saveAddress() {
        String name = this.edName.getText().toString();
        String branch = this.edBranch.getText().toString();
        String zip = this.edZip.getText().toString();
        String mobile = this.edMobile.getText().toString();
        String deliveryAddress = this.edDeliveryAddress.getText().toString();
        boolean error = false;
        if (TextUtils.isEmpty(name)) {
            error = true;
        }
        if (TextUtils.isEmpty(branch)) {
            error = true;
        }
        if (TextUtils.isEmpty(zip)) {
            error = true;
        }
        if (TextUtils.isEmpty(mobile)) {
            error = true;
        }
        if (TextUtils.isEmpty(deliveryAddress)) {
            error = true;
        }
        if (!error) {
            if (this.account == null) {
                this.account = new BankAccount();
            }
            this.account.setId(BankRepo.getInstance().getCount());//??
            this.account.setName(name);
            this.account.setBranch(branch);
            this.account.setDeliveryAddress(deliveryAddress);
            this.account.setMobile(mobile);
            this.account.setZip(zip);
            BankRepo.getInstance().save(this.account);
            ((PayoutActivity) getActivity()).updateAccountFragment();
        }
    }
}
