package com.org.profburo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.org.profburo.R;
import com.org.profburo.adapters.TeachersDescriptionAdapter;
import com.org.profburo.adapters.TestStatisticAdapter;
import com.org.profburo.network.RestApi;
import com.org.profburo.network.responsesEntities.teachers.TeacherResponse;
import com.org.profburo.network.responsesEntities.test.TestStatistic;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestsStatisticFragment extends Fragment {

    private DrawerLayout drawer;
    private RecyclerView recyclerView;
    private ImageView toNav;
    private List<TestStatistic> tests;

    public TestsStatisticFragment(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tests_statistic_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initClickable(view);
        recyclerSettingUp(view);


    }

    private void recyclerSettingUp(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.stats_list);
        RestApi.getInstance()
                .getApi()
                .getTestStatistic()
                .enqueue(new Callback<List<TestStatistic>>() {
                    @Override
                    public void onResponse(Call<List<TestStatistic>> call, Response<List<TestStatistic>> response) {
                        if (response.isSuccessful())
                        {
                            tests = new ArrayList<>(response.body());
                            TestStatisticAdapter adapter = new TestStatisticAdapter(tests);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TestStatistic>> call, Throwable t) {

                    }
                });
    }

    private void initClickable(View view)
    {
        toNav = (ImageView) view.findViewById(R.id.toNavBar);

        toNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }
}
