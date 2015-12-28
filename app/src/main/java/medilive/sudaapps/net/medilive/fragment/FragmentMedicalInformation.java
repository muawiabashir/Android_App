package medilive.sudaapps.net.medilive.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import medilive.sudaapps.net.medilive.R;

/**
 * Created by Adil on 28/12/2015.
 */
public class FragmentMedicalInformation extends BasicV4Fragment {

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=(View)inflater.inflate(R.layout.fragment_navigation,container,false);
        return rootView;
    }

    CardView box1View,box2View,box3View,box4View;
    TextView box1TextView,box2TextView,box3TextView,box4TextView;
    @Override
    public void initViews() {
        super.initViews();
        box1View=(CardView)rootView.findViewById(R.id.box1);
        box2View=(CardView)rootView.findViewById(R.id.box2);
        box3View=(CardView)rootView.findViewById(R.id.box3);
        box4View=(CardView)rootView.findViewById(R.id.box4);
        box1TextView=(TextView)rootView.findViewById(R.id.box1_text);
        box2TextView=(TextView)rootView.findViewById(R.id.box2_text);
        box3TextView=(TextView)rootView.findViewById(R.id.box3_text);
        box4TextView=(TextView)rootView.findViewById(R.id.box4_text);
    }

    @Override
    public void initValuesInViews() {
        super.initValuesInViews();
        box1TextView.setText(getResources().getString(R.string.medical_directory));
        box2TextView.setText(getResources().getString(R.string.limits_signs));
        box3TextView.setText(getResources().getString(R.string.drug_information));
        box4TextView.setText(getResources().getString(R.string.conditions));
    }
}
