package medilive.sudaapps.net.medilive.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Vibrator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.model.IntervalModel;

/**
 * Created by Adil on 26/11/2015.
 */
public class SelectIntervalAdapter extends RecyclerView.Adapter<SelectIntervalAdapter.DialogViewHolder> {

    Context mContext;
    LayoutInflater inflater;
    ArrayList<IntervalModel> dataList = new ArrayList<>();
    ItemClickCallBack itemClickCallBack;

    SelectIntervalAdapter instance;

    public SelectIntervalAdapter(Context context, ItemClickCallBack itemClickCallBack,int start,int last) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.itemClickCallBack = itemClickCallBack;
        initList(start,last);
        instance = this;

    }

    @Override
    public DialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DialogViewHolder(inflater.inflate(R.layout.interval_picker_item, parent, false));
    }

    @Override
    public void onBindViewHolder(DialogViewHolder holder, int position) {
        holder.number.setText(dataList.get(position).getValue()+"");
        if(dataList.get(position).isHasFocus()){
            holder.itemParent.setCardBackgroundColor(mContext.getResources().getColor(R.color.highlighter_transparent));
            holder.number.setTextColor(mContext.getResources().getColor(R.color.counter_text_color));
        }else{
            holder.itemParent.setCardBackgroundColor(mContext.getResources().getColor(R.color.accentColor));
            holder.number.setTextColor(mContext.getResources().getColor(R.color.counter_text_color));
        }
    }

    private void initList(int start,int last) {
        for (int i=start;i<=last;i++){
            dataList.add(new IntervalModel(i,false));
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    int focusedItem = -1;

    class DialogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView number;
        CardView itemParent;


        public DialogViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.text_number);
            itemParent = (CardView) itemView.findViewById(R.id.item_parent);
            itemParent.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            Vibrator vb = (Vibrator)   mContext.getSystemService(Context.VIBRATOR_SERVICE);
            vb.vibrate(30);
            if (v.getId() == R.id.item_parent) {
                if(focusedItem<=-1) {
                    focusedItem=getLayoutPosition();
                    dataList.get(getLayoutPosition()).setHasFocus(true);
                    notifyItemChanged(getLayoutPosition());
                }else{
                    dataList.get(focusedItem).setHasFocus(false);
                    notifyItemChanged(focusedItem);
                    focusedItem=getLayoutPosition();
                    dataList.get(getLayoutPosition()).setHasFocus(true);
                    notifyItemChanged(getLayoutPosition());
                }
                itemClickCallBack.onItemClicked(dataList.get(getLayoutPosition()).getValue());
            }
        }
    }

    public interface ItemClickCallBack {
        public void onItemClicked(int value);
    }

    private int fetchAccentColor() {
        TypedValue typedValue = new TypedValue();

        TypedArray a = mContext.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorAccent });
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

}
