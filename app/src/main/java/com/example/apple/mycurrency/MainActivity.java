package com.example.apple.mycurrency;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button search;
    //private TextView main2;
    private EditText input;
    private TextView textView_USD;
    private TextView textView_KRW;
    private TextView textView_JPY;
    private TextView textView_GBP;
    private TextView textView_HKD;

    private Double currency_USD;
    private Double currency_HKD;
    private Double currency_JPY;
    private Double currency_KRW;
    private Double currency_GBP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView ryan = (ImageView) findViewById(R.id.ryan);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(ryan);
        Glide.with(this).load(R.drawable.ryan).into(gifImage);

        Button imageButton = (Button) findViewById(R.id.search);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), searchcurrency.class);
                startActivity(intent);
            }
        });

        input = findViewById(R.id.input);
        textView_USD = findViewById(R.id.textview_USD);
        textView_HKD = findViewById(R.id.textview_HKD);
        textView_JPY = findViewById(R.id.textview_JPY);
        textView_KRW = findViewById(R.id.textview_KRW);
        textView_GBP = findViewById(R.id.textview_GBP);
        getWebsite();


    }


    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    //台灣銀行牌告匯率
                    Document doc = Jsoup.connect("https://rate.bot.com.tw/xrt?Lang=zh-TW").get();
                    String title = doc.title();
                    Elements tds = doc.select("td");
                    String currency_country = null;
                    String currency_rate_buy = null;
                    String currency_rate_sell = null;
                    String[] currency_country_token = null;

                    //builder.append(title).append("\n");

                    //for(int i = 0; i < tds.size(); i=i+11){
                    int i = 0; //USD
                        currency_country = tds.get(i).text();
                        currency_country_token = currency_country.split(" ");
                        currency_rate_buy = tds.get((i+1)).text();
                        currency_rate_sell = tds.get((i+2)).text();
                        builder.append(currency_country_token[0])
                                .append("\t").append(currency_rate_buy)
                                .append("\t").append(currency_rate_sell)
                                .append("\n");
                        currency_USD = (Double.parseDouble(currency_rate_buy)+Double.parseDouble(currency_rate_sell)) /2 ;



                    int j = 11; //HKD
                    currency_country = tds.get(j).text();
                    currency_country_token = currency_country.split(" ");
                    currency_rate_buy = tds.get((j+1)).text();
                    currency_rate_sell = tds.get((j+2)).text();
                    builder.append(currency_country_token[0])
                            .append("\t").append(currency_rate_buy)
                            .append("\t").append(currency_rate_sell)
                            .append("\n");
                    currency_HKD = (Double.parseDouble(currency_rate_buy)+Double.parseDouble(currency_rate_sell)) /2;

                    int g = 22; //GBP, UKD
                    currency_country = tds.get(g).text();
                    currency_country_token = currency_country.split(" ");
                    currency_rate_buy = tds.get((g+1)).text();
                    currency_rate_sell = tds.get((g+2)).text();
                    builder.append(currency_country_token[0])
                            .append("\t").append(currency_rate_buy)
                            .append("\t").append(currency_rate_sell)
                            .append("\n");
                    currency_GBP = (Double.parseDouble(currency_rate_buy)+Double.parseDouble(currency_rate_sell)) / 2;

                    int k = 165; //KRW
                    currency_country = tds.get(k).text();
                    currency_country_token = currency_country.split(" ");
                    currency_rate_buy = tds.get((k+1)).text();
                    currency_rate_sell = tds.get((k+2)).text();
                    builder.append(currency_country_token[0])
                            .append("\t").append(currency_rate_buy)
                            .append("\t").append(currency_rate_sell)
                            .append("\n");
                    currency_KRW = (Double.parseDouble(currency_rate_buy) + Double.parseDouble(currency_rate_sell))/ 2;

                    int jp = 77; //JPY
                    currency_country = tds.get(k).text();
                    currency_country_token = currency_country.split(" ");
                    currency_rate_buy = tds.get((jp+1)).text();
                    currency_rate_sell = tds.get((jp+2)).text();
                    builder.append(currency_country_token[0])
                            .append("\t").append(currency_rate_buy)
                            .append("\t").append(currency_rate_sell)
                            .append("\n");
                    currency_JPY = (Double.parseDouble(currency_rate_buy) + Double.parseDouble(currency_rate_sell))/ 2;


                    //}


                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();
    }

    public void convert(View view){
        DecimalFormat df = new DecimalFormat("#.##");
    double input_value = Double.parseDouble(input.getText().toString());
    textView_USD.setText(df.format(input_value/currency_USD));
    textView_KRW.setText(df.format(input_value/currency_KRW));
    textView_JPY.setText(df.format(input_value/currency_JPY));
    textView_HKD.setText(df.format(input_value/currency_HKD));
    textView_GBP.setText(df.format(input_value/currency_GBP));


    }


}

