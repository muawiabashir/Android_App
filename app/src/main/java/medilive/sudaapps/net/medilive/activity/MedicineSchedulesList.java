package medilive.sudaapps.net.medilive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.adapter.ViewPagerAdapter;
import medilive.sudaapps.net.medilive.services.AlarmService;
import medilive.sudaapps.net.medilive.utils.Utilities;

/**
 * Created by muawia.ibrahim on 10/12/2015.
 */
public class MedicineSchedulesList extends AppCompatBaseActivity {

    private static final String TAG="MedicineSchedulesList";

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    FloatingActionButton fab;
    ImageView addSchedule;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_screen);

    }

    @Override
    public void initViews() {
        Log.v(TAG, "initViews()");
        addSchedule=(ImageView)findViewById(R.id.add_alarm);
        toolbar= (Toolbar)findViewById(R.id.toolbar);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
//        fab=(FloatingActionButton)findViewById(R.id.fab);
    }

    @Override
    public void initValues() {
        Log.v(TAG, "initValues");
    }

    @Override
    public void initValuesInViews() {
        super.initValuesInViews();
        viewPagerAdapter=new ViewPagerAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void setOnViewSwipe() {
        super.setOnViewSwipe();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    addSchedule.startAnimation(Utilities.AnimationUtilities.setSlideInChildBottomAnimation(getApplicationContext(), addSchedule));
                } else {
                    addSchedule.startAnimation(Utilities.AnimationUtilities.setSlideOutChildBottomAnimation(getApplicationContext(), addSchedule));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this, AlarmService.class));
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void setOnViewClickListener() {
        super.setOnViewClickListener();

    }

    @Override
    public void setAppBar() {
        super.setAppBar();
        toolbar.setTitle(R.string.toolbar_med_sch);
        setSupportActionBar(toolbar);
    }

}