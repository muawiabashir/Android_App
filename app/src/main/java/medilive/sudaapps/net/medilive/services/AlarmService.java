package medilive.sudaapps.net.medilive.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import medilive.sudaapps.net.medilive.broadcast.Alarm;
import medilive.sudaapps.net.medilive.helper.SQLiteHandler;
import medilive.sudaapps.net.medilive.model.MedicineSchedule;


/**
 * Created by Adil on 09/10/15.
 */
public class AlarmService extends Service
{
    Alarm alarm = new Alarm();
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d(Service.class.getName(),"Fetching db.");
        List<MedicineSchedule> list=new SQLiteHandler(this).getMedicineSchedules();
        Collections.sort(list);
        for(MedicineSchedule medicineSchedule:list)
            if(!medicineSchedule.isScheduleTimePassed()) {
                alarm.SetAlarm(this, medicineSchedule);
                Log.d(Service.class.getName(),medicineSchedule.getMedName()+" scheduling");
//                break;
            }

        Log.d(Service.class.getName(),"Alarm setting loop done.");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
