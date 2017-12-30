package com.lzy.mywheels.App;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zidan on 2017/8/30.
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivty(Activity activity){

        activities.add(activity);
    }

    public static void removeActivty(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activities){

            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
