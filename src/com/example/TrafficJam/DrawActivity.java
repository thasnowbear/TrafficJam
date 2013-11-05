package com.example.TrafficJam;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.example.TrafficJam.DrawView;

import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Notandi
 * Date: 24.10.2013
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
public class DrawActivity extends Activity {
    SharedPreferences prefs;
    private DrawView d;
    int level;
    int diff;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw);
        prefs = this.getSharedPreferences(
                "com.example.TrafficJam", Context.MODE_PRIVATE);
        d = (DrawView) findViewById(R.id.drawview);
        level = prefs.getInt("level", 0);
        diff = prefs.getInt("difficulty", 0);
        //TODO send in diff without app breaking
        d.setDiff(diff);
        d.setLevel(level);
        d.restart();
    }



    public void NextLevel(View view){
        d.next();
        level = d.getlevel();
        prefs.edit().putInt("level", level).commit();
        Update();
    }

    public void PreviousLevel(View view){
        d.previous();
        level = d.getlevel();
        prefs.edit().putInt("level", level).commit();
        Update();
    }

    public void RestartLevel(View view){
        d.restart();
        Update();
    }

    public void Update(){
        String[] s = d.getSolved();
        for(int i = 0; i < s.length; i++){
            prefs.edit().putString(i+"",s[i]);
            System.out.println("UPDATE :  "+ i + "  :   " + s[i]);
        }
    }

}