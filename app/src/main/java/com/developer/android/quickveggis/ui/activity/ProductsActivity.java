package com.developer.android.quickveggis.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.fragments.FilterFragment;
import com.developer.android.quickveggis.ui.fragments.ProductsFragment;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;

public class ProductsActivity extends AppCompatActivity {
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btnFilter)
    ImageView txtFilter;

    /* renamed from: com.quickveggies.quickveggies.ui.activity.ProductsActivity.1 */
    class C02651 implements OnClickListener {
        C02651() {
        }

        public void onClick(View v) {
            ProductsActivity.this.toggleMenu();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ProductsActivity.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_products);
        ButterKnife.bind((Activity) this);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.app_name);
        FragmentUtils.changeFragment((FragmentActivity) this, (int) R.id.content, ProductsFragment.newInstance(), false);
        FragmentUtils.changeFragment((FragmentActivity) this, (int) R.id.filter, FilterFragment.newInstance(), false);
        this.txtFilter.setOnClickListener(new C02651());
    }

    public void toggleMenu() {
        if (this.drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            this.drawerLayout.closeDrawers();
        } else {
            this.drawerLayout.openDrawer(Gravity.RIGHT);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        onBackPressed();
        return true;
    }
}
