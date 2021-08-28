package com.org.profburo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.org.profburo.R;
import com.org.profburo.entities.User;
import com.org.profburo.network.RestApi;
import com.org.profburo.network.responsesEntities.login.LoginResponse;
import com.org.profburo.network.responsesEntities.region.RegionResponse;
import com.org.profburo.network.responsesEntities.registration.RegisterBody;
import com.org.profburo.network.responsesEntities.school.SchoolResponse;
import com.org.profburo.others.PermissionCode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.org.profburo.others.UtilitaryVariables.authorisedUser;

public class RegForm extends AppCompatActivity {

    HashMap<String, Integer> regionBind = new HashMap<>();

    //-----------------------------------------
    private View specialDivider;
    private TextView schoolText;
    private Spinner schoolNameSpinner;      //Прятаемая часть
    private TextView gradeText;
    private Spinner classSpinner;
    private TextView literaText;
    private Spinner literaSpinner;
    //----------------------------------------

    //----------------------------------------
    private Button regAccept;
    private EditText lastnameField;
    private EditText nameField;
    private EditText patronymicField;
    private DatePicker datePicker;
    private Spinner regionSpinner;
    private Spinner regionDistrictSpinner;
    private EditText serialPassportField;
    private EditText numberPassportField;
    private Spinner genderSpinner;
    private EditText emailField;
    private EditText phoneField;
    private EditText passwordField;
    private EditText secondPasswordField;
    //----------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_form);

