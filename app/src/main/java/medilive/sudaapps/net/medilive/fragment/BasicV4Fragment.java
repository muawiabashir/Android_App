package medilive.sudaapps.net.medilive.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Phaedra on 7/16/2015.
 */
public abstract class BasicV4Fragment extends Fragment {
    private static final String TAG="BasicV4Fragment";
    public BasicV4Fragment(){
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initValues();
        setAppBar();
        initValuesInViews();
        setOnViewClickListener();
        setOnViewTouchListener();
        setOnViewItemClickListener();
    }

    public void initViews(){
        Log.v(TAG, "initViews()");
    };
    public void initValues(){
        Log.v(TAG,"initValues()");
    };
    public void setAppBar(){
        Log.v(TAG,"setAppBar");
    };
    public  void initValuesInViews(){
        Log.v(TAG,"initValuesInViews");
    };
    public  void setOnViewTouchListener(){
        Log.v(TAG,"setOnViewTouchListener");
    };
    public  void setOnViewClickListener(){
        Log.v(TAG,"setOnViewClickListener");
    };
    public  void setOnViewItemClickListener(){
        Log.v(TAG,"setOnViewItemClickListener()");
    };
}
