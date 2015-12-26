package medilive.sudaapps.net.medilive.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Adil on 26/12/2015.
 */
public class SharedPreferenceManager {

    private static final String ALARM_STRING_KEY="alarm";

    public static void saveNextAlarm(Activity mActivity,String alarmString){
        SharedPreferences sharedPref = mActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(ALARM_STRING_KEY, alarmString);
        editor.commit();
    }

    public static String getNextAlarm(Activity mActivity){
        SharedPreferences sharedPref = mActivity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(ALARM_STRING_KEY,null);
    }


}
