package com.org.profburo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.org.profburo.R;
import com.org.profburo.network.RestApi;
import com.org.profburo.network.responsesEntities.test.HasAnyCompletedTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.org.profburo.others.UtilitaryVariables.authorisedUser;

public class ProfileFragment extends Fragment {

    private ImageView toNav;
    private Button startTest;
    private Button seeResults;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    public ProfileFragment(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    public ProfileFragment(NavigationView navigationView, DrawerLayout drawer)
    {
        this.drawer = drawer;
        this.navigationView = navigationView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initClickable(view);

    }

    private void initClickable(View view)
    {
        toNav = (ImageView) view.findViewById(R.id.toNavBar);
        startTest = (Button) view.findViewById(R.id.startTest);
        seeResults = (Button) view.findViewById(R.id.seeResults);

        toNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest.setVisibility(View.GONE);
                seeResults.setVisibility(View.GONE);
                TestsChoiceFragment selectedTestChoiceFragment = new TestsChoiceFragment(drawer);
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.profile_main, selectedTestChoiceFragment);
                navigationView.setCheckedItem(R.id.nav_test);
                trans.commit();
            }
        });

        initSeeResultsButton();
    }

    private void initSeeResultsButton()
    {
        RestApi.getInstance()
                .getApi()
                .hasTests(authorisedUser.getId())
                .enqueue(new Callback<HasAnyCompletedTest>() {
                    @Override
                    public void onResponse(Call<HasAnyCompletedTest> call, Response<HasAnyCompletedTest> response) {
                        if (response.isSuccessful())
                        {
                            if (response.body().getHasCompletedTests() == false)
                            {
                                seeResults.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(getContext(), "У Вас нет пройденных тестов", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                seeResults.setAlpha(0.5f);
                            }
                            else
                            {
                                seeResults.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startTest.setVisibility(View.GONE);
                                        seeResults.setVisibility(View.GONE);
                                        TestsChoiceFragment selectedTestChoiceFragment = new TestsChoiceFragment(drawer);
                                        FragmentTransaction trans = getFragmentManager().beginTransaction();
                                        trans.replace(R.id.profile_main, selectedTestChoiceFragment);
                                        navigationView.setCheckedItem(R.id.nav_test);
                                        trans.commit();
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HasAnyCompletedTest> call, Throwable t) {

                    }
                });
    }

}
