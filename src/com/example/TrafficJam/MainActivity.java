package com.example.TrafficJam;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    Puzzle m_puzzle;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }





    public void buttonPressed(View view) {
        Intent intent = new Intent(this, DrawActivity.class);
        startActivity(intent);
    }


    public void buttonLevel(View view){
        Intent intent = new Intent(this, Puzzles.class);
        startActivity(intent);
    }

    public void Options(View view){
        Intent intent = new Intent(this, Options.class);
        startActivity(intent);
    }
}


