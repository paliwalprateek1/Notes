package com.example.prateek.people.Contact;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prateek.people.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class Details extends AppCompatActivity {

    PersonSave ps = new PersonSave(this, null, null, 1);
    EditText etName, etPhone;
    String path;
    final int RESULT_LOAD_IMAGE = 1;
    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        etName = (EditText) findViewById(R.id.editTextname);
        etPhone = (EditText) findViewById(R.id.editTextphone);
        //Toast.makeText(this, "app yaha hai", Toast.LENGTH_SHORT).show();
    }

    public void saveIt(View view) {

        Person person = new Person(etName.getText().toString(), etPhone.getText().toString(), path);
        ps.insertcontact(person);
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addImage(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);


    }
//
//    compile 'com.koushikdutta.ion:ion:2.+'
//    compile 'com.squareup.picasso:picasso:2.4.0'

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Log.d("here am I+++"+ picturePath, "oh bot");
            path = picturePath;
            im = (ImageView) findViewById(R.id.im);
            im.setImageBitmap(BitmapFactory.decodeFile(path));

            imagesend(new File(path));

        }
    }

    String picture_url;
    public void imagesend(File f) {
        Ion.with(getBaseContext()).load("POST", "http://uploads.im/api").uploadProgressHandler(new ProgressCallback() {
            @Override
            public void onProgress(long uploaded, long total) {
                System.out.println("uploaded " + (int) uploaded + " Total: " + total);
            }
        }).setMultipartParameter("platform", "android").setMultipartFile("image", f).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                // Log.d("imageresult",result);

                try {

                    JSONObject jsonObject = new JSONObject(result);

                    String data = jsonObject.getString("data");

                    JSONObject inerJson = new JSONObject(data);

                    String picture_url = inerJson.getString("img_url");
                    toastImage(picture_url);

                    Log.d("picture_url", picture_url);

                    // progressBar.setVisibility(View.GONE);
                } catch (JSONException ee) {
                    Log.d("JSONException", ee + "");
                }


            }
        });
    }
    public void toastImage(String s){
        Log.d(s+"---", "olay");
    }


}
