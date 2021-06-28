package com.example.resume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void buttonProfile(View view)
    {
        Intent intent = new Intent(this, Profile.class);
        //intent.putExtra("ObjectMyself", self);
        startActivity(intent);
    }

    public void buttonEducation(View view)
    {
        Intent intent = new Intent(this, Education.class);
        //intent.putExtra("ObjectMyself", self);
        startActivity(intent);
    }

    public void buttonWork(View view)
    {
        Intent intent = new Intent(this, Work.class);
        //intent.putExtra("ObjectMyself", self);
        startActivity(intent);
    }

    public void buttonSkill(View view)
    {
        Intent intent = new Intent(this, Skill.class);
        //intent.putExtra("ObjectMyself", self);
        startActivity(intent);
    }

    public void buttonProject(View view)
    {
        Intent intent = new Intent(this, Project.class);
        //intent.putExtra("ObjectMyself", self);
        startActivity(intent);
    }

    public void buttonActivity(View view)
    {
        Intent intent = new Intent(this, Curricular.class);
        //intent.putExtra("ObjectMyself", self);
        startActivity(intent);
    }
}