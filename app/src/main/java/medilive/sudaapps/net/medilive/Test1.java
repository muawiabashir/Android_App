package medilive.sudaapps.net.medilive;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by muawia.ibrahim on 10/14/2015.
 */
public class Test1 extends Activity {
    HashMap<String, List<String>> Movies_category;
    List<String> Movies_list;
    ExpandableListView Exp_list;
    MoviesAdapter adapter;
    private static final String[] IMAGES = new String[] {
            "http://tugbaustundag.com/slider/Android_Developer_Days_Logo.png",
            "http://tugbaustundag.com/slider/cocuklar-icin-bilisim-zirvesi.jpg",
            "http://tugbaustundag.com/slider/indirmobil700.jpg",
            "http://tugbaustundag.com/slider/ux700.png"

    };
    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);
        final ExpandableListView  Exp_list = (ExpandableListView) findViewById(R.id.exp_list);
        Movies_category = DataProvider.getInfo();
        Movies_list = new ArrayList<String>(Movies_category.keySet());
        adapter = new MoviesAdapter(this, Movies_category, Movies_list);
        Exp_list.setAdapter(adapter);

    }
}