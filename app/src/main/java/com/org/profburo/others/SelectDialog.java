package com.org.profburo.others;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.org.profburo.R;
import com.org.profburo.activities.RegForm;

import static com.org.profburo.others.UtilitaryVariables.authorisedUser;

public class SelectDialog extends Dialog implements View.OnClickListener {
    private Button studentButton;
    private Button matureButton;
    private Button teacherButton;

    public SelectDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usertypes_buttons_fragment);

        studentButton = (Button) findViewById(R.id.student);
        matureButton = (Button) findViewById(R.id.mature);
        teacherButton = (Button) findViewById(R.id.teacher);

        studentButton.setOnClickListener(this);
        matureButton.setOnClickListener(this);
        teacherButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.student:
                authorisedUser.setPermission(PermissionCode.STUDENT);
                break;
            case R.id.mature:
                authorisedUser.setPermission(PermissionCode.MATURE);
                break;
            case R.id.teacher:
                authorisedUser.setPermission(PermissionCode.TEACHER);
                break;
        }
        Intent toRegPage = new Intent(getContext(), RegForm.class);
        getContext().startActivity(toRegPage);
    }
}
