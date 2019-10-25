package com.developer.android.quickveggis.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.config.Config;
import com.developer.android.quickveggis.model.Notification;
import com.developer.android.quickveggis.ui.fragments.CartFragment;
import com.developer.android.quickveggis.ui.fragments.CategoriesFragment;
import com.developer.android.quickveggis.ui.fragments.ConversationListFragment;
import com.developer.android.quickveggis.ui.fragments.MenuFragment;
import com.developer.android.quickveggis.ui.fragments.NotificationListFragment;
import com.developer.android.quickveggis.ui.fragments.ProductFragment;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.ActionItemBadge.BadgeStyles;
import com.mikepenz.actionitembadge.library.utils.BadgeDrawableBuilder;
import com.mikepenz.actionitembadge.library.utils.BadgeStyle;
import com.mikepenz.actionitembadge.library.utils.UIUtil;

import org.joda.time.MutableDateTime;

public class MainActivity extends AppCompatActivity {
    public static final int MENU_PRODUCT_VISIBLE = 3;
    public static final int MENU_CART_VISIBLE = 2;
    public static final int MENU_NOT_VISIBLE = 1;
    public static final int MENU_VISIBLE = 0;
    @Bind(R.id.appBar)
    AppBarLayout appBarLayout;
    private OnBackStackChangedListener backStackListener;
    @Bind(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    int currentMenuVisibility;
    @Bind(R.id.drawerLayout)
    public DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtOffline)
    public TextView txtOffline;

    /* renamed from: com.quickveggies.quickveggies.ui.activity.MainActivity.2 */
    class C02632 implements OnClickListener {
        final /* synthetic */ MenuItem menuItem;

        C02632(MenuItem menuItem) {
            this.menuItem = menuItem;
        }

        public void onClick(View v) {
            MainActivity.this.onOptionsItemSelected(menuItem);
        }
    }

    public interface MenuController {
        int getMenuVisibility();
    }

    /* renamed from: com.quickveggies.quickveggies.ui.activity.MainActivity.3 */
    class C05593 implements OnBackStackChangedListener {
        C05593() {
        }

        public void onBackStackChanged() {
            updateMenu(FragmentUtils.getCurrent(MainActivity.this));
            setNavIcon();
        }
    }

    public MainActivity() {
        backStackListener = new C05593();
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    public static Intent getStartIntent(Context context, int mode) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("mode", mode);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind((Activity) this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportFragmentManager().addOnBackStackChangedListener(backStackListener);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.setToolbarNavigationClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });
        mDrawerToggle.syncState();

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        FragmentUtils.changeFragment((FragmentActivity) this, R.id.menu, MenuFragment.newInstance(), false);

