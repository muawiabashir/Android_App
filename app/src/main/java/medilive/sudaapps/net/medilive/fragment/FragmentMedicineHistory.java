package medilive.sudaapps.net.medilive.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import medilive.sudaapps.net.medilive.R;

/**
 * Created by Phaedra on 7/16/2015.
 */
public class FragmentMedicineHistory extends BasicV4Fragment {

    View rootView;
    RecyclerView historyListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=(View)inflater.inflate(R.layout.fragment_medicine_history,container,false);
        return rootView;
    }

    @Override
    public void initViews() {
        super.initViews();
        historyListView=(RecyclerView)rootView.findViewById(R.id.recyclerView);
    }
    @Override
    public void initValuesInViews() {
        super.initValuesInViews();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        historyListView.setLayoutManager(linearLayoutManager);
    }
}
