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
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.model.ProfileMenu;
import com.developer.android.quickveggis.ui.activity.ProfileActivity;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.developer.android.quickveggis.ui.utils.RecyclerItemClickListener;
import com.developer.android.quickveggis.ui.utils.RecyclerItemClickListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration.Builder;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    private static final int MENU_ABOUT = 3;
    private static final int MENU_SETTINGS = 2;
    private static final int MENU_UPDATE_PROFILE = 1;
    private static final int MENU_LOGOUT = 4;
    MenuAdapter adapter;
    List<ProfileMenu> data;
    @Bind(R.id.rv)
    RecyclerView rv;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.ProfileFragment.1 */
    class C05691 implements RecyclerItemClickListener.OnItemClickListener {
        C05691() {
        }

        public void onItemClick(View view, int position) {
            ProfileFragment.this.onMenuClicked((ProfileMenu) ProfileFragment.this.data.get(position));
        }
    }

    public class MenuAdapter extends Adapter<MenuAdapter.Holder> {

        public class Holder extends ViewHolder {
            @Bind(R.id.txtTitle)
            TextView txtTitle;

            public Holder(View itemView) {
                super(itemView);
                ButterKnife.bind((Object) this, itemView);
            }
        }

        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(ProfileFragment.this.getContext()).inflate(R.layout.item_settings, parent, false));
        }

        public void onBindViewHolder(Holder holder, int position) {
            holder.txtTitle.setText(((ProfileMenu) ProfileFragment.this.data.get(position)).getTitle());
        }

        public int getItemCount() {
            return ProfileFragment.this.data.size();
        }
    }

    public ProfileFragment() {
        this.data = new ArrayList();
    }

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.profile);
        this.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        fillMenu();
        this.adapter = new MenuAdapter();
        this.rv.setAdapter(this.adapter);
        this.rv.addItemDecoration(((Builder) ((Builder) new Builder(getActivity()).color(ContextCompat.getColor(getContext(), R.color.divider))).sizeResId(R.dimen.dp1)).build());
        this.rv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new C05691()));
    }

    private void fillMenu() {
        data.clear();
        data.add(new ProfileMenu(R.string.update_profile, MENU_UPDATE_PROFILE));
        data.add(new ProfileMenu(R.string.settings, MENU_SETTINGS));
        data.add(new ProfileMenu(R.string.about, MENU_ABOUT));
        data.add(new ProfileMenu(R.string.log_out, MENU_LOGOUT));
    }

    private void onMenuClicked(ProfileMenu menu) {
        switch (menu.getId()) {
            case MENU_UPDATE_PROFILE /*1*/:
                FragmentUtils.changeFragment(getActivity(),R.id.content, ProfileUpdateFragment.newInstance(), true);
                break;
            case MENU_SETTINGS /*2*/:
                FragmentUtils.changeFragment(getActivity(),R.id.content, SettingsFragment.newInstance(), true);
                break;
            case MENU_ABOUT /*3*/:
                FragmentUtils.changeFragment(getActivity(), R.id.content, AboutFragment.newInstance(), true);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ProfileActivity)getActivity()).btnSave.setVisibility(View.GONE);
    }
}
