package com.org.profburo.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.profburo.R;
import com.org.profburo.activities.LoginForm;
import com.org.profburo.activities.MainPage;
import com.org.profburo.activities.TestForm;

import java.util.List;

public class TestDescriptionAdapter extends RecyclerView.Adapter<TestDescriptionAdapter.ViewHolder> {

    private List<String> localDataSet;

    public TestDescriptionAdapter(List<String> localDataSet) {
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
        holder.getTextView().setText(localDataSet.get(position));
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
        private final TextView textView;
        private final Button button;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.testName);
            button = (Button) view.findViewById(R.id.testStart);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toTest = new Intent(view.getContext(), TestForm.class);
                    view.getContext().startActivity(toTest);
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
