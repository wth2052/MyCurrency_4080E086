package com.example.apple.mycurrency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;

public class searchcurrency extends AppCompatActivity {
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchcurrency);
        getWebsite();
        result = findViewById(R.id.result);

        ImageView ryan = (ImageView) findViewById(R.id.ryan2);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(ryan);
        Glide.with(this).load(R.drawable.ryan2).into(gifImage);


        ImageView ryan3 = (ImageView) findViewById(R.id.ryan3);
        GlideDrawableImageViewTarget gifImage2 = new GlideDrawableImageViewTarget(ryan3);
        Glide.with(this).load(R.drawable.ryan3).into(gifImage2);


        Button imageButton = (Button) findViewById(R.id.gotopage3);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), main2.class);
                startActivity(intent);
            }
        });
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
                            .append("\t  ").append(currency_rate_sell)
                            .append("\n");




                    int j = 11; //HKD
                    currency_country = tds.get(j).text();
                    currency_country_token = currency_country.split(" ");
                    currency_rate_buy = tds.get((j+1)).text();
                    currency_rate_sell = tds.get((j+2)).text();
                    builder.append(currency_country_token[0])
                            .append("\t ").append(currency_rate_buy)
                            .append(" \t   ").append(currency_rate_sell)
                            .append("\n");


                    int g = 22; //GBP, UKD
                    currency_country = tds.get(g).text();
                    currency_country_token = currency_country.split(" ");
                    currency_rate_buy = tds.get((g+1)).text();
                    currency_rate_sell = tds.get((g+2)).text();
                    builder.append(currency_country_token[0])
                            .append("\t ").append(currency_rate_buy)
                            .append("\t   ").append(currency_rate_sell)
                            .append("\n");

                    int k = 165; //KRW
                    currency_country = tds.get(k).text();
                    currency_country_token = currency_country.split(" ");
                    currency_rate_buy = tds.get((k+1)).text();
                    currency_rate_sell = tds.get((k+2)).text();
                    builder.append(currency_country_token[0])
                            .append("\t").append(currency_rate_buy)
                            .append("\t").append(currency_rate_sell)
                            .append("\n");


                    int jp = 77; //JPY
                    currency_country = tds.get(jp).text();
                    currency_country_token = currency_country.split(" ");
                    currency_rate_buy = tds.get((jp+1)).text();
                    currency_rate_sell = tds.get((jp+2)).text();
                    builder.append(currency_country_token[0])
                            .append("\t ").append(currency_rate_buy)
                            .append("\t ").append(currency_rate_sell)
                            .append("\n");



                    //}


                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(builder.toString());
                    }
                });
            }
        }).start();
    }


    public void convert(View view) {
        DecimalFormat df = new DecimalFormat("#.##");
    }


}
