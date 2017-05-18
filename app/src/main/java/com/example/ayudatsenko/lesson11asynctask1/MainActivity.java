package com.example.ayudatsenko.lesson11asynctask1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView clicksView;
    Button clicksBtn;
    Button progressBtn;
    TextView statusView;
    ProgressBar indicator;

    int[] integers = null;
    int cliks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        integers = new int[100];
        for(int i=0; i<100; i++){
            integers[i]=i+1;
        }

        clicksView = (TextView) findViewById(R.id.clicksView);
        clicksBtn = (Button) findViewById(R.id.clicksBtn);

        clicksBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cliks++;
                clicksView.setText("Clics: " + cliks);
            }
        });

        progressBtn = (Button) findViewById(R.id.progressBtn);
        statusView = (TextView) findViewById(R.id.statusView);
        indicator = (ProgressBar) findViewById(R.id.indicator);

        progressBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new ProgressTask().execute();
            }
        });
    }

    class ProgressTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground(Void... unused){
            for(int i=0; i < integers.length; i++){
                publishProgress(i);
                SystemClock.sleep(100);
            };
            return (null);
        }

        @Override
        protected void onProgressUpdate(Integer... items){
            indicator.setProgress(items[0]+1);
            statusView.setText("Status: " + String.valueOf(items[0]+1));
        }

        @Override
        protected void onPostExecute(Void unused){
            Toast.makeText(getApplicationContext(), "Task finfshed", Toast.LENGTH_SHORT).show();
        }

    }

}
