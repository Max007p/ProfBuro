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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.org.profburo.R;
import com.org.profburo.adapters.TestDescriptionAdapter;
import com.org.profburo.entities.TestDescription;

import java.util.ArrayList;
import java.util.List;

public class TestsChoiceFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageView toNav;
    private Button startTest;
    private DrawerLayout drawer;

    public TestsChoiceFragment(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_choice_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initClickable(view);
        recyclerSettingUp(view);

    }

    private void recyclerSettingUp(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.testsList);

        List<String> list = new ArrayList<>();
        list.add("Профориентационный тест");
        list.add("Психологический тест");
        list.add("Эмоциональный тест");
        list.add("Профориентационный тест 2");
        list.add("Психологический тест 2");
        list.add("Эмоциональный тест 2");

        TestDescriptionAdapter adapter = new TestDescriptionAdapter(list);
        recyclerView.setAdapter(adapter);
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
