package com.example.prateek.people.Contact;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prateek.people.Notes.Main2Activity;
import com.example.prateek.people.R;

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    PersonSave ps = new PersonSave(this, null, null, 2);
    ListView lv;
    List<Person> list;

    UserArrayAdapter uaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = ps.getcontact();
        lv = (ListView) findViewById(R.id.listview);
        uaa = new UserArrayAdapter(this, R.layout.person,list);
        lv.setAdapter(uaa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Person paa = (Person)lv.getItemAtPosition(position);
                String s = paa.getName().toString();
                String ss = paa.getPhone().toString();
                String sss;

                if(paa.getPath()==null)
                {
                    sss = "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/IMG-20160518-WA0003.jpg";
                }
                else
                {
                    sss = paa.getPath().toString();
                }
                Person contact= new Person(s, ss, sss);
                Log.d(contact.getName()+" asdg","areeeeeeee");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, About.class);
                intent.putExtra("name", s);
                intent.putExtra("number", ss);
                intent.putExtra("path", sss);
                startActivity(intent);


            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            public boolean onItemLongClick(AdapterView<?> arg0, View v, final int index, long arg3){
              //  Toast.makeText(list.this, myList.get "kya mast dabaya re", Toast.LENGTH_SHORT).show();
               final int position = lv.getPositionForView(v);
              /*  new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Person paa = (Person)lv.getItemAtPosition(position);
                                String s = paa.getName().toString();
                                String ss = paa.getPhone().toString();
                                Person contact= new Person(s, ss);
                                ps.deletecontact(contact);
                                Log.d("hre", "am i");
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();*/
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
                builderSingle.setTitle("choose");
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this, android.R.layout.select_dialog_item);
                arrayAdapter.add("Delete");
                arrayAdapter.add("Call");
                arrayAdapter.add("Message");
                arrayAdapter.add("Send a file");

                builderSingle.setNegativeButton(
                        "cancel",
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick (DialogInterface dialog, int which){
                                dialog.dismiss();
                            }});

                builderSingle.setAdapter(

                        arrayAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String strName = arrayAdapter.getItem(which);
                                AlertDialog.Builder builderInner = new AlertDialog.Builder(
                                        MainActivity.this);
                                if(strName=="Delete")
                                {
                                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                    Person paa = (Person)lv.getItemAtPosition(position);
                                    String s = paa.getName().toString();
                                    String ss = paa.getPhone().toString();
                                    String sss;

                                    if(paa.getPath()==null)
                                    {
                                        sss = "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/IMG-20160518-WA0003.jpg";
                                    }
                                    else
                                    {
                                        sss = paa.getPath().toString();
                                    }
                                    Person contact= new Person(s, ss, sss);
                                    ps.deletecontact(contact);
                                    Log.d("hre", "am i");
                                    finish();
                                    startActivity(getIntent());

                                }

                                else if(strName=="Call")
                                {
                                    Person person = list.get(position);
                                    Intent call = new Intent(Intent.ACTION_DIAL);
                                    call.setData(Uri.parse("tel:"+person.getPhone()));
                                    startActivity(call);

                                }

                                else if(strName=="Message")
                                    Toast.makeText(MainActivity.this, "coming soon", Toast.LENGTH_SHORT).show();

                                else if(strName=="Send a file")
                                {
                                    Toast.makeText(MainActivity.this, "coming soon", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                builderSingle.create().show();
                lv.getItemAtPosition(position);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Details.class);
            startActivity(intent);
            finish();
            return true;
        }
        if(id==R.id.about) {
            Toast.makeText(this, "Add some experimental action", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
