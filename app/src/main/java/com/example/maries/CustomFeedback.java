package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomFeedback extends BaseAdapter {
    String[] Date,Name,Feedback;
    private Context context;
    public CustomFeedback(Context context, String[] Date, String[] Name,String[] Feedback){
        this.context=context;
        this.Date=Date;
        this.Name=Name;
        this.Feedback=Feedback;
    }

    @Override
    public int getCount() {
        return 0;
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

            LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;
            if(view==null)
            {
                gridView=new View(context);
                //gridView=inflator.inflate(R.layout.customview, null);
                gridView=inflator.inflate(R.layout.activity_custom_feedback,null);

            }
            else
            {
                gridView=(View)view;

            }
            TextView tv1=(TextView)gridView.findViewById(R.id.textView48);
            TextView tv2=(TextView)gridView.findViewById(R.id.textView46);
            TextView tv3=(TextView)gridView.findViewById(R.id.textView50);


            tv1.setTextColor(Color.BLACK);


            tv1.setText(Date[i]);
            tv2.setText(Name[i]);
            tv3.setText(Feedback[i]);



            return gridView;
    }
}