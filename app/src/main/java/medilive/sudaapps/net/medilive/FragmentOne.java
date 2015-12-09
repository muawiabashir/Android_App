package medilive.sudaapps.net.medilive;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import medilive.sudaapps.net.medilive.activity.Directory;
import medilive.sudaapps.net.medilive.activity.MedicineSchedulesList;
import medilive.sudaapps.net.medilive.activity.RegisterActivity;
import medilive.sudaapps.net.medilive.helper.SQLiteHandler;
import medilive.sudaapps.net.medilive.helper.SessionManager;

/**
 * Created by muawia.ibrahim on 8/27/2015.
 */
public class FragmentOne extends Fragment {
    ListView listView;
    GridView grid;
   // private myAdapter myadaapter;
    public String[] drawer_item;
    private GridViewAdapter gridAdapter;
    private ActionBarDrawerToggle drawerListener;
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    String[] web = {
            "التسجبل", "بحث عن دواء", "خدمات طبية", "معلومات عن دواء", "اخري"

    };
    int[] imageId = {
            R.drawable.image0,
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,


    };
    private SQLiteHandler db;
    private SessionManager session;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_one, viewGroup, false);
        //CustomGrid adapter = new CustomGrid(FragmentOne.this, web, imageId);
        CustomGrid adapter = new CustomGrid(getActivity(), web, imageId);
        grid=(GridView)view.findViewById(R.id.gridView);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
           //     Toast.makeText(getActivity(), "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                if (position==0){
                    db = new SQLiteHandler(getActivity());

                    // session manager
                    session = new SessionManager(getActivity());

                    if (!session.isLoggedIn()) {
                        logoutUser();
                    }


                }
                if (position==1){
                Intent intent=new Intent(getActivity(),MedicineSchedulesList.class);
                    startActivity(intent);
                }
                if (position==2){
                    Intent intent=new Intent(getActivity(),Directory.class);
                    startActivity(intent);
                }
                if (position==3){
                    Intent intent=new Intent(getActivity(),ImageSlider.class);
                    startActivity(intent);
                }

            }
        });


        return view;
    }
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);

    }
}
