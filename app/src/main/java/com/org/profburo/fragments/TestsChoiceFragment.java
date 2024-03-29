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
import com.org.profburo.adapters.TestDescriptionAdapter;
import com.org.profburo.network.RestApi;
import com.org.profburo.network.responsesEntities.test.TestsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.org.profburo.others.UtilitaryVariables.authorisedUser;

public class TestsChoiceFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageView toNav;
    private DrawerLayout drawer;
    private List<TestsResponse> tests;

    public TestsChoiceFragment(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_choice_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initClickable(view);
        recyclerSettingUp(view);

    }

    private void recyclerSettingUp(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.testsList);
        RestApi.getInstance()
                .getApi()
                .getTestsList(authorisedUser.getId())
                .enqueue(new Callback<List<TestsResponse>>() {
                    @Override
                    public void onResponse(Call<List<TestsResponse>> call, Response<List<TestsResponse>> response) {
                        if (response.isSuccessful())
                        {
                            tests = new ArrayList<>(response.body());
                            TestDescriptionAdapter adapter = new TestDescriptionAdapter(tests);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TestsResponse>> call, Throwable t) {

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
