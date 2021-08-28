package com.org.profburo.others;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.org.profburo.R;
import com.org.profburo.network.responsesEntities.participiants.ParticipiantsResponse;

public class ParticipiantDialog extends Dialog {

    private ParticipiantsResponse participiant;
    private TextView userName;
    private TextView userBirthday;
    private TextView userPassportSeries;
    private TextView userPassportNumber;
    private TextView userHeadquarter;
    private TextView userEmail;
    private TextView userPhone;

    public ParticipiantDialog(@NonNull Context context, ParticipiantsResponse participiant) {
        super(context);
        this.participiant = participiant;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participiant_description_fragment);

        userName = (TextView) findViewById(R.id.user_name);
        userBirthday = (TextView) findViewById(R.id.user_birthday);
        userPassportSeries = (TextView) findViewById(R.id.user_passport_series);
        userPassportNumber = (TextView) findViewById(R.id.user_passport_number);
        userHeadquarter = (TextView) findViewById(R.id.user_headquarter);
        userEmail = (TextView) findViewById(R.id.user_email);
        userPhone = (TextView) findViewById(R.id.user_phone);

        writeData();
    }

    private void writeData()
    {
        userName.setText(String.format("%s %s %s", participiant.getLastName(), participiant.getFirstName(), participiant.getMiddlename()));
        userBirthday.setText(participiant.getBirthday());
        userPassportSeries.setText(participiant.getSerialPassport());
        userPassportNumber.setText(participiant.getNumberPassport());
        userHeadquarter.setText(participiant.getHeadquarters());
        userEmail.setText(participiant.getEmail());
        userPhone.setText(participiant.getPhone());
    }


}
