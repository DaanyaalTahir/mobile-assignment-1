package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Result class which contains the result from the computation
 * @author Daanyaal Tahir (100746644)
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button backBtn = findViewById(R.id.backBtn);

        // Add on click listener to back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });

        // Retrieve the result from the intent
        double monthlyPayment = getIntent().getDoubleExtra("monthlyPayment", 0.0);

        // Display the result
        ((TextView)findViewById(R.id.resultText)).setText("$" + String.format("%.2f", monthlyPayment) + " /Monthly");


        // Setup the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Add back button
        getSupportActionBar().setTitle("Result");

    }

    // Override onOptionsItemSelected to return home when back button is selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Call intent to return home
                returnHome();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Function to return to home page using Intents
     */
    private void returnHome(){
        startActivity(new Intent(ResultActivity.this, MainActivity.class));
    }
}