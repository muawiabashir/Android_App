package medilive.sudaapps.net.medilive.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.adapter.SelectGenderAdapter;

/**
 * Created by Adil on 08/01/2016.
 */
public class SelectGenderDialog extends Dialog {
    Context mContext;
    GenderSelectListener genderSelectCallBack;

    public SelectGenderDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    static SelectGenderDialog instance = null;

    public void setOnGenderSelectCallListener(GenderSelectListener onGenderSelectCallListener ){
        genderSelectCallBack=onGenderSelectCallListener;
    }
    public static SelectGenderDialog getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gender_picker_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        instance = this;
        initViews();
        initValues();
        initValuesInViews();
        setOnClickListener();
    }

    RecyclerView recyclerView;
    TextView okView, cancelView;

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        okView = (TextView) findViewById(R.id.ok);
        cancelView = (TextView) findViewById(R.id.cancel);
    }

    private void initValues() {

    }

    String selectedString;
    private void initValuesInViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemViewCacheSize(2);
        SelectGenderAdapter adapter = new SelectGenderAdapter(mContext);
        adapter.setOnItemClickCallBack(new SelectGenderAdapter.ItemClickCallBack() {
            @Override
            public void onItemClicked(String gender) {
                selectedString=gender;
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        okView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderSelectCallBack.onSelectingGender(selectedString);
                dismiss();
            }
        });
    }

    public interface GenderSelectListener {
        public void onSelectingGender(String value);
    }

}
