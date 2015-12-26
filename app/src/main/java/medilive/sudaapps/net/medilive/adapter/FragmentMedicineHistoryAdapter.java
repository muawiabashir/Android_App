package medilive.sudaapps.net.medilive.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.model.MedicineSchedule;

/**
 * Created by Adil on 26/12/2015.
 */
public class FragmentMedicineHistoryAdapter extends RecyclerView.Adapter<FragmentMedicineHistoryAdapter.ViewHolder> {

    private static final String TAG = "FragmentMedicineHistoryAdapter";
    private LayoutInflater inflater;
    private static Context mContext;
    private static ArrayList<MedicineSchedule> dataList = new ArrayList<>();


    private static boolean fileDownloaded = false;
    public static Uri externalPath;
    private static FragmentMedicineHistoryAdapter instance;

    public FragmentMedicineHistoryAdapter(ArrayList<MedicineSchedule> list, Context context) {
        dataList = list;
        mContext = context;
        instance = this;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_medicine_schedule, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void update(ArrayList<MedicineSchedule> list){
        dataList=list;
        notifyDataSetChanged();
    }
    public void notifyItemRemove(int position){
        notifyItemRemoved(position);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MedicineSchedule current = dataList.get(position);
        holder.medQuantityView.setText("Take "+current.getQuantity() +" medicine.");
        holder.medNameView.setText(current.getMedName());
        holder.medDozeView.setText(current.getDosage());
        holder.medCommentView.setText(current.getComment());

    }

    public ArrayList<MedicineSchedule> getDataList() {
        return dataList;
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView medDeleteView;
        TextView medNameView,medDozeView,medCommentView,medQuantityView;
        public ViewHolder(View v) {
            super(v);
            medDeleteView=(ImageView)v.findViewById(R.id.delete);
            medCommentView=(TextView)v.findViewById(R.id.comment);
            medDozeView=(TextView)v.findViewById(R.id.doze);
            medNameView=(TextView)v.findViewById(R.id.title);
            medQuantityView=(TextView)v.findViewById(R.id.quantity);
            medDeleteView.setVisibility(View.GONE);

            setClickListeners();
        }

        private void setClickListeners(){
//            medDeleteView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    SQLiteHandler sqLiteHandler= new SQLiteHandler(mContext);
//                    MedicineSchedule medicineSchedule= dataList.get(getLayoutPosition());
//                    medicineSchedule.setIsScheduleEnd(1);
//                    sqLiteHandler.updateSchedules(medicineSchedule);
//                    dataList.remove(getLayoutPosition());
//                    instance.notifyItemRemove(getLayoutPosition());
//                }
//            });
        }

    }


}
