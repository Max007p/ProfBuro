package com.org.profburo.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.profburo.R;
import com.org.profburo.activities.LoginForm;
import com.org.profburo.network.responsesEntities.participiants.ParticipiantsResponse;
import com.org.profburo.network.responsesEntities.teachers.TeacherResponse;
import com.org.profburo.others.ParticipiantDialog;
import com.org.profburo.others.SelectDialog;

import java.util.List;

public class ParticipiantsAdapter extends RecyclerView.Adapter<ParticipiantsAdapter.ViewHolder> {

    private List<ParticipiantsResponse> localDataSet;

    public ParticipiantsAdapter(List<ParticipiantsResponse> localDataSet) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public ParticipiantsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.participiant_recycle_item, parent, false);

        return new ParticipiantsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipiantsAdapter.ViewHolder holder, int position) {
        holder.getParticipiantName().setText(String.format("%s %s %s", localDataSet.get(position).getLastName(), localDataSet.get(position).getFirstName(), localDataSet.get(position).getMiddlename()));
        holder.setParticipiant(localDataSet.get(position));
        holder.getParticipiantAddressAndSchool().setText(String.format("%s, %s", localDataSet.get(position).getRegion(), localDataSet.get(position).getHeadquarters()));
        holder.getParticipiantContacts().setText(String.format("%s, %s", localDataSet.get(position).getPhone(), localDataSet.get(position).getEmail()));
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
        private final TextView participiantName;
        private final TextView participiantAddressAndSchool;
        private final TextView participiantContacts;
        private ParticipiantsResponse participiant;
        private ParticipiantDialog dialog;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            participiantName = (TextView) view.findViewById(R.id.participiant_name);
            participiantAddressAndSchool = (TextView) view.findViewById(R.id.participiants_address_and_school);
            participiantContacts = (TextView) view.findViewById(R.id.participiant_contacts);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new ParticipiantDialog(v.getContext(), participiant);
                    dialog.show();
                }
            });
        }

        public TextView getParticipiantName() {
            return participiantName;
        }

        public TextView getParticipiantAddressAndSchool() {
            return participiantAddressAndSchool;
        }

        public TextView getParticipiantContacts() {
            return participiantContacts;
        }

        public ParticipiantsResponse getParticipiant() {
            return participiant;
        }

        public void setParticipiant(ParticipiantsResponse participiant) {
            this.participiant = participiant;
        }
    }
}
