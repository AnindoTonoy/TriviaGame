package com.example.triviagame;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements mAdapter.OnItemListener {

    TextView textView;
    RecyclerView recyclerView;
    Button button;
    EditText editText;

    Timer timer;

    String[] s1;
    String[] s2;
    String[] country = new String[5];
    String[] capital = new String[5];
    int click_count = 0;
    int score = 0;
    String[] round = new String[] {"Round - 1", "Round - 2", "Round - 3"};
    int r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.round);
        recyclerView = findViewById(R.id.recycler_view);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        Intent intent = getIntent();
        if (intent != null) {
            score = intent.getIntExtra("score", score);
            r = intent.getIntExtra("r", 0);
            String name = round[r];
            textView.setText(name);
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                r++;
                Intent intent = new Intent(MainActivity.this, TimesUpActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("r", r);
                startActivity(intent);
                finish();
            }
        },30000);

        s1 = getResources().getStringArray(R.array.name_of_countries);
        s2 = getResources().getStringArray(R.array.name_of_capitals);

        getRandom(s1, s2);

        mAdapter mAdapter = new mAdapter(this, country, capital, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void check_answer(View view) {
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }

        String ans = String.valueOf(editText.getText());
        String correct_answer = capital[click_count];
        boolean correct = ans.matches(correct_answer);

        if (correct){
            Toast toast = Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
            editText.getText().clear();
            score++;
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        }

        editText.setVisibility(View.INVISIBLE);
        click_count++;
    }

    private void getRandom(String[] country, String[] capital) {
        for(int i=0; i< 5; i++){
            int a = new Random().nextInt(country.length);
            this.country[i] = country[a];
            this.capital[i] = capital[a];
        }
    }

    public void onItemClick(int position) {
        Toast toast = Toast.makeText(getApplicationContext(),"Selected",Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
        editText.setVisibility(View.VISIBLE);
    }

}
