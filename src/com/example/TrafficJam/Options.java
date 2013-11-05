package com.example.TrafficJam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: Notandi
 * Date: 5.11.2013
 * Time: 00:56
 * To change this template use File | Settings | File Templates.
 */
public class Options extends Activity {
    private int diff;
    SharedPreferences prefs;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = this.getSharedPreferences(
                "com.example.TrafficJam", Context.MODE_PRIVATE);
        setContentView(R.layout.options);
    }

    public void ClearPref(View view){
        SharedPreferences preferences = getSharedPreferences("com.example.TrafficJam", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }

    public void Hard(View view){
        diff = 2;
        prefs.edit().putInt("difficulty", diff).commit();
    }
    public void Medium(View view){
        diff = 1;
        prefs.edit().putInt("difficulty", diff).commit();

    }
    public void Easy(View view){
        diff = 0;
        prefs.edit().putInt("difficulty", diff).commit();
    }



}