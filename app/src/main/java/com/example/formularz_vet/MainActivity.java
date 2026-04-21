package com.example.formularz_vet;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText ownerNameInput;
    private Spinner speciesSpinner;
    private SeekBar ageSeekBar;
    private TextView ageValue;
    private EditText visitPurposeInput;
    private EditText timeInput;
    private Button okButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ownerNameInput = findViewById(R.id.ownerNameInput);
        speciesSpinner = findViewById(R.id.speciesSpinner);
        ageSeekBar = findViewById(R.id.ageSeekBar);
        ageValue = findViewById(R.id.ageValue);
        visitPurposeInput = findViewById(R.id.visitPurposeInput);
        okButton = findViewById(R.id.okButton);
        resultText = findViewById(R.id.resultText);
        timeInput = findViewById(R.id.timeInput);

        String[] species = {"Pies", "Kot", "Swinka Morska"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, species);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciesSpinner.setAdapter(adapter);

        speciesSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                if (position == 0) ageSeekBar.setMax(18);
                if (position == 1) ageSeekBar.setMax(20);
                if (position == 2) ageSeekBar.setMax(9);

                ageSeekBar.setProgress(0);
                ageValue.setText("0");
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageValue.setText(String.valueOf(progress));
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        okButton.setOnClickListener(v -> {
            String owner = ownerNameInput.getText().toString();
            String speciesSelected = speciesSpinner.getSelectedItem().toString();
            String age = ageValue.getText().toString();
            String purpose = visitPurposeInput.getText().toString();
            String time = timeInput.getText().toString();

            String result = owner + ", " + speciesSelected + ", " + age + ", " + purpose + ", " + time;

            resultText.setText(result);
        });
    };
}