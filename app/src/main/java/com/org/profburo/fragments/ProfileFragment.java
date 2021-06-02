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

    public ProfileFragment(DrawerLayout drawer) {
        this.drawer = drawer;
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
                Toast message = new Toast(getContext());
                message.setText("Test has started");
                message.setDuration(Toast.LENGTH_LONG);
                message.show();
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
                                        Toast.makeText(getContext(), "Осуществляется переход к списку результатов теста", Toast.LENGTH_SHORT).show();
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
