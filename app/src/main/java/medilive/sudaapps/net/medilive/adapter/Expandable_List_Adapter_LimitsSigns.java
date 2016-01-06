package medilive.sudaapps.net.medilive.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.model.Expandable_items_Limits_Signs;
import medilive.sudaapps.net.medilive.model.Expandable_items_Limits_Signs_Header;

/**
 * Created by muawia.ibrahim on 1/6/2016.
 */
public class Expandable_List_Adapter_LimitsSigns extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Expandable_items_Limits_Signs_Header> headerslist;
    private ArrayList<Expandable_items_Limits_Signs_Header> originalList;

    public Expandable_List_Adapter_LimitsSigns(Context context, ArrayList<Expandable_items_Limits_Signs_Header> headerslist) {
        this.context = context;
        this.headerslist = new ArrayList<Expandable_items_Limits_Signs_Header>();
        this.headerslist.addAll(headerslist);
        this.originalList = new ArrayList<Expandable_items_Limits_Signs_Header>();
        this.originalList.addAll(headerslist);

    }

    @Override
    public int getGroupCount() {
        return headerslist.size();
    }

    @Override
    public int getChildrenCount(int i) {
        ArrayList<Expandable_items_Limits_Signs> expandable_items_limits_signses = headerslist.get(i).getExpandable_items_Limits_Signs_List();

        return expandable_items_limits_signses.size();
    }

    @Override
    public Object getGroup(int i) {
        return headerslist.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Expandable_items_Limits_Signs> expandable_items_limits_signses = headerslist.get(groupPosition).getExpandable_items_Limits_Signs_List();

        return expandable_items_limits_signses.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Expandable_items_Limits_Signs_Header expandable_items_limits_signs_header = (Expandable_items_Limits_Signs_Header) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_layout, null);

        }

        TextView heading = (TextView) convertView.findViewById(R.id.heading);
        heading.setText(expandable_items_limits_signs_header.getName().trim());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Expandable_items_Limits_Signs expandable_items_limits_signs = (Expandable_items_Limits_Signs) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_layout, null);
        }
        TextView test = (TextView) convertView.findViewById(R.id.test);
        TextView men = (TextView) convertView.findViewById(R.id.men);
        TextView women = (TextView) convertView.findViewById(R.id.women);
        test.setText(expandable_items_limits_signs.getTest().trim());
        men.setText(expandable_items_limits_signs.getMen().trim());
        women.setText(expandable_items_limits_signs.getWomen().trim());
      //  convertView.setBackgroundColor(childPosition % 2 == 0 ? Color.GRAY : Color.WHITE);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void filterData(String query) {
        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(headerslist.size()));
        headerslist.clear();

        if (query.isEmpty()) {
            headerslist.addAll(originalList);
        } else {
            for (Expandable_items_Limits_Signs_Header continent : originalList) {
                ArrayList<Expandable_items_Limits_Signs> countryList = continent.getExpandable_items_Limits_Signs_List();
                ArrayList<Expandable_items_Limits_Signs> newList = new ArrayList<Expandable_items_Limits_Signs>();
                for (Expandable_items_Limits_Signs country : countryList) {
                    if (country.getTest().toLowerCase().contains(query) || country.getMen().toLowerCase().contains(query) || country.getWomen().toLowerCase().contains(query)) {
                        newList.add(country);
                    }
                }
                if (newList.size() > 0) {
                    Expandable_items_Limits_Signs_Header nContinent = new Expandable_items_Limits_Signs_Header(continent.getName(), newList);
                    headerslist.add(nContinent);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(headerslist.size()));
        notifyDataSetChanged();
    }
}
