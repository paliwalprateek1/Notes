package com.example.prateek.people.Notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prateek.people.R;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    NoteSaver ns = new NoteSaver(this, null, null, 1);
    ListView lv;
    List<Notes> list;

    NotesArrayAdapter naa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, AddNotes.class);
                startActivity(intent);
            }
        });
        Toast.makeText(this, "Add some", Toast.LENGTH_SHORT).show();


        list = ns.getNotes();
        lv = (ListView) findViewById(R.id.listview2);
        naa = new NotesArrayAdapter(this, R.layout.notes, list);
        lv.setAdapter(naa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Notes paa = (Notes) lv.getItemAtPosition(position);
                String s = paa.getHead().toString();
                String ss = paa.getData().toString();
                Notes notes = new Notes(s, ss);
                //Log.d(contact.getName()+" asdg","areeeeeeee");
                Intent intent = new Intent();
                intent.setClass(Main2Activity.this, AboutNotes.class);
                intent.putExtra("head", s);
                intent.putExtra("data", ss);
                startActivity(intent);


            }
        });


        lv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                      public boolean onItemLongClick(AdapterView<?> arg0, View v, final int index, long arg3) {
                          //  Toast.makeText(list.this, myList.get "kya mast dabaya re", Toast.LENGTH_SHORT).show();
                          final int position = lv.getPositionForView(v);
                          new AlertDialog.Builder(Main2Activity.this)
                                  .setTitle("Delete entry")
                                  .setMessage("Are you sure you want to delete this entry?")
                                  .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                      public void onClick(DialogInterface dialog, int which) {

                                          Notes paa = (Notes) lv.getItemAtPosition(position);
                                          String s = paa.getHead().toString();
                                          String ss = paa.getData().toString();
                                          Notes notes = new Notes(s, ss);
                                          ns.deleteNotes(notes);
                                          Log.d("hre", "am i");
                                          finish();
                                          startActivity(getIntent());
                                      }
                                  })
                                  .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                      public void onClick(DialogInterface dialog, int which) {
                                          dialog.dismiss();
                                      }
                                      // do nothing
                                  })
                                  .setIcon(android.R.drawable.ic_dialog_alert)
                                  .show();
                          return true;
                      }
                                      }
        );
    }
}


