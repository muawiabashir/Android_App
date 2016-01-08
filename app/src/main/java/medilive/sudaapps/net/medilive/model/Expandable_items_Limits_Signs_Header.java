package medilive.sudaapps.net.medilive.model;

import java.util.ArrayList;

/**
 * Created by muawia.ibrahim on 1/6/2016.
 */
public class Expandable_items_Limits_Signs_Header {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Expandable_items_Limits_Signs> getExpandable_items_Limits_Signs_List() {
        return Expandable_items_Limits_Signs_List;
    }

    public void setExpandable_items_Limits_Signs_List(ArrayList<Expandable_items_Limits_Signs> expandable_items_Limits_Signs_List) {
        Expandable_items_Limits_Signs_List = expandable_items_Limits_Signs_List;
    }

    private String name;
    private ArrayList<Expandable_items_Limits_Signs> Expandable_items_Limits_Signs_List = new ArrayList<Expandable_items_Limits_Signs>();

    public Expandable_items_Limits_Signs_Header(String name, ArrayList<Expandable_items_Limits_Signs> expandable_items_Limits_Signs_List) {
        this.name = name;
        Expandable_items_Limits_Signs_List = expandable_items_Limits_Signs_List;
    }
}
