package medilive.sudaapps.net.medilive.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.activity.AddMedicineSchedules;
import medilive.sudaapps.net.medilive.adapter.FragmentMedicineListAdapter;
import medilive.sudaapps.net.medilive.helper.SQLiteHandler;
import medilive.sudaapps.net.medilive.listener.ListItemTouchListener;
import medilive.sudaapps.net.medilive.model.MedicineSchedule;

/**
 * Created by Adil on 7/16/2015.
 */
public class FragmentMedicineSchedule extends BasicV4Fragment {

    private static final String TAG="FragmentMedicineSchedule";
    private static SurfaceView itemVideoView;
    View rootView;
    RecyclerView videosListView;
    FragmentMedicineListAdapter adapterMedSchedules;
    private static FragmentMedicineSchedule instance=null;
    ArrayList<MedicineSchedule> medicineScheduleArrayList=new ArrayList<>();
    ImageView addSchedule;
    public static FragmentMedicineSchedule getInstance(){
        return instance;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        instance=this;
        rootView=(View)inflater.inflate(R.layout.fragment_medicine, container, false);
        return rootView;
    }

    @Override
    public void initViews() {
        super.initViews();
        addSchedule=(ImageView) getActivity().findViewById(R.id.add_alarm);
        videosListView=(RecyclerView)rootView.findViewById(R.id.recyclerView);
    }

    ArrayList<MedicineSchedule> schedulesList=new ArrayList<>();
    @Override
    public void initValues() {
        super.initValues();
        adapterMedSchedules =new FragmentMedicineListAdapter(medicineScheduleArrayList,getActivity());
        SQLiteHandler sqLiteHandler=new SQLiteHandler(getActivity());
        schedulesList=sqLiteHandler.getMedicineSchedules();
    }

    @Override
    public void initValuesInViews() {
        super.initValuesInViews();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        videosListView.setLayoutManager(linearLayoutManager);
        videosListView.setItemViewCacheSize(500);
        videosListView.setAdapter(adapterMedSchedules);
    }

    @Override
    public void onResume() {
        super.onResume();
        SQLiteHandler sqLiteHandler=new SQLiteHandler(getActivity());
        adapterMedSchedules.update(sqLiteHandler.getMedicineSchedules());
    }

    @Override
    public void setOnViewClickListener() {
        super.setOnViewClickListener();
        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getActivity(), AddMedicineSchedules.class);
//                intent.putExtra(DetailsActivity.ID, Contact.CONTACTS[position].getId());

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        // the context of the activity
                        getActivity(),

                        // For each shared element, add to this method a new Pair item,
                        // which contains the reference of the view we are transitioning *from*,
                        // and the value of the transitionName attribute
                        new Pair<View, String>(view.findViewById(R.id.add_alarm),
                                getString(R.string.transition_name_alarm))
                );

                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());

            }
        });
    }

    @Override
    public void setOnViewItemClickListener() {
        super.setOnViewItemClickListener();
        videosListView.addOnItemTouchListener(new ListItemTouchListener(getActivity(), videosListView, new ListItemTouchListener.ClickListenerInterface() {
            @Override
            public void onClick(View v, int position) {

            }
        }));
    }


}
