package com.org.profburo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.org.profburo.R;

import static com.org.profburo.others.UtilitaryVariables.authorisedUser;

public class SettingsFragment extends Fragment {

    private ImageView toNav;
    private Button saveChanges;
    private DrawerLayout drawer;
    private EditText lastnameField;
    private EditText nameField;
    private EditText patronymicField;
    private DatePicker datePicker;
    private EditText phoneField;

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
        initData();

    }

    private void initClickable(View view)
    {
        toNav = (ImageView) view.findViewById(R.id.toNavBar);
        saveChanges = (Button) view.findViewById(R.id.saveChanges);
        lastnameField = (EditText) view.findViewById(R.id.lastnameField);
        nameField = (EditText) view.findViewById(R.id.nameField);
        patronymicField = (EditText) view.findViewById(R.id.patronymicField);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        phoneField = (EditText) view.findViewById(R.id.phoneField);

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
                message.setText("Данные отредактированы");
                message.setDuration(Toast.LENGTH_LONG);
                message.show();
            }
        });
    }

    private void initData()
    {
        lastnameField.setText(authorisedUser.getLastName());
        nameField.setText(authorisedUser.getFirstName());
        patronymicField.setText(authorisedUser.getMiddleName());
        int day = Integer.parseInt(authorisedUser.getBirthday().substring(0, authorisedUser.getBirthday().indexOf("-")));
        int month = Integer.parseInt(authorisedUser.getBirthday().substring(authorisedUser.getBirthday().indexOf("-"), authorisedUser.getBirthday().lastIndexOf("-")));
        int year = Integer.parseInt(authorisedUser.getBirthday().substring(authorisedUser.getBirthday().lastIndexOf("-")));
        datePicker.init(year, month, day, null);
        phoneField.setText(authorisedUser.getPhone());
    }

}
