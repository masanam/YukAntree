package com.wartatv.yukantree.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.wartatv.yukantree.R;
import com.wartatv.yukantree.fragment.CategoryFragment;
import com.wartatv.yukantree.fragment.HomeFragment;
import com.wartatv.yukantree.fragment.MyOrderFragment;
import com.wartatv.yukantree.fragment.NewProductFragment;
import com.wartatv.yukantree.fragment.OffrersFragment;
import com.wartatv.yukantree.fragment.PopularProductFragment;
import com.wartatv.yukantree.fragment.ProfileFragment;
import com.wartatv.yukantree.helper.Converter;
import com.wartatv.yukantree.model.User;
import com.wartatv.yukantree.util.Preferences;
import com.wartatv.yukantree.util.localstorage.LocalStorage;
import java.util.ArrayList;

/**
 * Created by .
 * www.wartatv.com
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static int cart_count = 0;
    User user;
    Fragment fragment = null;

    @SuppressLint("ResourceAsColor")
    static void centerToolbarTitle(@NonNull final Toolbar toolbar) {
        final CharSequence title = toolbar.getTitle();
        final ArrayList<View> outViews = new ArrayList<>(1);
        toolbar.findViewsWithText(outViews, title, View.FIND_VIEWS_WITH_TEXT);
        if (!outViews.isEmpty()) {
            final TextView titleView = (TextView) outViews.get(0);
            titleView.setGravity(Gravity.CENTER);
            titleView.setTextColor(Color.parseColor("#FAD23C"));
            final Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) titleView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            toolbar.requestLayout();
            //also you can use titleView for changing font: titleView.setTypeface(Typeface);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    /*public void toggleCommunicationGroup(View button) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        MenuItem group = navigationView.getMenu().findItem(R.id.nav_communication_group);
        boolean isVisible = group.isVisible();
        group.setVisible(!isVisible);
        Button toggleButton = (Button)findViewById(R.id.main_toggle_button);
        if (isVisible) {
            toggleButton.setText("Enable communication group");
        } else {
            toggleButton.setText("Disable communication group");
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this, cart_count, R.drawable.ic_shopping_basket));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart_action:
                startActivity(new Intent(getApplicationContext(), QueueActivity.class));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        centerToolbarTitle(toolbar);
        cart_count = cartCount();

//float-button
        FloatingActionButton fab = findViewById(R.id.fab_menu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new HomeFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_to_left,R.anim.slide_from_right);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        ImageView home=findViewById(R.id.nav_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new HomeFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_to_left,R.anim.slide_from_right);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        ImageView feed=findViewById(R.id.nav_history);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new MyOrderFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_to_left,R.anim.slide_from_right);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        ImageView notification=findViewById(R.id.nav_help);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new MyOrderFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_to_left,R.anim.slide_from_right);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        ImageView profile=findViewById(R.id.nav_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ProfileFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_to_left,R.anim.slide_from_right);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        localStorage = new LocalStorage(getApplicationContext());
        String userString = localStorage.getUserLogin();
        Gson gson = new Gson();
        userString = localStorage.getUserLogin();
        user = gson.fromJson(userString, User.class);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);

        TextView nav_user = hView.findViewById(R.id.nav_header_name);
        LinearLayout nav_footer = findViewById(R.id.footer_text);
        if (user != null) {
            nav_user.setText(user.getName());
        }
        nav_footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localStorage.logoutUser();
                Preferences.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(getApplicationContext(), LoginRegisterActivity.class));
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                // Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
            }
        });

        displaySelectedScreen(R.id.nav_home);
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.nav_category:
                fragment = new CategoryFragment();
                break;
            case R.id.nav_popular_products:
                fragment = new PopularProductFragment();
                break;
            case R.id.nav_new_product:
                fragment = new NewProductFragment();
                break;

            case R.id.nav_offers:
                fragment = new OffrersFragment();
                break;
            case R.id.nav_search:
                //fragment = new CategoryFragment();
                break;
            case R.id.nav_my_order:
                fragment = new MyOrderFragment();
                break;
            case R.id.nav_my_cart:
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;
            case R.id.nav_history:
                fragment = new CategoryFragment();
                break;


        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
            ft.replace(R.id.content_frame, fragment);
            ft.commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }

    @Override
    public void onAddProduct() {
        super.onAddProduct();
        cart_count++;
        invalidateOptionsMenu();

    }

    @Override
    public void onRemoveProduct() {
        super.onRemoveProduct();
    }


}
