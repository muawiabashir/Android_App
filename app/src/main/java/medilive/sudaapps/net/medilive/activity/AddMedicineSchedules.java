package medilive.sudaapps.net.medilive.activity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.dialog.SelectIntervalDialog;
import medilive.sudaapps.net.medilive.helper.SQLiteHandler;
import medilive.sudaapps.net.medilive.model.MedicineSchedule;

/**
 * Created by Adil on 01/11/2015.
 */
public class AddMedicineSchedules extends AppCompatBaseActivity implements SelectIntervalDialog.NumberPickerCallBack {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
    }

    FloatingActionButton addAlarmView;
    LinearLayout addAlarmParentView;
    static CardView startTimeView,endTimeView,timeView,intervalView;
    static TextView startTimeTextView,endTimeTextView,timeOfAlarmTextView,intervalTextView;
    EditText commentView,dozeView,quantityView,nameView;

    static Animation rotation;
    static MedicineSchedule medicineSchedule = new MedicineSchedule();
    @Override
    public void initViews() {
        super.initViews();

        addAlarmView=(FloatingActionButton)findViewById(R.id.done);
        addAlarmParentView=(LinearLayout)findViewById(R.id.add_alarm_parent);
        startTimeView=(CardView)findViewById(R.id.start_date);
        endTimeView=(CardView)findViewById(R.id.end_date);
        timeView=(CardView)findViewById(R.id.time_of_alarm);
        startTimeTextView=(TextView)findViewById(R.id.start_date_text);
        endTimeTextView=(TextView)findViewById(R.id.end_date_text);
        intervalTextView=(TextView)findViewById(R.id.interval_text);
        timeOfAlarmTextView=(TextView)findViewById(R.id.time_of_alarm_text);
        commentView=(EditText)findViewById(R.id.enter_medicine_comment);
        intervalView=(CardView)findViewById(R.id.interval_time);
        dozeView=(EditText)findViewById(R.id.enter_medicine_dosage);
        quantityView=(EditText)findViewById(R.id.enter_medicine_quantity);
        nameView=(EditText)findViewById(R.id.enter_medicine_name);

    }

    @Override
    public void initValues() {
        super.initValues();
        rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clock_wise_animation);
    }

    @Override
    public void initValuesInViews() {
        super.initValuesInViews();
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                addAlarmView.setImageResource(R.drawable.alarm_check);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void setOnViewClickListener() {
        super.setOnViewClickListener();

        intervalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectIntervalDialog dialog=new SelectIntervalDialog(AddMedicineSchedules.this,AddMedicineSchedules.this,1,24);
                dialog.show();
            }
        });
        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");

            }
        });
        startTimeView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = DatePickerFragment.getInstance(START_TIME);
                newFragment.show(getSupportFragmentManager(), "start_date");
            }
        });
        endTimeView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = DatePickerFragment.getInstance(END_TIME);
                newFragment.show(getSupportFragmentManager(), "end_date");
            }
        });
        addAlarmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineSchedule.setMedName(nameView.getText().toString());
                medicineSchedule.setComment(commentView.getText().toString());
                int quantity=0;
                try{
                    quantity= Integer.parseInt(quantityView.getText().toString());
                }catch (NumberFormatException e){
                    quantity=0;
                }
                medicineSchedule.setQuantity(quantity);
                medicineSchedule.setDosage(dozeView.getText().toString());

                SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
                sqLiteHandler.addMedicineSchedule(medicineSchedule);
                v.startAnimation(rotation);
            }
        });
        addAlarmParentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                circularRevealOnStart(v);
                v.removeOnLayoutChangeListener(this);
            }
        });

    }

    @Override
    public void onSelectingValue(int value) {
        medicineSchedule.setInterval(value);
        intervalTextView.setText(value+"");
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        static String type;
        public static DatePickerFragment getInstance(String dateType){
            Bundle bundle = new Bundle();
            bundle.putString("type",dateType);
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setArguments(bundle);
            return fragment;
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            type=getArguments().getString("type");
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year=c.get(Calendar.YEAR);
            return new DatePickerDialog(getActivity(),R.style.DialogTheme,this,year,month,day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);

            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");

            if(type.equals(START_TIME)) {
                startTimeTextView.setText(sdf.format(calendar.getTime()));
                medicineSchedule.setStartDate(calendar);
            }
            else {
                if(calendar.get(Calendar.DATE)>=Calendar.getInstance().get(Calendar.DATE)){
                    endTimeTextView.setText(sdf.format(calendar.getTime()));
                    medicineSchedule.setEndDate(calendar);
                }
                else if(calendar.before(Calendar.getInstance()))
                    Toast.makeText(getActivity(), R.string.select_from_futur,Toast.LENGTH_LONG).show();
            }
        }
    }


        public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        static String time;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), R.style.DialogTheme, this, hour, minute,
                    false);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if(hourOfDay<10&&minute<10)
                time = "0"+hourOfDay+":"+"0"+minute;
            else if(hourOfDay<10)
                time = "0"+hourOfDay+":"+minute;
            else if(minute<10)
                time = hourOfDay+":"+"0"+minute;
            else {
                time = hourOfDay+":"+minute;
            }

            Calendar c = Calendar.getInstance();

            if(medicineSchedule.getStartDate()==null){
                Toast.makeText(getActivity(), R.string.set_start_end_first,Toast.LENGTH_LONG).show();
                return;
            }
            if(medicineSchedule.getStartDate().getTime().after(c.getTime()))
                c=medicineSchedule.getStartDate();
            c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),hourOfDay,minute);
            medicineSchedule.setTime(c);
            medicineSchedule.setHour(hourOfDay);
            medicineSchedule.setMinutes(minute);
            timeOfAlarmTextView.setText(time);

        }
    }

    private static final String START_TIME="start";
    private static final String END_TIME="end";




    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void circularRevealOnStart(final View view) {
        view.setVisibility(View.INVISIBLE);
        int cx = view.getWidth();
        int cy = 0;
        float finalRadius = Math.max(view.getWidth(), view.getHeight());
        Animator circularReveal;
        circularReveal = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        circularReveal.setDuration(800);
        circularReveal.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
//                view.startAnimation(iconAnimation);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.elastic_animation);
                addAlarmView.startAnimation(animation);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        circularReveal.start();
    }
}