        findAllViews();
        loadSpinners();
        hideUI(authorisedUser.getPermission());
        initClickable();
    }

    private void findAllViews()
    {
        //------------------------------------------------------------------
        regAccept = (Button) findViewById(R.id.regAccept);
        specialDivider = (View) findViewById(R.id.specialDivider);
        schoolText = (TextView) findViewById(R.id.schoolText);
        schoolNameSpinner = (Spinner) findViewById(R.id.schoolNameSpinner);     //Прятаемая часть
        gradeText = (TextView) findViewById(R.id.gradeText);
        classSpinner = (Spinner) findViewById(R.id.classSpinner);
        literaText = (TextView) findViewById(R.id.literaText);
        literaSpinner = (Spinner) findViewById(R.id.literaSpinner);
        //------------------------------------------------------------------

        //------------------------------------------------------------------
        lastnameField = (EditText) findViewById(R.id.lastnameField);
        nameField = (EditText) findViewById(R.id.nameField);
        patronymicField = (EditText) findViewById(R.id.patronymicField);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        regionSpinner = (Spinner) findViewById(R.id.regionSpinner);
        regionDistrictSpinner = (Spinner) findViewById(R.id.regionDistrictSpinner);
        serialPassportField = (EditText) findViewById(R.id.serialPassportField);
        numberPassportField = (EditText) findViewById(R.id.numberPassportField);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        emailField = (EditText) findViewById(R.id.emailField);
        phoneField = (EditText) findViewById(R.id.phoneField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        secondPasswordField = (EditText) findViewById(R.id.secondPasswordField);
        //------------------------------------------------------------------
    }

    private void loadSpinners()
    {
        //-------------------------------------------------
        List<String> region = new ArrayList<>();
        region.add("Ленинградская область");
        ArrayAdapter<String> regionAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_dropdownlist, region);
        regionAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        regionSpinner.setAdapter(regionAdapter);
        //-------------------------------------------------
        List<Integer> classes = new ArrayList<Integer>();
        classes.add(7);
        classes.add(8);
        classes.add(9);
        classes.add(10);
        classes.add(11);
        ArrayAdapter<Integer> classAdapter = new ArrayAdapter<Integer>(getApplicationContext(), R.layout.spinner_dropdownlist, classes);
        classAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);
        //-------------------------------------------------
        List<String> literas = new ArrayList<String>();
        literas.add("А");
        literas.add("Б");
        literas.add("В");
        literas.add("Г");
        literas.add("Д");
        ArrayAdapter<String> literaAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_dropdownlist, literas);
        literaAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        literaSpinner.setAdapter(literaAdapter);
        //-------------------------------------------------
        loadRegionSpinner();
        //-------------------------------------------------
        List<String> sexes = new ArrayList<>();
        sexes.add("мужчина");
        sexes.add("женщина");
        ArrayAdapter<String> sexAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_dropdownlist, sexes);
        sexAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        genderSpinner.setAdapter(sexAdapter);
    }

    private void loadRegionSpinner()
    {
        RestApi.getInstance()
                .getApi()
                .getRegions()
                .enqueue(new Callback<List<RegionResponse>>() {
                    @Override
                    public void onResponse(Call<List<RegionResponse>> call, Response<List<RegionResponse>> response) {
                        if (response.isSuccessful())
                        {
                            regionBind.clear();
                            List<String> regionsDistricts = new ArrayList<>();
                            for (int i = 0; i < response.body().size(); i++)
                            {
                                regionsDistricts.add(response.body().get(i).getName());
                                regionBind.put(response.body().get(i).getName(), response.body().get(i).getId());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_dropdownlist, regionsDistricts);
                            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
                            regionDistrictSpinner.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<RegionResponse>> call, Throwable t) {

                    }
                });

        regionDistrictSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer regionId = regionBind.get(regionDistrictSpinner.getItemAtPosition(position));
                loadSchoolSpinner(regionId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadSchoolSpinner(int regionId)
    {
        RestApi.getInstance()
                .getApi()
                .getSchools(regionId)
                .enqueue(new Callback<List<SchoolResponse>>() {
                    @Override
                    public void onResponse(Call<List<SchoolResponse>> call, Response<List<SchoolResponse>> response) {
                        if (response.isSuccessful())
                        {
                            List<String> schools = new ArrayList<>();
                            for (int i = 0; i < response.body().size(); i++)
                            {
                                schools.add(response.body().get(i).getName());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_dropdownlist, schools);
                            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
                            schoolNameSpinner.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SchoolResponse>> call, Throwable t) {

                    }
                });
    }

    private void hideUI(String role)
    {
        switch (role)
        {
            case "mature":
                specialDivider.setVisibility(View.GONE);
                schoolText.setVisibility(View.GONE);
                schoolNameSpinner.setVisibility(View.GONE);
                gradeText.setVisibility(View.GONE);
                classSpinner.setVisibility(View.GONE);
                literaText.setVisibility(View.GONE);
                literaSpinner.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void initClickable()
    {
        regAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked())
                {
                    register();
                }
            }
        });
    }

    private void register()
    {
        String lastnameFieldVar = lastnameField.getText().toString();
        String nameFieldVar = nameField.getText().toString();
        String patronymicFieldVar = patronymicField.getText().toString();
        String regionSpinnerVar = regionSpinner.getSelectedItem().toString();
        String regionDistrictSpinnerVar = regionDistrictSpinner.getSelectedItem().toString();
        String schoolNameSpinnerVar = schoolNameSpinner.getSelectedItem().toString();
        String classSpinnerVar = classSpinner.getSelectedItem().toString();
        String literaSpinnerVar = literaSpinner.getSelectedItem().toString();
        String serialPassportFieldVar = serialPassportField.getText().toString();
        String numberPassportFieldVar = numberPassportField.getText().toString();
        String genderSpinnerVar = genderSpinner.getSelectedItem().toString();
        String emailFieldVar = emailField.getText().toString();
        String phoneFieldVar = phoneField.getText().toString();
        String passwordFieldVar = passwordField.getText().toString();

        Integer birthDay = datePicker.getDayOfMonth();
        Integer birthMonth = datePicker.getMonth() + 1;
        Integer birthYear = datePicker.getYear();
        String birthDate = birthYear.toString() + "-" + birthMonth.toString() + "-" + birthDay.toString();

        String gradeVar = classSpinnerVar + " " + literaSpinnerVar;

        if (authorisedUser.getPermission().equals(PermissionCode.MATURE))
        {
            RestApi.getInstance()
                    .getApi()
                    .registerUser(new RegisterBody(
                            lastnameFieldVar,
                            nameFieldVar,
                            patronymicFieldVar,
                            birthDate,
                            regionDistrictSpinnerVar,
                            serialPassportFieldVar,
                            numberPassportFieldVar,
                            genderSpinnerVar,
                            emailFieldVar,
                            phoneFieldVar,
                            passwordFieldVar,
                            authorisedUser.getPermission()))
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful())
                            {
                                if (response.body().getStatus().equals("succeed"))
                                {
                                    authorisedUser = new User(response.body());
                                    Intent toLogPage = new Intent(RegForm.this, MainPage.class);
                                    startActivity(toLogPage);
                                }
                                else
                                {
                                    Toast.makeText(RegForm.this, "Такой пользователь уже существует", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });
        }
        else
        {
            RestApi.getInstance()
                    .getApi()
                    .registerUser(new RegisterBody(
                            lastnameFieldVar,
                            nameFieldVar,
                            patronymicFieldVar,
                            birthDate,
                            regionDistrictSpinnerVar,
                            schoolNameSpinnerVar,
                            gradeVar,
                            serialPassportFieldVar,
                            numberPassportFieldVar,
                            genderSpinnerVar,
                            emailFieldVar,
                            phoneFieldVar,
                            passwordFieldVar,
                            authorisedUser.getPermission()))
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful())
                            {
                                if (response.body().getStatus().equals("succeed"))
                                {
                                    authorisedUser = new User(response.body());
                                    Intent toLogPage = new Intent(RegForm.this, MainPage.class);
                                    startActivity(toLogPage);
                                    finishAfterTransition();
                                }
                                else
                                {
                                    Toast.makeText(RegForm.this, "Такой пользователь уже существует", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });
        }
    }

    private boolean isChecked()
    {
        if (lastnameField.getText().length() == 0 ||
            nameField.getText().length() == 0 ||
            patronymicField.getText().length() == 0 ||
            serialPassportField.getText().length() == 0 ||
            numberPassportField.getText().length() == 0 ||
            genderSpinner.getSelectedItem().toString().length() == 0 ||
            emailField.getText().length() == 0 ||
            phoneField.getText().length() == 0 ||
            passwordField.getText().length() == 0 ||
            secondPasswordField.getText().length() == 0)
        {
            Toast.makeText(RegForm.this, "Проверьте заполненность полей", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!passwordField.getText().toString().equals(secondPasswordField.getText().toString()))
        {
            Toast.makeText(RegForm.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
