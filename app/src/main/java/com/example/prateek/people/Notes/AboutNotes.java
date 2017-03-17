package com.example.prateek.people.Notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.prateek.people.R;

public class AboutNotes extends AppCompatActivity {
    TextView textHead;
    TextView textData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_notes);

        Intent intent = new Intent();
        String str = getIntent().getStringExtra("head");
        String strr = getIntent().getStringExtra("data");

        textHead = (TextView) findViewById(R.id.textHead);
        textData = (TextView) findViewById(R.id.textData);

        textHead.setText(str);
        //Log.d("oyouyoyoyoy  "+NAME,"");
        textData.setText(strr);
    }
}

