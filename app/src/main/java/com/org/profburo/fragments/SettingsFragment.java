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

public class SettingsFragment extends Fragment {

    private ImageView toNav;
    private Button saveChanges;
    private DrawerLayout drawer;

    public SettingsFragment(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initClickable(view);

    }

    private void initClickable(View view)
    {
        toNav = (ImageView) view.findViewById(R.id.toNavBar);
        saveChanges = (Button) view.findViewById(R.id.saveChanges);

        toNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast message = new Toast(getContext());
                message.setText("Changes have saved");
                message.setDuration(Toast.LENGTH_LONG);
                message.show();
            }
        });
    }

}
