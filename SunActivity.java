package com.example.planet_11_a_test2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class SunActivity extends AppCompatActivity
{
    private String adres = "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_512_4500.jpg";
    private Button przycisk;
    private TextView napis;
    private ImageView obraz;
    private Bitmap bmp;
    private ProgressBar condition;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun);

        przycisk= (Button) findViewById(R.id.button6);
        napis= (TextView) findViewById(R.id.textView6);
        obraz= (ImageView) findViewById(R.id.imageView6);
        condition= (ProgressBar) findViewById(R.id.progressBar6);

        OdczytObrazka oo = new OdczytObrazka();
        oo.execute(adres);
    }
    public void click_button_exit(View view)
    {
        finish();
    }
    class OdczytObrazka extends AsyncTask<String, Void, Boolean>
    {
        private Bitmap bmp;
        @Override
        protected Boolean doInBackground(String... params)
        {
            URL u;
            InputStream is;
            boolean ret = true;
            try
            {
                u = new URL(params[0]);
                is=u.openStream();
                Bitmap temp= BitmapFactory.decodeStream(is);
                bmp= temp.copy(Bitmap.Config.ARGB_8888,true);
            }
            catch (Exception e )
            {
                ret = false;
            }

            return ret;
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            if(result)
            {
                Canvas c = new Canvas(bmp);
                Paint p = new Paint();
                int szer = bmp.getWidth() , wys = bmp.getHeight();
                p.setColor(Color.BLUE);
                p.setStyle(Paint.Style.STROKE);
                c.drawRect(0, 0, szer-1, wys-1, p);
                obraz.setImageBitmap(bmp);
                napis.setText("Download complete" + szer + " x " + wys);
            }
            else
            {
                napis.setText("Error while downloading data");
            }
            przycisk.setEnabled(true);
            condition.setVisibility(ProgressBar.INVISIBLE);
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute()
        {
            condition.setVisibility(ProgressBar.VISIBLE);
            przycisk.setEnabled(false);
            napis.setText("Download data ...");
            super.onPreExecute();
        }
    }
}
