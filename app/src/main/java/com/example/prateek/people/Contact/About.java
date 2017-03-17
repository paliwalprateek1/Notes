package com.example.prateek.people.Contact;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prateek.people.R;

public class About extends AppCompatActivity {
    ImageView imageView;
    TextView textName;
    TextView textPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = new Intent();
        String str = getIntent().getStringExtra("name");
        String strr = getIntent().getStringExtra("number");
        String star = getIntent().getStringExtra("path");

        textName = (TextView) findViewById(R.id.textName);
        textPhone = (TextView) findViewById(R.id.textPhone);
        imageView = (ImageView) findViewById(R.id.imageView2);

        imageView.setImageBitmap(BitmapFactory.decodeFile(star));


        textName.setText(str);
        //Log.d("oyouyoyoyoy  "+NAME,"");
        textPhone.setText(strr);
    }
}
