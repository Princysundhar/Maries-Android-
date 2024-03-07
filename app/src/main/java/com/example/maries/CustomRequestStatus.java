package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CustomRequestStatus extends BaseAdapter {
    private Context context;
    String[] date,status,paymentdate,paymentstatus,amount;

    public CustomRequestStatus(Context applicationContext, String[] date, String[] status, String[] paymentdate, String[] paymentstatus, String[] amount) {


        this.context=applicationContext;
        this.date=date;
        this.status=status;
        this.paymentdate=paymentdate;
        this.paymentstatus=paymentstatus;
        this.amount=amount;
    }

    @Override
    public int getCount() {
        return date.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}