package com.org.profburo.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.org.profburo.R;
import com.org.profburo.entities.UserAnswers;
import com.org.profburo.network.RestApi;
import com.org.profburo.network.responsesEntities.test.AnswerQuestionBind;
import com.org.profburo.network.responsesEntities.test.TestInsertResponse;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.org.profburo.others.UtilitaryVariables.authorisedUser;

public class TestForm extends AppCompatActivity {

    private TextView testQuestion;
    private LinearLayout answersSpace;
    private ProgressBar progressBar;
    private TextView currentQuestionCount;
    private TextView maxQuestionCount;
    private TextView testName;
    private ImageView toMainPage;

    private Integer testId;
    private List<AnswerQuestionBind> testBody = new ArrayList<>();
    private UserAnswers testAnswers = new UserAnswers();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_page);

        toMainPage = (ImageView) findViewById(R.id.to_main_page);
        testName = (TextView) findViewById(R.id.test_name);
        testQuestion = (TextView) findViewById(R.id.test_question);
        answersSpace = (LinearLayout) findViewById(R.id.answers_space);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        currentQuestionCount = (TextView) findViewById(R.id.current_question_count);
        maxQuestionCount = (TextView) findViewById(R.id.max_question_count);

        toMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainPage = new Intent(TestForm.this, MainPage.class);
                startActivity(toMainPage);
                finishAfterTransition();
            }
        });

        Bundle arguments = getIntent().getExtras();
        testId = arguments.getInt("testId");
        testName.setText(arguments.getCharSequence("testName"));
        arguments.clear();

        initTestData(testId);

    }

    private void testProcess()
    {
        testAnswers.setQuizsetId(testId);
        testAnswers.setUserId(authorisedUser.getId());
        currentQuestionCount.setText(String.valueOf(1));
        maxQuestionCount.setText(String.valueOf(testBody.size()+1));
        progressBar.setMax(testBody.size());
        drawQuiz(0);
    }

    private void drawQuiz(int testBodyIterator)
    {
        LinearLayout.LayoutParams buttonsParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams spaceParams
                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                30, 0);

        Typeface fontType = ResourcesCompat.getFont(getBaseContext(), R.font.notosans_bold);
        Drawable img = ResourcesCompat.getDrawable(getResources(), R.drawable.test_buttons_selector, getTheme());
            testQuestion.setText(testBody.get(testBodyIterator).getQuestion().getText());

        for (int j = 0; j < testBody.get(testBodyIterator).getAnswers().size(); j++)
        {
            Space space = new Space(getBaseContext());
            space.setLayoutParams(spaceParams);

            Button answer = new Button(getBaseContext());
            answer.setLayoutParams(buttonsParams);
            answer.setBackground(img);
            answer.setTypeface(fontType);
            answersSpace.addView(answer);
            answersSpace.addView(space);
            answer.setText(testBody.get(testBodyIterator).getAnswers().get(j).getText());

            int finalI = testBodyIterator;
            int finalJ = j;

            answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    testAnswers.addAnswer(testBody.get(finalI).getQuestion().getId(), testBody.get(finalI).getAnswers().get(finalJ).getId());
                    answersSpace.removeAllViews();
                    progressBar.incrementProgressBy(1);
                    currentQuestionCount.setText(String.valueOf(testBodyIterator+2));

                    if (testBodyIterator < testBody.size()-1)
                    {
                        drawQuiz(testBodyIterator+1);
                    }
                    else
                    {
                        sendResults();
                        Intent toLogPage = new Intent(TestForm.this, MainPage.class);
                        startActivity(toLogPage);
                        finishAfterTransition();
                    }
                }
            });
        }

        Button answer = new Button(getBaseContext());
        answer.setLayoutParams(buttonsParams);
        answer.setBackground(img);
        answer.setTypeface(fontType);
        answersSpace.addView(answer);
        answersSpace.removeView(answer);

    }

    private void initTestData(int testId){
        RestApi.getInstance()
        .getApi()
        .getTestBody(testId)
        .enqueue(new Callback<List<AnswerQuestionBind>>() {
            @Override
            public void onResponse(Call<List<AnswerQuestionBind>> call, Response<List<AnswerQuestionBind>> response) {
                if (response.isSuccessful())
                {
                    testBody = new ArrayList<>(response.body());
                    testProcess();
                }
            }

            @Override
            public void onFailure(Call<List<AnswerQuestionBind>> call, Throwable t) {

            }
        });
    }

    private void sendResults()
    {
        RestApi.getInstance()
                .getApi()
                .setTestsResults(testAnswers)
                .enqueue(new Callback<TestInsertResponse>() {
                    @Override
                    public void onResponse(Call<TestInsertResponse> call, Response<TestInsertResponse> response) {
                        if (response.isSuccessful())
                        {
                            if (response.body().getAreResultsInserted())
                            {
                                Toast.makeText(getBaseContext(), "Спасибо за прохождение теста", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TestInsertResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent toLogPage = new Intent(TestForm.this, MainPage.class);
        startActivity(toLogPage);
        finishAfterTransition();
    }
}
