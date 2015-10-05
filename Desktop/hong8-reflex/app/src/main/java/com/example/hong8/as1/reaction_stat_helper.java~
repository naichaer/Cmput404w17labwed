package com.example.hong8.as1;

import android.app.Application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hongwang on 15/9/29.
 */
public class reaction_stat_helper extends Application{
    private int count=0;
    public ArrayList<Integer> reaction_times = new ArrayList<Integer>();
    public ArrayList<Integer> copy_times=new ArrayList<Integer>();

    public void add_reaction_time(int time){
        reaction_times.add(time);
        copy_times.add(time);
        count++;
    }

    public int getCount(){
        return count;
    }

    public String getMinTime(){
        if (reaction_times.size() == 0){
            return "";
        }
        Collections.sort(copy_times);
        return ""+Collections.min(copy_times);
    }

    //if x=10, get minimum reaction time for last 10 times
    //if x=1000,get minimum reaction time for last 100 times
    public String getXMinTime(int x){
        if(count<x){
            return getMinTime();
        }
        int xmin = reaction_times.get(count - 1);
        for(int i=1; i<x;i++){
            int time=reaction_times.get(count - 1 - i);
            if (xmin >time){
                xmin=time;
            }
        }
        return ""+xmin;
    }

    public String getMaxTime(){
        if (reaction_times.size() == 0){
            return "";
        }
        Collections.sort(copy_times);
        return ""+Collections.max(copy_times);
    }

    //if x=10, get maximum reaction time for last 10 times
    //if x=1000,get maximum reaction time for last 100 times
    public String getXMaxTime(int x){
        if(count<x){
            return getMaxTime();
        }
        int xmax = reaction_times.get(count - 1);
        for(int i=1; i<x;i++){
            int time=reaction_times.get(count - 1-i);
            if (xmax < time){
                xmax=time;
            }
        }
        return ""+xmax;
    }

    public String getMedTime(){
        if (reaction_times.size() == 0){
            return "";
        }
        int middle = count/2;
        Collections.sort(copy_times);
        return ""+copy_times.get(middle);
    }

    public String getXMedTime(int x){
        if(count<x){
            return getMedTime();
        }else{
            int middle = x/2;
            //abstruct last x times record from reaction_times list
            List<Integer> subList =reaction_times.subList(count-x, count);
            ArrayList<Integer> copy= new ArrayList<Integer>();
            for (Integer i: subList){
                copy.add(i);
            }
            Collections.sort(copy);
            return ""+copy.get(middle);
        }
    }

    //average time = total time / played times
    public String getAvgTime(){
        if (reaction_times.size() == 0){
            return "";
        }
        int sum = 0;
        //compute total times
        for (Integer s : reaction_times) {
            sum = sum + s;
        }
        return ""+ sum / count;
    }

    public String getXAvgTime(int x){
        int xsum=0;
        if(count< x){
            return getAvgTime();
        }else{
            for(int i=0; i<x;i++){
                int time= reaction_times.get(count - 1-i);
                //compute total times
                xsum=xsum+time;
            }
            return ""+xsum/x;
        }
    }

}
