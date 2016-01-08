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

        //********************Chemistry

        ArrayList<Expandable_items_Limits_Signs> chemi = new ArrayList<Expandable_items_Limits_Signs>();
        Expandable_items_Limits_Signs chemi1 = new Expandable_items_Limits_Signs("Sodium", "135–145 mEq/L", "135–145 mEq/L");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Potassium", "3.5–5.0 mEq/L", "3.5–5.0 mEq/L");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Chloride", "95–105 mEq/L", "95–105 mEq/L");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Bicarbonate (HCO3)", "19–25 mEq/L", "19–25 mEq/L");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Total calcium", "9–11 mg/dL or 4.5–5.5 mEq/L", "9–11 mg/dL or 4.5–5.5 mEq/L");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Ionized calcium", "4.2–5.4 mg/dL or 2.1–2.6 mEq/L", "4.2–5.4 mg/dL or 2.1–2.6 mEq/L");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Phosphorus/phosphate", "2.4–4.7 mg/dL", "2.4–4.7 mg/dL");



        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Magnesium", "1.8–3.0 mg/dL or 1.5–2.5 mEq/L", "1.8–3.0 mg/dL or 1.5–2.5 mEq/L");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Glucose", "65–99 mg/dL", "65–99 mg/dL");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Osmolality", "285–310 mOsm/kg", "285–310 mOsm/kg");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Ammonia (NH3)", "10–80 mcg/dL", "10–80 mcg/dL");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Amylase", "≤130 U/L", "≤130 U/L");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Creatine phosphokinase total (CK, CPK)", "<150 U/L", "<150 U/L");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Creatine kinase isoenzymes, MB fraction", ">5% in MI", ">5% in MI");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Lactic dehydrogenase (LDH)", "50–150 U/L", "50–150 U/L");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Protein, total", "6–8 g/d", "6–8 g/d");
        chemi.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Albumin", "4–6 g/dL", "4–6 g/dL");
        chemi.add(chemi1);

        Expandable_items_Limits_Signs_Header chem = new Expandable_items_Limits_Signs_Header("CHEMISTRY", chemi);
        continentList.add(chem);
        //********************HEPATIC

        ArrayList<Expandable_items_Limits_Signs> HEPATIC = new ArrayList<Expandable_items_Limits_Signs>();
        Expandable_items_Limits_Signs HEPATIC1 = new Expandable_items_Limits_Signs("AST", "8–46 U/L", "7–34 U/L");
        HEPATIC.add(HEPATIC1);
        HEPATIC1 = new Expandable_items_Limits_Signs("ALT", "10–30 IU/mL", "10–30 IU/mL");
        HEPATIC.add(HEPATIC1);
        chemi1 = new Expandable_items_Limits_Signs("Total bilirubin", "0.3–1.2 mg/dL", "0.3–1.2 mg/dL");
        HEPATIC.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Conjugated bilirubin", "0.0–0.2 mg/dL", "0.0–0.2 mg/dL");
        HEPATIC.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Unconjugated (indirect) bilirubin", "0.2–0.8 mg/dL", "0.2–0.8 mg/dL");
        HEPATIC.add(chemi1);
        chemi1 = new Expandable_items_Limits_Signs("Alkaline phosphatase", "20–90 U/L", "20–90 U/L");
        HEPATIC.add(chemi1);

        Expandable_items_Limits_Signs_Header HEPATIC2 = new Expandable_items_Limits_Signs_Header("HEPATIC", HEPATIC);
        continentList.add(HEPATIC2);

//**********************************************RENAL


        ArrayList<Expandable_items_Limits_Signs> RENAL = new ArrayList<Expandable_items_Limits_Signs>();
        Expandable_items_Limits_Signs RENAL1 = new Expandable_items_Limits_Signs("BUN", "6–20 mg/dL", "6–20 mg/dL");
        RENAL.add(RENAL1);
        RENAL1 = new Expandable_items_Limits_Signs("Creatinine", "0.6–1.3 mg/dL", "0.5–1.0 mg/dL");
        RENAL.add(RENAL1);
        RENAL1 = new Expandable_items_Limits_Signs("Uric acid", "4.0–8.5 mg/dL", "2.7–7.3 mg/dL");
        RENAL.add(RENAL1);



        Expandable_items_Limits_Signs_Header RENAL2 = new Expandable_items_Limits_Signs_Header("RENAL", RENAL);
        continentList.add(RENAL2);

//**********************************************ARTERIAL BLOOD GASES


        ArrayList<Expandable_items_Limits_Signs> ARTERIAL  = new ArrayList<Expandable_items_Limits_Signs>();
        Expandable_items_Limits_Signs ARTERIAL1 = new Expandable_items_Limits_Signs("pH", "7.35–7.45", "7.35–7.45");
        ARTERIAL.add(ARTERIAL1);
        ARTERIAL1 = new Expandable_items_Limits_Signs("Po2", "80–100 mm Hg", "80–100 mm Hg");
        ARTERIAL.add(ARTERIAL1);
        ARTERIAL1 = new Expandable_items_Limits_Signs("Pco2", "35–45 mm Hg", "35–45 mm Hg");
        ARTERIAL.add(ARTERIAL1);
        ARTERIAL1 = new Expandable_items_Limits_Signs("O2 saturation", "95–97%", "95–97%");
        ARTERIAL.add(ARTERIAL1);
        ARTERIAL1 = new Expandable_items_Limits_Signs("Base excess", "+2–(-2)", "+2–(-2)");
        ARTERIAL.add(ARTERIAL1);
        ARTERIAL1 = new Expandable_items_Limits_Signs("Bicarbonate (HCO3-)", "22–26 mEq/L", "22–26 mEq/L");
        ARTERIAL.add(ARTERIAL1);



        Expandable_items_Limits_Signs_Header ARTERIAL2 = new Expandable_items_Limits_Signs_Header("ARTERIAL BLOOD GASES", ARTERIAL);
        continentList.add(ARTERIAL2);

        //**********************************************URINE TESTS


        ArrayList<Expandable_items_Limits_Signs> URINE  = new ArrayList<Expandable_items_Limits_Signs>();
        Expandable_items_Limits_Signs URINE1 = new Expandable_items_Limits_Signs("pH", "4.5–8.0", "4.5–8.0");
        URINE.add(URINE1);
        URINE1 = new Expandable_items_Limits_Signs("Specific gravity", "1.010–1.025", "1.010–1.025");
        URINE.add(URINE1);
        Expandable_items_Limits_Signs_Header URINE2 = new Expandable_items_Limits_Signs_Header("URINE TESTS", URINE);
        continentList.add(URINE2);

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
