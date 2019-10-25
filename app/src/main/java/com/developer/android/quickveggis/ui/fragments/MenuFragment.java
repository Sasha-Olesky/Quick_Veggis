package com.developer.android.quickveggis.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.activity.ConversationActivity;
import com.developer.android.quickveggis.ui.activity.MainActivity;
import com.developer.android.quickveggis.ui.activity.ProfileActivity;
import com.developer.android.quickveggis.ui.activity.TutorialActivity;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.developer.android.quickveggis.ui.utils.RecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import org.joda.time.MutableDateTime;
import org.joda.time.chrono.EthiopicChronology;

public class MenuFragment extends Fragment {
    MenuAdapter adapter;
    @Bind(R.id.blockUser)
    View blockUser;
    List<Menu> data;
    @Bind(R.id.imgAvatar)
    ImageView imgAvatar;
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.txtName)
    TextView txtName;

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.MenuFragment.2 */
    class C03032 implements OnClickListener {
        C03032() {
        }

        public void onClick(View v) {
            MenuFragment.this.onItemClicked(Menu.Setting);
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.MenuFragment.3 */
    static /* synthetic */ class MenuItems {
        static final /* synthetic */ int[] values;

        static {
            values = new int[Menu.values().length];
            try {
                values[Menu.Wallet.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                values[Menu.Wishlist.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                values[Menu.Category.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                values[Menu.Setting.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                values[Menu.History.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                values[Menu.Service.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                values[Menu.Tutorail.ordinal()] = 7;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    enum Menu {
        Category(R.string.categories, R.drawable.menu_cat),
        History(R.string.history, R.drawable.menu_history),
        Wallet(R.string.wallet, R.drawable.menu_wallet),
        Wishlist(R.string.wishlist, R.drawable.menu_whishlist),
        Setting(R.string.settings, R.drawable.menu_setting),
        Bonuses(R.string.bonuses, R.drawable.menu_bonuses),
        Service(R.string.service, R.drawable.menu_service),
        Tutorail(R.string.tutorial, R.drawable.menu_tutorial);

        int icon;
        int title;

        private Menu(int title, int icon) {
            this.title = title;
            this.icon = icon;
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.fragment.MenuFragment.1 */
    class C05661 implements RecyclerItemClickListener.OnItemClickListener {
        C05661() {
        }

        public void onItemClick(View view, int position) {
            MenuFragment.this.onItemClicked((Menu) MenuFragment.this.data.get(position));
        }
    }

    public class MenuAdapter extends Adapter<MenuAdapter.Holder> {

        public class Holder extends ViewHolder {
            @Bind(R.id.imgIcon)
            ImageView imgIcon;
            @Bind(R.id.txt_badge)
            TextView txtBadge;
            @Bind(R.id.txtTitle)
            TextView txtTitle;

            public Holder(View itemView) {
                super(itemView);
                ButterKnife.bind((Object) this, itemView);
            }
        }

        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(MenuFragment.this.getContext()).inflate(R.layout.item_menu, parent, false));
        }

        public void onBindViewHolder(Holder holder, int position) {
            Menu menu = (Menu) MenuFragment.this.data.get(position);
            holder.imgIcon.setImageResource(menu.icon);
            holder.txtTitle.setText(menu.title);
            if (menu.equals(Menu.Wallet)) {
                holder.txtBadge.setVisibility(0);
            } else {
                holder.txtBadge.setVisibility(8);
            }
        }

        public int getItemCount() {
            return MenuFragment.this.data.size();
        }
    }

    public MenuFragment() {
        this.data = Arrays.asList(new Menu[]{Menu.Category, Menu.History, Menu.Wallet, Menu.Wishlist, Menu.Setting, Menu.Service, Menu.Tutorail});
    }

    public static MenuFragment newInstance() {
        Bundle args = new Bundle();
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new MenuAdapter();
        this.rv.setAdapter(this.adapter);
        this.rv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new C05661()));
        this.blockUser.setOnClickListener(new C03032());
        updateUserInfo();
    }

    private void onItemClicked(Menu menu) {
        ((MainActivity) getActivity()).closeMenu();
        Fragment fragment = null;
        switch (MenuItems.values[menu.ordinal()]) {
            case 1   /*Wallet*/:
                fragment = WalletFragment.newInstance();
                break;
            case 2 /*Wishlist*/:
                fragment = WishlistFragment.newInstance();
                break;
            case 3 /*Categories*/:
                fragment = CategoriesFragment.newInstance();
                break;
            case 4 /*Setting - Profile*/:
                startActivity(ProfileActivity.getStartIntent(getContext()));
                break;
            case 5 /*RedeemHistory*/:
//                fragment = OrderHistoryFragment.newInstance();    //show tablayout
                fragment = HistoryListFragment.newInstance(1);
                break;
            case 6 /*Service*/:
//                fragment = ConversationListFragment.newInstance();
                startActivity(ConversationActivity.getStartIntent(getContext()));
                break;
            case 7 /*Tutorial*/:
//                fragment = Walk2PagerFragment.newInstance();
                break;
        }

        if (MenuItems.values[menu.ordinal()] == 7) {
            getActivity().startActivity(new Intent(getActivity(), TutorialActivity.class));
            getActivity().finish();
        }

        if (fragment != null) {
            FragmentUtils.popBackStack(getActivity());
            FragmentUtils.changeFragment((FragmentActivity)getActivity(), R.id.content, fragment, false);
        }
    }

    private void updateUserInfo() {
        Picasso.with(getContext()).load(R.drawable.ic_default_avatar).fit().centerCrop().into(this.imgAvatar);
    }
}
