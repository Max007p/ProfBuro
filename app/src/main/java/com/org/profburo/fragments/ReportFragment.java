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

public class ReportFragment extends Fragment {

    private DrawerLayout drawer;
    private ImageView toNav;
    private Button sendReport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.report_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initClickable(view);

    }

    public ReportFragment(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    private void initClickable(View view)
    {
        toNav = (ImageView) view.findViewById(R.id.toNavBar);
        sendReport = (Button) view.findViewById(R.id.sendReport);

        toNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        sendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast message = new Toast(getContext());
                message.setText("Спасибо за отзыв!");
                message.setDuration(Toast.LENGTH_LONG);
                message.show();
            }
        });
    }
}
