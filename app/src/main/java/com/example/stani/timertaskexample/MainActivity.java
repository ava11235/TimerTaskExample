package com.example.stani.timertaskexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    //declare time and timer task instance vars
    Timer timer;
    TimerTask timerTask;

    //use a Handler to run the TimerTask

    final Handler handler = new Handler();

    //instance var for the widget
    Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get reference to widget
        cancelButton = findViewById(R.id.cancelButton);

        //handle button's on click event
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if the timer is still running, cancel it
                if (timer != null) {
                }
                timer.cancel();

                timer = null;

            }
        });
    }

    @Override

    protected void onResume() {

        super.onResume();
        // start timer when the app comes from the background

        startTimer();

    }

    //start the timer method
    public void startTimer() {

        //instanciate a new timer

        timer = new Timer();




        initializeTimerTask();


        //schedule the timer -  after 5000ms delay, run it every 5000ms

        timer.schedule(timerTask, 5000, 5000); //

    }

    //initialize the TimerTask with what it will do
    public void initializeTimerTask() {


        timerTask = new TimerTask() {

            public void run() {


                //use a handler to display a toast with the current timestamp

                handler.post(new Runnable() {

                    public void run() {

                        //get  current timeStamp

                        Calendar calendar = Calendar.getInstance();

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");

                        final String strDate = simpleDateFormat.format(calendar.getTime());


                        //display the toast

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), strDate, duration);

                        toast.show();

                    }

                });

            }
        };

    }

}