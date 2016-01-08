package medilive.sudaapps.net.medilive.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import medilive.sudaapps.net.medilive.fragment.FragmentMedicineHistory;
import medilive.sudaapps.net.medilive.fragment.FragmentMedicineSchedule;

/**
 * Created by Phaedra on 6/11/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final String TAG="ViewPagerAdapter";
    private static String FRAGMENT_NAME_SCHEDULES="Schedules";
    private static String FRAGMENT_NAME_HISTORY="History";
    private FragmentManager fragMan=null;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragMan=fm;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new FragmentMedicineSchedule();
        else if(position==1)
            return new FragmentMedicineHistory();
        return null;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       if(position==0)
           return FRAGMENT_NAME_SCHEDULES;
        else if(position==1)
           return FRAGMENT_NAME_HISTORY;
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
