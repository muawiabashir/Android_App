package medilive.sudaapps.net.medilive.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import java.util.ArrayList;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.adapter.Expandable_List_Adapter_LimitsSigns;
import medilive.sudaapps.net.medilive.model.Expandable_items_Limits_Signs;
import medilive.sudaapps.net.medilive.model.Expandable_items_Limits_Signs_Header;

/**
 * Created by muawia.ibrahim on 12/23/2015.
 */
public class LimitsSigns extends FragmentActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{


    private SearchView search;
    private Expandable_List_Adapter_LimitsSigns listAdapter;
    private ExpandableListView myList;
    private ArrayList<Expandable_items_Limits_Signs_Header> continentList = new ArrayList<Expandable_items_Limits_Signs_Header>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limits_signs);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(LimitsSigns.this);
        search.setOnCloseListener(this);

        // display the list
        displayList();
        // expand all Groups
        expandAll();



    }

    private void loadSomeData() {
        ArrayList<Expandable_items_Limits_Signs> limits_signs_List = new ArrayList<Expandable_items_Limits_Signs>();


        Expandable_items_Limits_Signs limits_signs = new Expandable_items_Limits_Signs("Red blood cells (RBC)", "4.6–6.2 million/mm3 4.2–5.4", "4.6–6.2 million/mm3 4.2–5.4 million/mm3");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Hemoglobin", "13.5–18 g/dL", "12–16 g/dL");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Hematocrit", "40–54%", "38–47%");
        limits_signs_List.add(limits_signs);

        limits_signs = new Expandable_items_Limits_Signs("Mean corpuscular volume (MCV)", "76–100 (micrometer)3", "76–100 (micrometer)3");
        limits_signs_List.add(limits_signs);


        limits_signs = new Expandable_items_Limits_Signs("Mean corpuscular hemoglobin (MCH)", "27–33 picogram", "27–33 picogram");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Mean corpuscular hemoglobin concentration (MCHC)", "33–37 g/dL", "33–37 g/dL");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Erythrocyte sedimentation rate (ESR)", "≤20 mm/hr", "≤30 mm/hr");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Leukocytes (WBC)", "5000–10,000/mm3", "5000–10,000/mm3");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Neutrophils", "54–75% (3000–7500/mm3)", "54–75% (3000–7500/mm3)");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Bands", "3–8% (150–700/mm3)", "3–8% (150–700/mm3)");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Eosinophils", "1–4% (50–400/mm3)", "1–4% (50–400/mm3)");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Basophils", "0–1% (25–100/mm3)", "0–1% (25–100/mm3)");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Monocytes", "2–8% (100–500/mm3)", "2–8% (100–500/mm3)");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Lymphocytes", "25–40% (1500–4500/mm3)", "25–40% (1500–4500/mm3)");
        limits_signs_List.add(limits_signs);

        limits_signs = new Expandable_items_Limits_Signs("T lymphocytes", "60–80% of lymphocytes", "60–80% of lymphocytes");
        limits_signs_List.add(limits_signs);

        limits_signs = new Expandable_items_Limits_Signs("B lymphocytes", "10–20% of lymphocytes", "10–20% of lymphocytes");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Platelets", "150,000–450,000/mm3", "150,000–450,000/mm3");
        limits_signs_List.add(limits_signs);


        limits_signs = new Expandable_items_Limits_Signs("Prothrombin time (PT)", "9.6–11.8 sec", "9.5–11.3 sec");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Partial thromboplastin time (PTT)", "30–45 sec", "30–45 sec");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Bleeding time (duke)", "1–3 min", "1–3 min");
        limits_signs_List.add(limits_signs);

        Expandable_items_Limits_Signs_Header continent = new Expandable_items_Limits_Signs_Header("HEMATOLOGIC", limits_signs_List);
        continentList.add(continent);

        limits_signs_List = new ArrayList<Expandable_items_Limits_Signs>();


        limits_signs = new Expandable_items_Limits_Signs("Sodium", "135–145 mEq/L", "135–145 mEq/L");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Potassium", "3.5–5.0 mEq/L", "3.5–5.0 mEq/L");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Chloride", "95–105 mEq/L", "95–105 mEq/L");
        limits_signs_List.add(limits_signs);


        limits_signs = new Expandable_items_Limits_Signs("Bicarbonate (HCO3)", "19–25 mEq/L", "19–25 mEq/L");
        limits_signs_List.add(limits_signs);

        limits_signs = new Expandable_items_Limits_Signs("Total calcium", "9–11 mg/dL or 4.5–5.5 mEq/L", "9–11 mg/dL or 4.5–5.5 mEq/L");
        limits_signs_List.add(limits_signs);

        limits_signs = new Expandable_items_Limits_Signs("Ionized calcium", "4.2–5.4 mg/dL or 2.1–2.6 mEq/L", "4.2–5.4 mg/dL or 2.1–2.6 mEq/L");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Phosphorus/phosphate", "2.4–4.7 mg/dL", "42.4–4.7 mg/dL");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Magnesium", "1.8–3.0 mg/dL or 1.5–2.5 mEq/L", "1.8–3.0 mg/dL or 1.5–2.5 mEq/L");
        limits_signs_List.add(limits_signs);

        limits_signs = new Expandable_items_Limits_Signs("Glucose", "65–99 mg/dL", "65–99 mg/dL");
        limits_signs_List.add(limits_signs);

        limits_signs = new Expandable_items_Limits_Signs("Osmolality", "285–310 mOsm/kg", "285–310 mOsm/kg");
        limits_signs_List.add(limits_signs);

        limits_signs = new Expandable_items_Limits_Signs("Ammonia (NH3)", "10–80 mcg/dL", "10–80 mcg/dL");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Amylase)", "≤130 U/L", "≤130 U/L");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Creatine phosphokinase total (CK, CPK))", "<150 U/L", "<150 U/L");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Creatine kinase isoenzymes, MB fraction", ">5% in MI", ">5% in MI");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Lactic dehydrogenase (LDH)", "50–150 U/L", "50–150 U/L");
        limits_signs_List.add(limits_signs);

        limits_signs = new Expandable_items_Limits_Signs("Protein, total", "6–8 g/d", "6–8 g/d");
        limits_signs_List.add(limits_signs);
        limits_signs = new Expandable_items_Limits_Signs("Albumin", "4–6 g/dL", "4–6 g/dL");
        limits_signs_List.add(limits_signs);


        continent = new Expandable_items_Limits_Signs_Header("CHEMISTRY", limits_signs_List);
        continentList.add(continent);

        continent = new Expandable_items_Limits_Signs_Header("HEPATIC", limits_signs_List);
        continentList.add(continent);
    }
    // method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        }
    }

    // method to expand all groups
    private void displayList() {

        // display the list
        loadSomeData();

        // get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.exp_list);
        // create the adapter by passing your ArrayList data
        listAdapter = new Expandable_List_Adapter_LimitsSigns(LimitsSigns.this, continentList);
        // attach the adapter to the list
        myList.setAdapter(listAdapter);

    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }
}
