package medilive.sudaapps.net.medilive.model;

import android.util.Log;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Adil on 01/11/2015.
 */
public class MedicineSchedule implements Serializable,Comparable<MedicineSchedule>{
    String medName;
    String dosage;
    int quantity=0;
    String comment;
    Calendar startDate;
    Calendar endDate;
    Calendar time;
    int isScheduleEnd=0;
    int hour=0;
    int minutes=0;


    public MedicineSchedule() {
    }

    public MedicineSchedule(String medName, String dosage, int quantity, String comment, Calendar startDate, Calendar endDate, Calendar time,int isScheduleEnd) {
        this.medName = medName;
        this.dosage = dosage;
        this.quantity = quantity;
        this.comment = comment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.isScheduleEnd=isScheduleEnd;
    }

    public int getIsScheduleEnd() {
        return this.isScheduleEnd;
    }

    public void setIsScheduleEnd(int isScheduleEnd) {
        this.isScheduleEnd = isScheduleEnd;
    }

    public String getMedName() {
        return this.medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getDosage() {
        return this.dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Calendar getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Calendar getAlarmTime() {
        return this.time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public long getStartDateInMillis(){
        return this.startDate.getTimeInMillis();
    }

    public long getEndDateInMillis(){
        return this.endDate.getTimeInMillis();
    }

    public long getAlarmTimeInMillis(){
        return this.time.getTimeInMillis();
    }


    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public long getAlarmTimeOfDayInMillis(){
        return hour*60*60*1000+minutes*60*1000;
    }

    public boolean isScheduleTimePassed(){
        long systemTime=System.currentTimeMillis();
        if(time.getTimeInMillis()>systemTime){
            if(Calendar.getInstance().before(startDate)){
                Log.d(MedicineSchedule.class.getName(),"Don't schedule this, it is future task");
                return true;
            }
            return false;
        }else{
            return true;
        }
    }
    @Override
    public int compareTo(MedicineSchedule another) {
        if ( this.getAlarmTimeOfDayInMillis()>another.getAlarmTimeOfDayInMillis() )
            return 1;
        else if ( this.getAlarmTimeOfDayInMillis()<another.getAlarmTimeOfDayInMillis() )
            return -1;
        return 0;
    }
}
