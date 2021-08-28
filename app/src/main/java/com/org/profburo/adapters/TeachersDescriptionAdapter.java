package com.org.profburo.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.profburo.R;
import com.org.profburo.activities.TestForm;
import com.org.profburo.activities.TestResultPage;
import com.org.profburo.network.responsesEntities.teachers.TeacherResponse;
import com.org.profburo.network.responsesEntities.test.TestsResponse;

import java.util.List;

public class TeachersDescriptionAdapter extends RecyclerView.Adapter<TeachersDescriptionAdapter.ViewHolder> {

    private List<TeacherResponse> localDataSet;

    public TeachersDescriptionAdapter(List<TeacherResponse> localDataSet) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_recycle_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTeacherName().setText(String.format("%s %s %s", localDataSet.get(position).getLastName(), localDataSet.get(position).getFirstName(), localDataSet.get(position).getMiddleName()));
        holder.setActive(localDataSet.get(position).getActive());
        holder.getTeacherAddressAndSchool().setText(String.format("%s, %s", localDataSet.get(position).getSchoolName(), localDataSet.get(position).getHeadquarterName()));
        holder.getTeacherContacts().setText(String.format("%s, %s", localDataSet.get(position).getPhone(), localDataSet.get(position).getEmail()));
        holder.setUserId(localDataSet.get(position).getUserId());

        if (localDataSet.get(position).getActive() == false)
        {
            holder.getButton().setColorFilter(Color.RED);
        }
        else
        {
            holder.getButton().setColorFilter(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView teacherName;
        private final TextView teacherAddressAndSchool;
        private final TextView teacherContacts;
        private final ImageButton button;
        private Boolean isActive;
        private Integer userId;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            teacherName = (TextView) view.findViewById(R.id.teacher_name);
            teacherAddressAndSchool = (TextView) view.findViewById(R.id.teacher_address_and_school);
            teacherContacts = (TextView) view.findViewById(R.id.teacher_contacts);
            button = (ImageButton) view.findViewById(R.id.teacher_status);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isActive == true)
                    {
                        button.setColorFilter(Color.RED);
                        isActive = false;
                    }
                    else
                    {
                        button.setColorFilter(Color.GREEN);
                        isActive = true;
                    }
                }
            });
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public TextView getTeacherName() {
            return teacherName;
        }

        public TextView getTeacherAddressAndSchool() {
            return teacherAddressAndSchool;
        }

        public TextView getTeacherContacts() {
            return teacherContacts;
        }

        public ImageButton getButton() {
            return button;
        }

        public Boolean getActive() {
            return isActive;
        }

        public void setActive(Boolean active) {
            isActive = active;
        }
    }
}
