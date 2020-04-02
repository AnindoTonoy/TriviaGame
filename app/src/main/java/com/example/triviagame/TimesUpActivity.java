package com.example.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TimesUpActivity extends AppCompatActivity {

    TextView textView;

    int r;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_up);

        textView = findViewById(R.id.score_text);
        Intent intent = getIntent();
        if (intent != null) {
            score = intent.getIntExtra("score", 0);
            r = intent.getIntExtra("r", 0);
            String name = String.format("Score: %s", score);
            textView.setText(name);
        }
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
        if(r<3){
            Intent intent = new Intent(TimesUpActivity.this, MainActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("r", r);
            startActivity(intent);
            finish();
        }
        else
            finish();

    }
}
