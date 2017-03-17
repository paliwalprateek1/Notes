package com.example.prateek.people.Notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.prateek.people.R;

import java.util.List;

/**
 * Created by prateek on 16/5/16.
 */
public class NotesArrayAdapter extends ArrayAdapter<Notes> {

    private List<Notes> object;

    public NotesArrayAdapter(Context context, int resourceid, List<Notes> object) {
        super(context,resourceid,object);
        this.object=object;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.notes, null);
        }

        Notes notes = object.get(position);

        if(notes!=null) {
            TextView tv = (TextView) v.findViewById(R.id.textView1);
            TextView tv1 = (TextView) v.findViewById(R.id.textView2);
            if(tv!=null) {
                tv.setText(notes.getHead());
            }
            if(tv1!=null) {
                String str = notes.getData();
                int n = str.length();
                String s;
                if(n<=30) {
                    s = str;
                }
                else {
                    s = str.substring(0, 30);
                }
                tv1.setText(s);
            }
        }
        return v;
    }
}
