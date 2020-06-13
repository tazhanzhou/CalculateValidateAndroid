package com.example.a06as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner spinner;
    Button btnG, btnV;
    TextView textView;
    EditText editText;
    int rightResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        textView = findViewById(R.id.textViewQuestion);
        editText = findViewById(R.id.editTextAnswer);

        btnG = findViewById(R.id.btnG);
        btnV = findViewById(R.id.btnV);

        btnV.setOnClickListener(this);
        btnG.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnG:
                goGenerate();
                break;
            case R.id.btnV:
                goValidate();
                break;
        }
    }

    private void goGenerate() {
        Random random = new Random();
        int operand1 = random.nextInt(10);
        int operand2 = random.nextInt(10);
        String operation = spinner.getSelectedItem().toString();
        String showOperation = null;
        switch (operation) {
            case "Add":
                rightResult = operand1 + operand2;
                showOperation = String.valueOf(operand1) + "+" + String.valueOf(operand2) + "=?";
                break;
            case "Sub":
                rightResult = operand1 - operand2;
                showOperation = String.valueOf(operand1) + "-" + String.valueOf(operand2) + "=?";
                break;
            case "Mul":
                rightResult = operand1 * operand2;
                showOperation = String.valueOf(operand1) + "*" + String.valueOf(operand2) + "=?";
                break;
            case "Dev":
                if (operand2 == 0) {
                    operand2++;
                }
                rightResult = operand1 / operand2;
                showOperation = String.valueOf(operand1) + "/" + String.valueOf(operand2) + "=?";
                break;
        }
        textView.setText(showOperation);
    }

    private void goValidate() {
        if (editText.getText().length() == 0) {
            Toast.makeText(this, "Enter an Answer first!", Toast.LENGTH_SHORT).show();
        } else {
            int integerUserAnswer = Integer.valueOf(editText.getText().toString());
            String strResult;
            if (integerUserAnswer == rightResult) {
                strResult = "Right Answer!";
            } else {
                strResult = "Wrong Answer!";
            }
            Toast.makeText(this, strResult, Toast.LENGTH_SHORT).show();
            editText.setText("");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}