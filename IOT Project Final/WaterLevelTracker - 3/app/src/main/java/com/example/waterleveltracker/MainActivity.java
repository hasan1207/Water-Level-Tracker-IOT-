package com.example.waterleveltracker;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.location.Location;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;




import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;




public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable fetchTask;
    private int[] images = {R.drawable.copy2, R.drawable.copy3, R.drawable.copy4, R.drawable.copy5, R.drawable.copy6};
    private ImageView myImageView;
    private int lastWaterLevel = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImageView = findViewById(R.id.myImageView);

        handler = new Handler(Looper.getMainLooper());
        fetchTask = new Runnable() {
            @Override
            public void run() {
                new FetchThingSpeakData().execute();
                handler.postDelayed(this, 1000); // schedule next execution in 1 second
            }
        };
        handler.post(fetchTask);
    }

    private class FetchThingSpeakData extends AsyncTask<Void, Void, ThingSpeakData> {
        @Override
        protected ThingSpeakData doInBackground(Void... voids) {
            try {
                String response = ThingSpeakAPI.getLatestData();
                return new ThingSpeakData(response);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ThingSpeakData data) {
            if (data != null) {
                int waterLevel = data.getWaterLevel();
                if (waterLevel >= 0 && waterLevel < images.length && waterLevel != lastWaterLevel) {
                    myImageView.setImageResource(images[waterLevel]);
                    lastWaterLevel = waterLevel;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(fetchTask);
    }
}