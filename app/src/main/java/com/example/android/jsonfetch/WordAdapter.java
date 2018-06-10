package com.example.android.jsonfetch;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {


    public WordAdapter(Activity context, ArrayList<Word> words){

        super(context,0,words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent,false);
        }

        Word currentWord = getItem(position);

        TextView nameText = (TextView)listItemView.findViewById(R.id.text_1);

        nameText.setText(currentWord.getName());

        TextView phaseText = (TextView)listItemView.findViewById(R.id.text_2);

        phaseText.setText(currentWord.getPhase());

        TextView durationText = (TextView)listItemView.findViewById(R.id.text_3);

        durationText.setText(currentWord.getDuration());

        TextView dateText = (TextView)listItemView.findViewById(R.id.text_4);

        dateText.setText(currentWord.getDate());


        View textContainer=(View)listItemView.findViewById(R.id.text_container);

        return listItemView;
    }
}
