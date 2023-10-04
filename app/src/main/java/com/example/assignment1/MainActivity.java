package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Main class which contains the homepage of the app
 * @author Daanyaal Tahir (100746644)
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateBtn = findViewById(R.id.calculateBtn); // Get reference to calculate button

        // Calculate button listener
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                // Get the input values
                String principleAmount = ((EditText)findViewById(R.id.principle_input)).getText().toString();
                String interestRate = ((EditText)findViewById(R.id.interest_rate_input)).getText().toString();
                String amortization = ((EditText)findViewById(R.id.amortization_period_input)).getText().toString();

                // Check to see if any input is empty
                if (principleAmount.isEmpty() || interestRate.isEmpty() || amortization.isEmpty()) {
                    // Display a Toast message for invalid input
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else{
                    // Retrieve monthly payment
                    double monthlyPayment = getMonthlyPayment(Float.parseFloat(principleAmount), Float.parseFloat(interestRate), Integer.parseInt(amortization));

                    // Navigate to the other activity and pass in the monthly payment data
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("monthlyPayment", monthlyPayment);
                    startActivity(intent);

                }
            }
        });

        // Setup the toolbar and its title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mortgage Payment Calculator");

    }

    /**
     * Function to calculate the monthly EMI payment
     *
     * @param principleAmount - initial amount borrowed
     * @param interestRate - annual interest rate
     * @param amortization - loan tenure
     * @return monthly payment amount as a double
     */
    private double getMonthlyPayment(float principleAmount, float interestRate, int amortization){
        // Get monthly interest rate as a double
        double monthlyInterestRate = interestRate / 12 / 100;

        // Get total monthly payments
        int totalPayments = amortization * 12;

        // Calculate monthly payment
        double monthlyPayment = principleAmount * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalPayments))
                / (Math.pow(1 + monthlyInterestRate, totalPayments) - 1);

        return monthlyPayment;
    }
}