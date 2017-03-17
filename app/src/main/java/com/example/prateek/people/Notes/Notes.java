package com.example.prateek.people.Notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.prateek.people.R;

public class Notes  {

    private String head;
    private String data;

    public Notes(String head,String data)
    {
        this.head=head;
        this.data=data;
    }

    public String getHead() {
        return head;
    }

    public String getData() {
        return data;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
