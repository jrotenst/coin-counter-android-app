package com.jrutz.coincounterjr;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.PersistableBundle;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Model object
    private CoinCounter mCoinCounter;

    // Status Bar and View references
    private EditText editTextPenny, editTextNickel, editTextDime, editTextQuarter;
    private TextView textViewStatusBar;

    // key to save and restore state


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupFields();
        setUpFAB();
    }

    private void setupFields() {
        mCoinCounter = new CoinCounter();
        editTextPenny = findViewById(R.id.penny_cv_et);
        editTextNickel = findViewById(R.id.nickel_cv_et);
        editTextDime = findViewById(R.id.dime_cv_et);
        editTextQuarter = findViewById(R.id.quarter_cv_et);
        textViewStatusBar = findViewById(R.id.status_bar_tv);
    }

    private void setUpFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewStatusBar.setText(calculateCents());
            }
        });
    }

    private String calculateCents() {
        mCoinCounter.setCountOfPennies(editTextPenny.getText().toString());
        mCoinCounter.setCountOfNickels(editTextNickel.getText().toString());
        mCoinCounter.setCountOfDimes(editTextDime.getText().toString());
        mCoinCounter.setCountOfQuarters(editTextQuarter.getText().toString());
        return getString(R.string.status_bar_text) + " " + mCoinCounter.getCentsValueTotal();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_clear) {
            clearAll();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            Utils.showInfoDialog (MainActivity.this,
                    R.string.about, R.string.about_text);
        }

        return super.onOptionsItemSelected(item);
    }

    private void clearAll() {
        editTextPenny.setText("");
        editTextNickel.setText("");
        editTextDime.setText("");
        editTextQuarter.setText("");
        textViewStatusBar.setText(getString(R.string.name));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("CC", mCoinCounter.getJSONStringFromThis());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCoinCounter = CoinCounter.getCoinCounterObjectFromJSONString
                (savedInstanceState.getString("CC"));
        updateUI();
    }

    private void updateUI() {
        editTextPenny.setText(mCoinCounter.getCountOfPennies());
        editTextNickel.setText(mCoinCounter.getCountOfNickels());
        editTextDime.setText(mCoinCounter.getCountOfDimes());
        editTextQuarter.setText(mCoinCounter.getCountOfQuarters());
        textViewStatusBar.setText(calculateCents());
    }
}