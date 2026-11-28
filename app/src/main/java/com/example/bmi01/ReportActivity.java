package com.example.bmi01;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class ReportActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Bundle bundle = getIntent().getExtras();
        double height = Double.parseDouble(bundle.getString("height")) / 100;  // cm → m
        double weight = Double.parseDouble(bundle.getString("weight"));
        double bmi = weight / (height * height);

        DecimalFormat nf = new DecimalFormat("0.00");
        ((TextView) findViewById(R.id.report_result))
                .setText(getString(R.string.bmi_result) + nf.format(bmi));

        ImageView image  = findViewById(R.id.report_image);
        TextView  advice = findViewById(R.id.report_advice);
        if (bmi > 25) {
            image.setImageResource(R.drawable.bot_fat);
            advice.setText(R.string.advice_heavy);
        } else if (bmi < 20) {
            image.setImageResource(R.drawable.bot_thin);
            advice.setText(R.string.advice_light);
        } else {
            image.setImageResource(R.drawable.bot_fit);
            advice.setText(R.string.advice_average);
        }
    }
}
