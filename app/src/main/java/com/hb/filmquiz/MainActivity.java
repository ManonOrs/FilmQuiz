package com.hb.filmquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hb.filmquiz.pojos.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private TextView tvScore;
    private Button btnTrue;
    private Button btnFalse;
    private Button btnRestart;
    private List<Question> questions = new ArrayList<Question>();
    private int indexQuestion = 0;
    private int score = 0;
    private final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_SCORE = "score";
    public static final String KEY_QUESTION = "question";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate() called");
        // on récupère les éléments du layout
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        btnRestart = findViewById(R.id.btnRestart);

        // ajoute les questions a la liste
        questions.add(new Question(getString(R.string.question_ai), true));
        questions.add(new Question(getString(R.string.question_taxi_driver), true));
        questions.add(new Question(getString(R.string.question_2001), false));
        questions.add(new Question(getString(R.string.question_reservoir_dogs), true));
        questions.add(new Question(getString(R.string.question_citizen_kane), false));

        if (savedInstanceState != null) {
            indexQuestion = savedInstanceState.getInt(KEY_INDEX);
            score = savedInstanceState.getInt(KEY_SCORE);
        }

        tvQuestion.setText(questions.get(indexQuestion).getText());
        tvScore.setText(getString(R.string.score).concat(": " + score));

        btnRestart.setVisibility(View.GONE);

        if (indexQuestion == questions.size() - 1) {
            btnRestart.setVisibility(View.VISIBLE);
            btnFalse.setVisibility(View.GONE);
            btnTrue.setVisibility(View.GONE);
            tvQuestion.setVisibility(View.GONE);
        }

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions.get(indexQuestion).isAnswer()) {
                    score = score + 1;
                    Toast toast = Toast.makeText(getApplicationContext(), "VRAI", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "FAUX", Toast.LENGTH_LONG);
                    toast.show();
                }
                indexQuestion = indexQuestion + 1;
                tvScore.setText(getString(R.string.score).concat(": " + score));
                if (indexQuestion < questions.size()) {
                    tvQuestion.setText(questions.get(indexQuestion).getText());
                } else {
                    btnRestart.setVisibility(View.VISIBLE);
                    btnFalse.setVisibility(View.GONE);
                    btnTrue.setVisibility(View.GONE);
                    tvQuestion.setVisibility(View.GONE);
                }
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions.get(indexQuestion).isAnswer()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "FAUX", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    score = score + 1;
                    Toast toast = Toast.makeText(getApplicationContext(), "VRAI", Toast.LENGTH_LONG);
                    toast.show();
                }
                indexQuestion = indexQuestion + 1;
                tvScore.setText(getString(R.string.score).concat(": " + score));

                if (indexQuestion < questions.size()) {
                    tvQuestion.setText(questions.get(indexQuestion).getText());

                } else {
                    btnRestart.setVisibility(View.VISIBLE);
                    btnFalse.setVisibility(View.GONE);
                    btnTrue.setVisibility(View.GONE);
                    tvQuestion.setVisibility(View.GONE);
                }
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = 0;
                indexQuestion = 0;
                tvQuestion.setText(questions.get(indexQuestion).getText());
                tvScore.setText(getString(R.string.score).concat(": " + score));
                btnRestart.setVisibility(View.GONE);
                btnFalse.setVisibility(View.VISIBLE);
                btnTrue.setVisibility(View.VISIBLE);
                tvQuestion.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");

        if (btnRestart.getVisibility() == View.VISIBLE) {
            outState.putInt(KEY_INDEX, questions.size() - 1);
        } else {
            outState.putInt(KEY_INDEX, indexQuestion);

        }
        outState.putInt(KEY_SCORE, score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cheat:

                Intent intent = new Intent(getApplicationContext(), CheatActivity.class);
                intent.putExtra(KEY_QUESTION, questions.get(indexQuestion));
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}