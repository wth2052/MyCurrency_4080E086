package com.example.apple.mycurrency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;



public class result extends AppCompatActivity {

    private String input_string;
    private double input_value;

    private TextView textView_USD;
    private TextView textView_GBP;
    private TextView textView_HKD;
    private TextView textView_KRW;
    private TextView textView_JPY;

    private double currency_USD;
    private double currency_GBP;
    private double currency_HKD;
    private double currency_KRW;
    private double currency_JPY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String input_string = intent.getStringExtra("input");

        input_value = Double.parseDouble(input_string);
        ImageView ryan = (ImageView) findViewById(R.id.ryanstick);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(ryan);
        Glide.with(this).load(R.drawable.ryanstick).into(gifImage);
        ImageView ryan2 = (ImageView) findViewById(R.id.ryanrun);
        GlideDrawableImageViewTarget gifImage2 = new GlideDrawableImageViewTarget(ryan2);
        Glide.with(this).load(R.drawable.ryanrun).into(gifImage2);
        textView_USD = findViewById(R.id.textview_USD);
        textView_GBP = findViewById(R.id.textview_GBP);
        textView_HKD = findViewById(R.id.textview_HKD);
        textView_KRW = findViewById(R.id.textview_KRW);
        textView_JPY = findViewById(R.id.textview_JPY);

        getWebsite(); //呼叫
    }

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    //台灣銀行牌告匯率
                    Document doc = Jsoup.connect("https://finance.naver.com/marketindex/").get();
                    String title = doc.title(); //取得網頁標題
                    Elements tds = doc.select("td"); //選擇網頁中td標籤中的內容
                    String currency_country = null;
                    String currency_rate_buy = null;
                    String currency_rate_sell = null;
                    String[] currency_country_token = null;

                    //builder.append(title).append("\n");

                    //for(int i = 0; i < tds.size(); i=i+11){
                    int i = 0;  //currency_USD 美金
                    currency_country = tds.get(i).text();
                    currency_country_token = currency_country.split(" "); //字串切割
                    currency_rate_buy = tds.get((i + 1)).text();
                    currency_rate_sell = tds.get((i + 2)).text();
                    currency_USD = (Double.parseDouble(currency_rate_buy)
                            + Double.parseDouble(currency_rate_sell)) /2 ;
                    builder.append(currency_country_token[0])
                            .append("\t").append(currency_USD)
                            .append("\n");
/*
                    int j = 22; //currency_GBP 英鎊
                    currency_country = tds.get(j).text();
                    currency_country_token = currency_country.split(" "); //字串切割
                    currency_rate_buy = tds.get((j + 1)).text();
                    currency_rate_sell = tds.get((j + 2)).text();
                    currency_GBP = (Double.parseDouble(currency_rate_buy)
                            + Double.parseDouble(currency_rate_sell)) / 2;
                    builder.append(currency_country_token[0])
                            .append("\t").append(currency_GBP)
                            .append("\n");

                    int k = 11; //currency_HKD 加幣
                    currency_country = tds.get(k).text();
                    currency_country_token = currency_country.split(" "); //字串切割
                    currency_rate_buy = tds.get((k + 1)).text();
                    currency_rate_sell = tds.get((k + 2)).text();
                    currency_HKD = (Double.parseDouble(currency_rate_buy)
                            + Double.parseDouble(currency_rate_sell)) / 2;
                    builder.append(currency_country_token[0])
                            .append("\t").append(currency_HKD)
                            .append("\n");


                    int l = 165; //currency_KRW 韓元
                    currency_country = tds.get(l).text();
                    currency_country_token = currency_country.split(" "); //字串切割
                    currency_rate_buy = tds.get((l + 1)).text();
                    currency_rate_sell = tds.get((l + 2)).text();
                    currency_KRW = (Double.parseDouble(currency_rate_buy)
                            + Double.parseDouble(currency_rate_sell)) / 2;
                    builder.append(currency_country_token[0])
                            .append("\t").append(currency_KRW)
                            .append("\n");


                    int m = 77; //currency_JPY 日圓
                    currency_country = tds.get(m).text();
                    currency_country_token = currency_country.split(" "); //字串切割
                    currency_rate_buy = tds.get((m + 1)).text();
                    currency_rate_sell = tds.get((m + 2)).text();
                    currency_JPY = (Double.parseDouble(currency_rate_buy)
                            + Double.parseDouble(currency_rate_sell)) / 2;
                    builder.append(currency_country_token[0])
                            .append("\t").append(currency_JPY)
                            .append("\n");
*/


                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //result.setText(builder.toString());
                    }
                });
            }
        }).start();
    }//getWebsite() 函式結束
git

    public void convert(View view){

        DecimalFormat df = new DecimalFormat("#.###");
        textView_USD.setText("USD(美金): " + df.format(input_value*currency_USD));

        /*
        textView_GBP.setText("GBP(英鎊): " + df.format(input_value/currency_GBP));
        textView_HKD.setText("HKD(港币)" + df.format(input_value/currency_HKD));
        textView_KRW.setText("KRW(韓元): " + df.format(input_value/currency_KRW));
        textView_JPY.setText("JPY(日圓): " + df.format(input_value/currency_JPY));
        */
    }

}//ResultActivity結束