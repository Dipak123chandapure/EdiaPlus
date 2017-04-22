package com.example.deepak.myapplication.Utility;

import android.graphics.Color;
import android.widget.EditText;
import android.widget.ImageView;


public class Utility {

    public static String CURRENT_FRAGMENT;
    public static EditText headerEditText;
    public static ImageView headerImageOne;
    public static ImageView headerImageTwo;
    public static ImageView headerImageThree;


    public static int getCircularTextBackground(char c){
        switch (c){
            case 'R' :
                return Color.rgb( 190, 220,250);
            case 'D' :
                return Color.rgb( 200, 250, 200);

            case 'A' :
                return Color.rgb( 180, 250,250);

            case 'S' :
                return Color.rgb( 250, 180,200);
            case 'G' :
                return Color.rgb( 250, 200,120);
            case 'J' :
                return Color.rgb( 250, 200, 160);
            case 'H' :
                return Color.rgb( 250, 180,250);
            case 'K' :
                return Color.rgb( 200, 200,230);

            default: return Color.rgb( 210, 220,210);
        }
    }

}
