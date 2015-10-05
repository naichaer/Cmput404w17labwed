package com.example.hong8.as1;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by hongwang on 15/9/27.
 */
public class visibility_controller {

    //Views in Arraylist player is ordered according to players
    //index/3=0 -> player1    index/3= 1 ->player2 ......
    //index%3=0 -> green button   index%3=1 ->green button with tick   index%3=2 ->player buzzes textview

    //only red button visible
    public void remain_red (ArrayList<View> view_array) {
        for (int i = 0; i < view_array.size(); i++) {
            if (i%3 != 0) {
                view_array.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    //only green button visible (for reaction time)
    public void turn_green (ArrayList<View> view_array) {
        for (int i = 0; i < view_array.size(); i++) {
            if (i%3 != 1) {
                view_array.get(i).setVisibility(View.INVISIBLE);
            }else {
                view_array.get(i).setVisibility(View.VISIBLE);
            }
        }
    }

    //only green button visible (for game buzzer)
    public void remain_green(ArrayList<View> view_array){
        for(int i =0; i< view_array.size();i++){
            if (i%3 ==1){
                view_array.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    //turn button from greenWithTick to green
    public void back_to_green(ArrayList<View> view_array,int index){
        view_array.get(index+1).setVisibility(View.INVISIBLE);
        view_array.get(index).setVisibility(View.VISIBLE);

    }

    //turn to button "greenWithTick"
    public void turn_greenNtick (ArrayList<View> view_array, int index) {
        view_array.get(index).setVisibility(View.INVISIBLE);
        view_array.get(index+1).setVisibility(View.VISIBLE);
    }
}
