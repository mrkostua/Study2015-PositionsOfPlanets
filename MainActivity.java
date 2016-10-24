package com.example.planet_11_a_test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void click_button_ISS(View view)
    {
        Intent iss = new Intent(this, ISSActivity.class);
        startActivity(iss);

    }
    public void click_button_Moon(View view)
    {
        Intent moon = new Intent(this, MoonActivity.class);
        startActivity(moon);

    }
    public void click_button_Sun(View view)
    {
        Intent sun = new Intent(this, SunActivity.class);
        startActivity(sun);

    }
    public void click_button_exit(View view)
    {
        finish();
    }
}
