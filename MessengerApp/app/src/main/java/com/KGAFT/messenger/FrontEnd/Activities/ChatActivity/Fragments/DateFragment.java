package com.KGAFT.messenger.FrontEnd.Activities.ChatActivity.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.KGAFT.messenger.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFragment extends Fragment {
    private long date;
    private String dateTextContent;

    public DateFragment(long date) {
        this.date = date;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        convertDateToText();
        ((TextView)view.findViewById(R.id.dateText)).setText(dateTextContent);
    }
    private void convertDateToText(){
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        dateTextContent = formater.format(new Date(date));
        translateDateToRus();
    }
    private void translateDateToRus(){
        String[] dateArgs = dateTextContent.split("-");
        String newDate = dateArgs[0];
        switch (Integer.parseInt(dateArgs[1])){
            case 1:
                newDate+=" Января";
                break;
            case 2:
                newDate+=" Февраля";
                break;
            case 3:
                newDate+=" Марта";
                break;
            case 4:
                newDate+=" Апреля";
                break;
            case 5:
                newDate+=" Мая";
                break;
            case 6:
                newDate+=" Июня";
                break;
            case 7:
                newDate+=" Июля";
                break;
            case 8:
                newDate+=" Августа";
                break;
            case 9:
                newDate+=" Сентября";
                break;
            case 10:
                newDate+=" Октября";
                break;
            case 11:
                newDate+=" Ноября";
                break;
            case 12:
                newDate+=" Декабря";
                break;
        }
        newDate+=" "+dateArgs[2];
        dateTextContent = newDate;
    }
}