package com.org.profburo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.org.profburo.R;
import com.org.profburo.entities.User;
import com.org.profburo.fragments.ProfileFragment;
import com.org.profburo.fragments.ReportFragment;
import com.org.profburo.fragments.SettingsFragment;
import com.org.profburo.fragments.TestsChoiceFragment;
import com.org.profburo.network.RestApi;
import com.org.profburo.others.UtilitaryVariables;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.org.profburo.others.UtilitaryVariables.authorisedUser;

public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FragmentTransaction fragmentTransaction;
    private ProfileFragment selectedProfileFragment;
    private TestsChoiceFragment selectedTestChoiceFragment;
    private ReportFragment selectedReportFragment;
    private SettingsFragment selectedSettingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        startScreen(authorisedUser.getPermission());
        setNavigation(savedInstanceState);
        hideUI(authorisedUser.getPermission());
    }

    private void startScreen(String role)
    {
        switch (role)
        {
            case "mature":

            case "student":
                selectedProfileFragment = new ProfileFragment(drawer);
                fragmentTransaction.replace(R.id.frame, selectedProfileFragment);
                fragmentTransaction.commit();
                break;

            case "teacher":
                break;

            case "worker":
                break;
        }

    }

    private void hideUI(String role)
    {
        switch (role)
        {
            case "mature":
                navigationView.getMenu().getItem(0).setVisible(false);
                navigationView.getMenu().getItem(1).setVisible(false);
                navigationView.getMenu().getItem(2).setVisible(false);
                break;
            case "student":
                navigationView.getMenu().getItem(0).setVisible(false);
                navigationView.getMenu().getItem(1).setVisible(false);
                navigationView.getMenu().getItem(2).setVisible(false);
                break;
            case "teacher":
                navigationView.getMenu().getItem(1).setVisible(false);
                navigationView.getMenu().getItem(2).setVisible(false);
                navigationView.getMenu().getItem(3).setVisible(false);
                break;
            case "worker":
                navigationView.getMenu().getItem(3).setVisible(false);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_testStat:

                break;

            case R.id.nav_participiants:

                break;

            case R.id.nav_teachers:

                break;

            case R.id.nav_test:
                selectedTestChoiceFragment = new TestsChoiceFragment(drawer);
                fragmentTransaction.replace(R.id.frame, selectedTestChoiceFragment);
                fragmentTransaction.addToBackStack("tests");
                break;

            case R.id.nav_settings:
                selectedSettingsFragment = new SettingsFragment(drawer);
                fragmentTransaction.replace(R.id.frame, selectedSettingsFragment);
                fragmentTransaction.addToBackStack("settings");
                break;

            case R.id.nav_profile:
                selectedProfileFragment = new ProfileFragment(drawer);
                fragmentTransaction.replace(R.id.frame, selectedProfileFragment);
                fragmentTransaction.addToBackStack("profile");
                break;

            case R.id.nav_report:
                selectedReportFragment = new ReportFragment(drawer);
                fragmentTransaction.replace(R.id.frame, selectedReportFragment);
                fragmentTransaction.addToBackStack("report");
                break;

            case R.id.nav_logout:
                Intent logout = new Intent(MainPage.this, LoginForm.class);
                startActivity(logout);
                break;
        }

        fragmentTransaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setNavigation(Bundle savedInstanceState)
    {
        setActionBar(toolbar);
        getActionBar().hide();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_profile);
        }

        View navHeader = navigationView.getHeaderView(0);
        TextView username = navHeader.findViewById(R.id.username);
        TextView email = navHeader.findViewById(R.id.email);
        username.setText(authorisedUser.getFirstName());
        email.setText(authorisedUser.getEmail());
    }
}
