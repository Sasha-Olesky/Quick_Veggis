package com.developer.android.quickveggis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.db.BankRepo;
import com.developer.android.quickveggis.model.BankAccount;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

//import com.quickveggies.quickveggies.db.BankRepo;
import java.util.ArrayList;
import java.util.List;

public class BankAccountListFragment extends Fragment {
    BankAdapter adapter;
    @Bind(R.id.btnNext)
    View btnNext;
    List<BankAccount> data;
    @Bind(R.id.fab)
    View fab;
    @Bind(R.id.rv)
    RecyclerView rv;
    int selected;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.BankAccountListFragment.1 */
    class C02841 implements OnClickListener {
        C02841() {
        }

        public void onClick(View v) {
            FragmentUtils.changeFragment(BankAccountListFragment.this.getActivity(), (int) R.id.content, SuccessFragment.newInstance(), false);
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.BankAccountListFragment.2 */
    class C02852 implements OnClickListener {
        C02852() {
        }

        public void onClick(View v) {
            FragmentUtils.changeFragment(BankAccountListFragment.this.getActivity(), (int) R.id.content, BankAccountFragment.newInstance(), false);
        }
    }

    public class BankAdapter extends Adapter<BankAdapter.Holder> {

        /* renamed from: com.quickveggies.quickveggies.ui.fragment.BankAccountListFragment.BankAdapter.1 */
        class C02861 implements OnClickListener {
            final /* synthetic */ BankAccount val$account;

            C02861(BankAccount bankAccount) {
                this.val$account = bankAccount;
            }

            public void onClick(View v) {
                BankAccountListFragment.this.onEditClick(this.val$account);
            }
        }

        /* renamed from: com.quickveggies.quickveggies.ui.fragment.BankAccountListFragment.BankAdapter.2 */
        class C02872 implements OnClickListener {
            final /* synthetic */ BankAccount val$account;
            final /* synthetic */ int val$position;

            C02872(int i, BankAccount bankAccount) {
                this.val$position = i;
                this.val$account = bankAccount;
            }

            public void onClick(View v) {
                BankAccountListFragment.this.onItemClicked(this.val$position, this.val$account);
            }
        }

        public class Holder extends ViewHolder {
            @Bind(R.id.blockSelection)
            View blockSelection;
            @Bind(R.id.btnEdit)
            View btnEdit;
            @Bind(R.id.txtHeader)
            TextView txtHeader;
            @Bind(R.id.txtContent)
            TextView txtTitle;

            public Holder(View itemView) {
                super(itemView);
                ButterKnife.bind((Object)this, itemView);
            }
        }

        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(BankAccountListFragment.this.getContext()).inflate(R.layout.item_bank_account, parent, false));
        }

        public void onBindViewHolder(Holder holder, int position) {
            if (data == null){
                return;
            }
            BankAccount account = (BankAccount) BankAccountListFragment.this.data.get(position);
            holder.txtHeader.setText(BankAccountListFragment.this.getString(R.string.bank_account, Integer.valueOf(position + 1)));
            holder.txtTitle.setText(getBankString(account));
            if (BankAccountListFragment.this.selected == position) {
                holder.blockSelection.setVisibility(0);
            } else {
                holder.blockSelection.setVisibility(8);
            }
            holder.btnEdit.setOnClickListener(new C02861(account));
            holder.itemView.setOnClickListener(new C02872(position, account));
        }

        private String getBankString(BankAccount account) {
            return BankAccountListFragment.this.getString(R.string.bank_item, account.getName(), account.getBranch(), account.getZip(), account.getMobile());
        }

        public int getItemCount() {
            return BankAccountListFragment.this.data.size();
        }
    }

    public BankAccountListFragment() {
        this.selected = -1;
        this.data = new ArrayList();
    }

    public static BankAccountListFragment newInstance() {
        Bundle args = new Bundle();
        BankAccountListFragment fragment = new BankAccountListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.bank_accounts);
        this.data = BankRepo.getInstance().getList();
        this.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new BankAdapter();
        this.rv.setAdapter(this.adapter);
//        this.rv.addItemDecoration(((VerticalDividerItemDecoration.Builder) ((HorizontalDividerItemDecoration.Builder) new Builder(getActivity()).color(ContextCompat.getColor(getContext(), 17170445))).sizeResId(R.dimen.divider_survey_image)).build());
        this.btnNext.setOnClickListener(new C02841());
        this.fab.setOnClickListener(new C02852());
    }

    private void onItemClicked(int position, BankAccount account) {
        if (position == this.selected) {
            this.selected = -1;
        } else {
            this.selected = position;
        }
        this.adapter.notifyDataSetChanged();
    }

    private void onEditClick(BankAccount account) {
        FragmentUtils.changeFragment(getActivity(), (int) R.id.content, BankAccountFragment.newInstance(account), true);
    }
}
