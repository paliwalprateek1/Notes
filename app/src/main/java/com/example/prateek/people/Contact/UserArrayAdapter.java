package com.example.prateek.people.Contact;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prateek.people.R;

import java.util.List;


public class UserArrayAdapter extends ArrayAdapter<Person>
{

    private List<Person> object;
    ImageView imageView;

    public UserArrayAdapter(Context context,int resourceid,List<Person> object)
    {
        super(context,resourceid,object);

        this.object=object;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v==null) {

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.person, null);

        }

        Person person = object.get(position);

        if(person!=null)
        {
            TextView tv = (TextView) v.findViewById(R.id.textView1);
            TextView tv1 = (TextView) v.findViewById(R.id.textView2);
            imageView = (ImageView)v.findViewById(R.id.imageView);

            if(tv!=null)
            {
                tv.setText(person.getName());
            }
            if(tv1!=null)
            {
                tv1.setText(person.getPhone());
            }

            String str = person.getPath();
            Bitmap thumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(str), 80, 80);
            imageView.setImageBitmap(thumbImage);
        }
        return v;
    }
}
