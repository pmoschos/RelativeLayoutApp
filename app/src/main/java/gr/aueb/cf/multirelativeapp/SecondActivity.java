package gr.aueb.cf.multirelativeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private TextView cityTV;
    private TextView agreeTV;
    private TextView rateTV;
    private TextView ageTV;
    private EditText siteET;
    private Button navBtn;
    private String myCities = "";
    private final String SHARED_PREF = "sharedPrefs";
    private final String URL = "url";
    private String myUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        cityTV = findViewById(R.id.cityTV);
        agreeTV = findViewById(R.id.agreeTV);
        rateTV = findViewById(R.id.rateTV);
        ageTV = findViewById(R.id.ageTV);
        siteET = findViewById(R.id.siteET);
        navBtn = findViewById(R.id.navBtn);

        Intent intent = getIntent();
        String rate = intent.getStringExtra("rate");
        String age = intent.getStringExtra("age");
        String agree = intent.getStringExtra("agree");
        ArrayList<String> cities = intent.getStringArrayListExtra("cities");

        for (String city : cities) {
            myCities += city + " ";
        }

        loadUrl();

        rateTV.setText("Rate: " + rate);
        ageTV.setText("Age: " + age);
        agreeTV.setText("Agree: " + agree);
        cityTV.setText("Cities: " + myCities);
        siteET.setText(myUrl);

        navBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = siteET.getText().toString().trim();
                Intent siteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(siteIntent);
                saveUrl();
            }
        });

    }

    private void saveUrl() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(URL, siteET.getText().toString().trim());
        editor.apply();
    }

    private void loadUrl() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        myUrl = sharedPreferences.getString(URL, "");
    }
}