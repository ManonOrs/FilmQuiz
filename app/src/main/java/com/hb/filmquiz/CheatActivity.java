package com.hb.filmquiz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hb.filmquiz.pojos.Question;

public class CheatActivity extends AppCompatActivity {

    private TextView tvReponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        tvReponse = findViewById(R.id.tvReponse);

        Intent intent = getIntent();
        Question question = (Question)intent.getSerializableExtra(MainActivity.KEY_QUESTION);

        tvReponse.setText(String.format("%s : %s", question.getText(), question.isAnswer()));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}