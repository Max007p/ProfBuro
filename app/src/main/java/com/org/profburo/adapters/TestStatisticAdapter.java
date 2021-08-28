package com.org.profburo.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.profburo.R;
import com.org.profburo.activities.TestForm;
import com.org.profburo.activities.TestResultPage;
import com.org.profburo.network.responsesEntities.test.TestStatistic;
import com.org.profburo.network.responsesEntities.test.TestsResponse;

import java.util.List;

public class TestStatisticAdapter extends RecyclerView.Adapter<TestStatisticAdapter.ViewHolder>{
    private List<TestStatistic> localDataSet;

    public TestStatisticAdapter(List<TestStatistic> localDataSet) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_statistic_recycle_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getRegionName().setText(localDataSet.get(position).getRegionName().substring(0, localDataSet.get(position).getRegionName().indexOf(" ")));
        holder.getRegionCount().setText(String.format("%s", localDataSet.get(position).getQuantity()));
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
        private final TextView regionName;
        private final TextView regionCount;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            regionName = view.findViewById(R.id.region_name);
            regionCount = view.findViewById(R.id.quantity_of_tests);

        }

        public TextView getRegionName() {
            return regionName;
        }

        public TextView getRegionCount() {
            return regionCount;
        }
    }
}