        int mode = getIntent().getIntExtra("mode", -1);
        if (mode == Config.PRODUCT_MODE) {
            setExpanded(false);
            collapsingToolbar.setEnabled(false);
            FragmentUtils.changeFragment((FragmentActivity)this, R.id.content, ProductFragment.newInstance(), false);
        } else if (mode == -1) {
            FragmentUtils.changeFragment((FragmentActivity) this, R.id.content, CategoriesFragment.newInstance(), false);
        } else {//cartFragment - offmode = 2, onmode=1
            FragmentUtils.changeFragment((FragmentActivity) this, R.id.content, CartFragment.newInstance(mode), false);
        }
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.expandedappbar);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int mode = intent.getIntExtra("mode", -1);
        FragmentUtils.popBackStack(this);
        FragmentUtils.changeFragment((FragmentActivity) this, R.id.content, CartFragment.newInstance(mode), false);
    }

    public boolean onPrepareOptionsMenu(Menu menu) { // because of this, the app crash ???

        setBadge(menu, R.id.action_messages, R.drawable.ic_message, MENU_NOT_VISIBLE);
        setBadge(menu, R.id.action_cart, R.drawable.ic_shopping, MENU_VISIBLE);
        setBadge(menu, R.id.action_notifications, R.drawable.ic_reminder, MENU_CART_VISIBLE);

        switch (currentMenuVisibility) {
            case MutableDateTime.ROUND_NONE /*0*/:
                menu.findItem(R.id.action_messages).setVisible(true);
                menu.findItem(R.id.action_cart).setVisible(true);
                menu.findItem(R.id.action_notifications).setVisible(true);
                menu.findItem(R.id.action_share).setVisible(false);
                break;
            case MENU_NOT_VISIBLE /*1*/:
                menu.findItem(R.id.action_messages).setVisible(false);
                menu.findItem(R.id.action_cart).setVisible(false);
                menu.findItem(R.id.action_notifications).setVisible(false);
                menu.findItem(R.id.action_share).setVisible(false);
                break;
            case MENU_CART_VISIBLE /*2*/:
                menu.findItem(R.id.action_messages).setVisible(false);
                menu.findItem(R.id.action_cart).setVisible(true);
                menu.findItem(R.id.action_notifications).setVisible(false);
                menu.findItem(R.id.action_share).setVisible(false);
                break;
            case MENU_PRODUCT_VISIBLE:/*3*/
                menu.findItem(R.id.action_messages).setVisible(false);
                menu.findItem(R.id.action_cart).setVisible(true);
                menu.findItem(R.id.action_notifications).setVisible(false);
                menu.findItem(R.id.action_share).setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    private void setBadge(Menu menu, int menuRes, int res, int badgeCount) {
        MenuItem menuItem = menu.findItem(menuRes);
        View view = menuItem.getActionView();
        view.setOnClickListener(new C02632(menuItem));
        View badgeView = view.findViewById(R.id.menu_badge);
        BadgeStyle style = BadgeStyles.RED.getStyle();
        UIUtil.setBackground(badgeView, new BadgeDrawableBuilder().corners(style.getCorner()).color(style.getColor()).colorPressed(style.getColorPressed()).build(this));
        ActionItemBadge.update(menuItem, ContextCompat.getDrawable(this, res), badgeCount);
        if (badgeCount > 0) {
            badgeView.setVisibility(View.VISIBLE);
        } else {
            badgeView.setVisibility(View.GONE);
        }
    }

    public void setExpanded(boolean expanded) {
        appBarLayout.setExpanded(expanded, true);
    }

    public void setTitle(int titleId) {
        super.setTitle(titleId);
        collapsingToolbar.setTitle(getString(titleId));
    }

    public void updateMenu(Fragment fragment) {
        if (fragment != null && (fragment instanceof MenuController)) {
            currentMenuVisibility = ((MenuController) fragment).getMenuVisibility();
            invalidateOptionsMenu();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void setNavIcon() {
        mDrawerToggle.setDrawerIndicatorEnabled(getSupportFragmentManager().getBackStackEntryCount() == 0);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.isDrawerIndicatorEnabled() && mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.action_notifications && getSupportFragmentManager().popBackStackImmediate()) {//???
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_messages /*2131493131*/:
                FragmentUtils.popBackStack(this);
                FragmentUtils.changeFragment((FragmentActivity) this, R.id.content, ConversationListFragment.newInstance(), false);
                break;

            case R.id.action_cart /*2131493133*/:
//                startActivity(CartActivity.getStartIntent(this));
                FragmentUtils.popBackStack(this);
                FragmentUtils.changeFragment((FragmentActivity) this, R.id.content, CartFragment.newInstance(2), false);//2 = offlinemode
                break;
            case R.id.action_notifications:
                FragmentUtils.popBackStack(this);
                FragmentUtils.changeFragment((FragmentActivity) this, R.id.content, NotificationListFragment.newInstance(), false);
//                startActivity(NotificationActivity.getStartIntent(this));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onBackPressed() {

        super.onBackPressed();
    }

    public void openMenu() {

        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    public void closeMenu() {
        mDrawerLayout.closeDrawers();
    }

    public void toggleMenu() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtOffline.setVisibility(View.INVISIBLE);
    }
}
