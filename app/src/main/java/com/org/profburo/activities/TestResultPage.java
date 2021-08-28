package com.org.profburo.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.org.profburo.R;
import com.org.profburo.network.RestApi;
import com.org.profburo.network.responsesEntities.test.TestResults;
import com.org.profburo.others.CompTestResults;
import com.org.profburo.others.SpecialNameCodesAndProfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestResultPage extends AppCompatActivity {
    private List<TestResults> results;
    private Integer resultId;


    private RadarChart radarChart;
    private ImageView toMainPage;
    private TextView testResultsDescription;
    private TextView testName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testresult_page);

        radarChart = (RadarChart) findViewById(R.id.radar_chart);
        toMainPage = (ImageView) findViewById(R.id.toMainPage);
        testResultsDescription = (TextView) findViewById(R.id.test_results_description);
        testName = (TextView) findViewById(R.id.test_name);

        toMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainPage = new Intent(getBaseContext(), MainPage.class);
                startActivity(toMainPage);
                finishAfterTransition();
            }
        });

        Bundle arguments = getIntent().getExtras();
        resultId = arguments.getInt("resultId");
        testName.setText(arguments.getCharSequence("testName"));

        arguments.clear();

        initdata();
    }

    private void initdata()
    {
        RestApi.getInstance()
                .getApi()
                .getTestsResults(resultId)
                .enqueue(new Callback<List<TestResults>>() {
                    @Override
                    public void onResponse(Call<List<TestResults>> call, Response<List<TestResults>> response) {
                        if (response.isSuccessful())
                        {
                            results = new ArrayList<>(response.body());
                            drawChart();
                            showResultsWords();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TestResults>> call, Throwable t) {

                    }
                });
    }

    private void drawChart()
    {
        ArrayList<RadarEntry> radarEntry = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < results.size(); i++)
        {
            radarEntry.add(new RadarEntry(results.get(i).getBalls().floatValue(), i));
            labels.add(results.get(i).getSpecialName());
        }

        RadarDataSet radarDataSet = new RadarDataSet(radarEntry, "Результат");

        setChartParametrs(radarDataSet, labels);

        RadarData radarData = new RadarData(radarDataSet);
        radarData.setLabels(labels);

        radarChart.setData(radarData);
        radarChart.notifyDataSetChanged();
        radarChart.invalidate();

    }

    private void setChartParametrs(RadarDataSet radarDataSet, ArrayList<String> labels)
    {
        radarDataSet.setValueTextColor(Color.BLACK);
        radarDataSet.setValueTextSize(14f);
        radarDataSet.setDrawValues(false);
        radarDataSet.setColor(Color.rgb(29, 162, 232));
        radarDataSet.setFillColor(Color.rgb(29, 162, 232));
        radarDataSet.setDrawFilled(true);
        radarDataSet.setFillAlpha(180);
        radarDataSet.setLineWidth(2f);
        radarDataSet.setDrawHighlightCircleEnabled(true);
        radarDataSet.setDrawHighlightIndicators(false);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setYOffset(0);
        xAxis.setXOffset(0);
        xAxis.setValueFormatter(new IndexAxisValueFormatter()
        {
            @Override
            public String getFormattedValue(float value) {
                return labels.get((int) value % labels.size());
            }
        });

        xAxis.setTextColor(Color.BLACK);

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setLabelCount(labels.size());
        yAxis.setTextSize(12f);
        yAxis.setAxisMinimum(0);

        TestResults maxValue = Collections.max(results, new CompTestResults());

        yAxis.setAxisMaximum(maxValue.getBalls());
        yAxis.setDrawLabels(false);

        Legend l = radarChart.getLegend();
        l.setTextSize(15f);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLACK);

        radarChart.animateXY(1500, 1500, Easing.EaseInOutQuad, Easing.EaseInOutQuad);
        radarChart.getDescription().setEnabled(false);
        radarChart.getLegend().setEnabled(false);
        radarChart.setWebLineWidth(1f);
        radarChart.setWebColor(Color.BLACK);
        radarChart.setWebColorInner(Color.BLACK);
        radarChart.setWebAlpha(100);
    }

    private void showResultsWords()
    {
        TestResults maxValue = Collections.max(results, new CompTestResults());
        String text = "У вас ярковыраженный навык - "+ maxValue.getSpecialName() + ".\n\n"
                + "Наиболее подходящими для вас являются следующие профессии:\n";
        for (int i = 0; i != 3; i++)
        {
            switch (maxValue.getSpecialName())
            {
                case SpecialNameCodesAndProfs.CULTURE:
                    text += "- " + SpecialNameCodesAndProfs.CULTURE_PROFS[i] + '\n';
                    break;

                case SpecialNameCodesAndProfs.INTELLIGENCE:
                    text += "- " + SpecialNameCodesAndProfs.INTELLIGENCE_PROFS[i] + '\n';
                    break;

                case SpecialNameCodesAndProfs.LIDERSHIP:
                    text += "- " + SpecialNameCodesAndProfs.LIDERSHIP_PROFS[i] + '\n';
                    break;

                case SpecialNameCodesAndProfs.SOCIAL:
                    text += "- " + SpecialNameCodesAndProfs.SOCIAL_PROFS[i] + '\n';
                    break;
            }
        }

        text = text.substring(0, text.length()-1);
        testResultsDescription.append(text);
    }
}
