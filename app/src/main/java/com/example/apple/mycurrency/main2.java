package com.example.apple.mycurrency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;

public class main2 extends AppCompatActivity {
    private EditText input;

    //public static final int sub = 1001; /*다른 액티비티를 띄우기 위한 요청코드(상수)*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        input = findViewById(R.id.input);
    }



  /*  public void convert(View view){
        DecimalFormat df = new DecimalFormat("#.##");
        double input_value = Double.parseDouble(inputTWD.getText().toString());
        textView_USD.setText(df.format(input_value/currency_USD));
    }
*/
    public void clickGo(View view){
        String input_string = input.getText().toString();
        Intent intent_result = new Intent();
        intent_result.setClass(main2.this, result.class);
        intent_result.putExtra("input", input_string);
        startActivity(intent_result);
    }


}
