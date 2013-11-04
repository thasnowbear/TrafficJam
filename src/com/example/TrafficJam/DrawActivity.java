package com.example.TrafficJam;


import android.app.Activity;
import android.content.Intent;
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
    private DrawView d;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw);
        d = (DrawView) findViewById(R.id.drawview);
    }

    public void NextLevel(View view){
        d.next();


    }

    public void PreviousLevel(View view){
        d.previous();

    }

}