package medilive.sudaapps.net.medilive.broadcast;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.activity.MedicineSchedulesList;
import medilive.sudaapps.net.medilive.helper.SQLiteHandler;
import medilive.sudaapps.net.medilive.model.MedicineSchedule;
import medilive.sudaapps.net.medilive.services.AlarmService;
import medilive.sudaapps.net.medilive.utils.Utilities;


/**
 * Created by Adil on 08/10/15.
 */
public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MedicineSchedule medicineSchedule = (MedicineSchedule) intent.getSerializableExtra("medicine");

        Log.d(Alarm.class.getName(), "In Alarm onReceive Method.");
        updateThenNotify(medicineSchedule, context);
    }

    private void updateThenNotify(MedicineSchedule medicineSchedule, Context context) {

        if (medicineSchedule.getInterval() == 0) {
            if (Utilities.calendarToDayString(medicineSchedule.getAlarmTime()).equals(Utilities.calendarToDayString(medicineSchedule.getEndDate()))) {
                medicineSchedule.setIsScheduleEnd(1);
            }
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            cal.add(Calendar.MILLISECOND, -100);
            medicineSchedule.setTime(cal);
            Log.d(Alarm.class.getName(), "Adding one day in the schedule");
        } else {
            Calendar cal = medicineSchedule.getAlarmTime();
            cal.add(Calendar.HOUR, medicineSchedule.getInterval());
            medicineSchedule.setTime(cal);
        }


        Log.d(Alarm.class.getName(), "Going to update the schedule");
        new SQLiteHandler(context).updateSchedules(medicineSchedule);
        context.startService(new Intent(context, AlarmService.class));

        Notify(medicineSchedule, context);
    }

    private void Notify(MedicineSchedule medicineSchedule, Context context) {

        Log.d(Alarm.class.getName(), "Notifying user.");

        String contentText = "Kindly take your medicine.";
        if (medicineSchedule.getIsScheduleEnd() == 1) {
            contentText = contentText + " It is your last medicine.";
        }

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_medicine)
                        .setContentTitle(medicineSchedule.getMedName())
                        .setContentText(contentText)
                        .setSound(Uri.parse("android.resource://medilive.sudaapps.net.medilive/" + R.raw.alar_notify));

        Intent resultIntent = new Intent(context, MedicineSchedulesList.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        (int) medicineSchedule.getAlarmTimeOfDayInMillis(),
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(medicineSchedule.getHour(), mBuilder.build());
    }

    public void SetAlarm(Context context, MedicineSchedule schedule) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        i.putExtra("medicine", schedule);
        PendingIntent pi = PendingIntent.getBroadcast(context, (int) schedule.getAlarmTimeOfDayInMillis(), i, PendingIntent.FLAG_UPDATE_CURRENT);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC_WAKEUP, schedule.getAlarmTimeInMillis(), pi);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, schedule.getAlarmTimeInMillis(), pi); // 1000 * 60 * 2 =Millisec * Second * Minute
        }
    }

    public void CancelAlarm(Context context,MedicineSchedule schedule) {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, (int) schedule.getAlarmTimeOfDayInMillis(), intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
