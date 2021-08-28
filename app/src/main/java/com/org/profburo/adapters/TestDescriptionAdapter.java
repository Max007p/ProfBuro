package com.org.profburo.adapters;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.org.profburo.R;
import com.org.profburo.activities.LoginForm;
import com.org.profburo.activities.MainPage;
import com.org.profburo.activities.TestForm;
import com.org.profburo.activities.TestResultPage;
import com.org.profburo.network.responsesEntities.test.TestsResponse;

import java.util.List;

public class TestDescriptionAdapter extends RecyclerView.Adapter<TestDescriptionAdapter.ViewHolder> {

    private List<TestsResponse> localDataSet;

    public TestDescriptionAdapter(List<TestsResponse> localDataSet) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_recycle_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTestName().setText(localDataSet.get(position).getTitleTest());
        holder.getTestDescription().setText(localDataSet.get(position).getDescriptionTest());
        holder.setId(localDataSet.get(position).getIdTest());
        holder.setResultId(localDataSet.get(position).getResultId());

        if (localDataSet.get(position).getResultId() != null)
        {
            holder.getButton().setText("Результаты");
            holder.getAgreeSign().setColorFilter(Color.GREEN);
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
        private final TextView testName;
        private final TextView testDescription;
        private final ImageView agreeSign;
        private Integer id;
        private Integer resultId;
        private final Button button;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            testName = view.findViewById(R.id.testName);
            testDescription = view.findViewById(R.id.testDesc);
            button = view.findViewById(R.id.testStart);
            agreeSign = view.findViewById(R.id.agree_sign);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (resultId == null)
                    {
                        Intent toTestPage = new Intent(v.getContext(), TestForm.class);
                        toTestPage.putExtra("testId", id);
                        toTestPage.putExtra("testName", testName.getText());
                        v.getContext().startActivity(toTestPage);
                    }
                    else
                    {
                        Intent toResultTestPage = new Intent(v.getContext(), TestResultPage.class);
                        toResultTestPage.putExtra("resultId", resultId);
                        toResultTestPage.putExtra("testName", testName.getText());
                        v.getContext().startActivity(toResultTestPage);
                    }
                }
            });
        }

        public ImageView getAgreeSign() {
            return agreeSign;
        }

        public Button getButton() {
            return button;
        }

        public Integer getResultId() {
            return resultId;
        }

        public void setResultId(Integer resultId) {
            this.resultId = resultId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public TextView getTestName()
        {
            return testName;
        }

        public TextView getTestDescription()
        {
            return testDescription;
        }
    }
}
