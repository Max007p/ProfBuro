package com.org.profburo.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.org.profburo.R;
import com.org.profburo.adapters.ParticipiantsAdapter;
import com.org.profburo.network.RestApi;
import com.org.profburo.network.responsesEntities.participiants.ParticipiantsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParticipiantsFragment extends Fragment {
    private DrawerLayout drawer;
    private RecyclerView recyclerView;
    private ImageView toNav;
    private TextView searchText;
    private List<ParticipiantsResponse> users;

    public ParticipiantsFragment(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.participiants_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerSettingUp(view);
        initClickable(view);

    }

    private void recyclerSettingUp(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.participiants_list);
        RestApi.getInstance()
                .getApi()
                .getAll(null)
                .enqueue(new Callback<List<ParticipiantsResponse>>() {
                    @Override
                    public void onResponse(Call<List<ParticipiantsResponse>> call, Response<List<ParticipiantsResponse>> response) {
                        if (response.isSuccessful())
                        {
                            users = new ArrayList<>(response.body());
                            ParticipiantsAdapter adapter = new ParticipiantsAdapter(users);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ParticipiantsResponse>> call, Throwable t) {

                    }
                });

    }

    private void initClickable(View view)
    {
        toNav = (ImageView) view.findViewById(R.id.toNavBar);
        searchText = (TextView) view.findViewById(R.id.search_text);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    RestApi.getInstance()
                            .getApi()
                            .getAll(null)
                            .enqueue(new Callback<List<ParticipiantsResponse>>() {
                                @Override
                                public void onResponse(Call<List<ParticipiantsResponse>> call, Response<List<ParticipiantsResponse>> response) {
                                    if (response.isSuccessful())
                                    {
                                        users = new ArrayList<>(response.body());
                                        ParticipiantsAdapter adapter = new ParticipiantsAdapter(users);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<ParticipiantsResponse>> call, Throwable t) {

                                }
                            });
                }
                else
                {
                    RestApi.getInstance()
                            .getApi()
                            .getAll(s.toString())
                            .enqueue(new Callback<List<ParticipiantsResponse>>() {
                                @Override
                                public void onResponse(Call<List<ParticipiantsResponse>> call, Response<List<ParticipiantsResponse>> response) {
                                    if (response.isSuccessful() && response.body() != null)
                                    {
                                        users = new ArrayList<>(response.body());
                                        ParticipiantsAdapter adapter = new ParticipiantsAdapter(users);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<ParticipiantsResponse>> call, Throwable t) {

                                }
                            });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        toNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }
}
