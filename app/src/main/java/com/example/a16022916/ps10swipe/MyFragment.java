package com.example.a16022916.ps10swipe;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MyFragment extends Fragment {

    private String data;
    private TextView tvResults;
    private Button btnChangeColor;

    public MyFragment() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TextView getTvResults() {
        return tvResults;
    }

    public void setTvResults(TextView tvResults) {
        this.tvResults = tvResults;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_my, container, false);
        btnChangeColor = view.findViewById(R.id.fragBtnColor);

        tvResults = view.findViewById(R.id.fragTvWords);
        tvResults.setText(data);

//        Color colColor;

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View locView) {
                Random rand = new Random();
                int randomValue = rand.nextInt(4);
                switch (randomValue){
                    case 0:
                        view.setBackgroundColor(Color.GREEN);
                        break;
                    case 1:
                        view.setBackgroundColor(Color.LTGRAY);
                        break;
                    case 2:
                        view.setBackgroundColor(Color.BLUE);
                        break;
                    case 3:
                        view.setBackgroundColor(Color.YELLOW);
                        break;

                }

            }
        });




        return view;
    };

}