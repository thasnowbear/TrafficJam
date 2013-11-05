package com.example.TrafficJam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: Notandi
 * Date: 5.11.2013
 * Time: 00:42
 * To change this template use File | Settings | File Templates.
 */
public class Puzzles extends Activity {
    SharedPreferences prefs;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzles);
        prefs = this.getSharedPreferences(
                "com.example.TrafficJam", Context.MODE_PRIVATE);

    }

    public void onClick(View view){
            Intent intent = new Intent(this, DrawActivity.class);
                if(prefs.getString("0", "10").equals("Y")){
            }

            int level = Integer.parseInt(view.getTag().toString());
            level--;
            prefs.edit().putInt("level", level).commit();
            startActivity(intent);

    }
}