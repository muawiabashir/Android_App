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
import medilive.sudaapps.net.medilive.adapter.SelectIntervalAdapter;

/**
 * Created by Adil on 26/11/2015.
 */
public class SelectIntervalDialog extends Dialog implements SelectIntervalAdapter.ItemClickCallBack{

    Context mContext;
    int selectNumber=0;
    int start=0;
    int last=0;
    NumberPickerCallBack callBack;
    private static SelectIntervalDialog instance=null;
    public SelectIntervalDialog(Context context, NumberPickerCallBack callBack,int start,int last) {
        super(context);
        this.callBack=callBack;
        this.mContext=context;
        this.selectNumber=start;
        this.start=start;
        this.last=last;
    }

    public static SelectIntervalDialog getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.interval_picker_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        instance=this;
        initViews();
        initValues();
        initValuesInViews();
        setOnClickListener();
    }

    RecyclerView recyclerView;
    TextView okView,cancelView;
    private void initViews(){
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        okView=(TextView)findViewById(R.id.ok);
        cancelView=(TextView)findViewById(R.id.cancel);
    }
    private void initValues(){

    }
    private void initValuesInViews(){
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(last-start<=-1)
            recyclerView.setItemViewCacheSize(1000);
        else
            recyclerView.setItemViewCacheSize(last - start);
        recyclerView.setAdapter(new SelectIntervalAdapter(mContext,this,start,last));
    }
    private void setOnClickListener(){
        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        okView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onSelectingValue(selectNumber);
                dismiss();
            }
        });
    }

    @Override
    public void onItemClicked(int selectedNumber) {
        this.selectNumber=selectedNumber;
    }

    public interface NumberPickerCallBack {
        public void onSelectingValue(int value);
    }
}
