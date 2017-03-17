package com.example.prateek.people.Notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.prateek.people.R;

public class AddNotes extends AppCompatActivity {

    NoteSaver ns = new NoteSaver(this, null, null, 1);
    EditText etHead, etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        etHead = (EditText) findViewById(R.id.editHead);
        etData = (EditText) findViewById(R.id.editData);
    }

    public void saveIt(View view) {
        Notes notes = new Notes(etHead.getText().toString(), etData.getText().toString());
        ns.insertNotes(notes);
        finish();
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
