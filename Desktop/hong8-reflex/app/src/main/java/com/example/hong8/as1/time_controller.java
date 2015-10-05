package com.example.hong8.as1;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hongwang on 15/9/27.
 */
public class time_controller {
    public long set_timer(TimerTask task) {
        //let the timer waits for a random number from 10 to 2000
        Timer timer = new Timer(true);
        int random = (int) (Math.random() * 1990) + 10;
        System.out.printf("random waiting time is %d%n", random);
        timer.schedule(task, random);

        //record the current time when the green button appears
        long start_time = System.currentTimeMillis();
        System.out.println(new Date() + "\n");

        return start_time;
    }

    public long compute_time(long start_time){
        //record the time when the user press the green button
        long reaction_time;
        long end_time = System.currentTimeMillis();
        System.out.println(new Date() + "\n");

        reaction_time = end_time - start_time;
        return reaction_time;
    }
}
