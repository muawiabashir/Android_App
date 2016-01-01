package medilive.sudaapps.net.medilive.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import medilive.sudaapps.net.medilive.model.DirectoryMedicine;

/**
 * Created by Adil on 30/12/2015.
 */
public class DirectoryMedicineParser {

    private final static String MEDI_ID_KEY = "medi_id";
    private final static String MEDI_NAME_KEY = "med_name";
    private final static String MEDI_ADDRESS_KEY = "address";
    private final static String MEDI_CITY_KEY = "city";
    private final static String MEDI_LNG_KEY = "lat";
    private final static String MEDI_LAT_KEY = "lng";



    private final static String MEDI_RESULT_KEY = "result";
    private final static String MEDI_RESULTS_KEY = "results";

    public static ArrayList<DirectoryMedicine> getDirectoryMedicinesList(JSONObject object) {
        ArrayList<DirectoryMedicine> listOfMedicines = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = object.getJSONArray(MEDI_RESULT_KEY);
            for (int i = 0; i < jsonArray.length(); i++) {
                listOfMedicines.add(parseSingle(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listOfMedicines;
    }

    public static DirectoryMedicine parseSingle(JSONObject jsonObject) throws JSONException {
        DirectoryMedicine medicine = new DirectoryMedicine();
        medicine.setMedicineAddress(jsonObject.getString(MEDI_ADDRESS_KEY));
        medicine.setMedicineCity(jsonObject.getString(MEDI_CITY_KEY));
        medicine.setMedicineID(jsonObject.getString(MEDI_ID_KEY));
        medicine.setMedicineName(jsonObject.getString(MEDI_NAME_KEY));
        return medicine;
    }

    public static ArrayList<DirectoryMedicine> getDirectoryMedicinesListWithDirections(JSONObject object) {
        ArrayList<DirectoryMedicine> listOfMedicines = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = object.getJSONArray(MEDI_RESULTS_KEY);
            for (int i = 0; i < jsonArray.length(); i++) {
                listOfMedicines.add(parseSingleWithDirections(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listOfMedicines;
    }

    public static DirectoryMedicine parseSingleWithDirections(JSONObject jsonObject) throws JSONException {
        DirectoryMedicine medicine = new DirectoryMedicine();
        medicine.setMedicineAddress(jsonObject.getString(MEDI_ADDRESS_KEY));
        medicine.setMedicineCity(jsonObject.getString(MEDI_CITY_KEY));
        medicine.setMedicineID(jsonObject.getString(MEDI_ID_KEY));
        medicine.setMedicineName(jsonObject.getString(MEDI_NAME_KEY));
        medicine.setMedicineLat(jsonObject.getString(MEDI_LAT_KEY));
        medicine.setMedicineLng(jsonObject.getString(MEDI_LNG_KEY));
        return medicine;
    }

}
