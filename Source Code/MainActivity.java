package com.example.resume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    profileJava re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonNext(View view)
    {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("ObjectResume", re);
        startActivity(intent);
    }






}