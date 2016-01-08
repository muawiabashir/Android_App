package medilive.sudaapps.net.medilive.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.activity.Directory;
import medilive.sudaapps.net.medilive.activity.LimitsSigns;

/**
 * Created by Adil on 28/12/2015.
 */
public class FragmentMedicalInformation extends BasicV4Fragment implements View.OnClickListener{

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
        box1View.setCardBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        box2View.setCardBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
        box3View.setCardBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        box4View.setCardBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
    }

    @Override
    public void setOnViewClickListener() {
        super.setOnViewClickListener();
        box1View.setOnClickListener(this);
        box2View.setOnClickListener(this);
        box3View.setOnClickListener(this);
        box4View.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.box1: {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.view_click));
                startActivity(new Intent(getActivity(), Directory.class));
                break;
            }
            case R.id.box2: {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.view_click));
                //open here limites and signs screen
//                startActivity(new Intent(getActivity(), MedicineSchedulesList.class));
                startActivity(new Intent(getActivity(), LimitsSigns.class));
                break;
            }
            case R.id.box3: {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.view_click));
                //open here drug information screen
//                startActivity(new Intent(getActivity(), MedicineSchedulesList.class));
                break;
            }
            case R.id.box4: {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.view_click));
                //open here conditions screen
//                startActivity(new Intent(getActivity(), MedicineSchedulesList.class));
                break;
            }

        }

    }
}
