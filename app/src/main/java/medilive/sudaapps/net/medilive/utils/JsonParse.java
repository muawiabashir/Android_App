package medilive.sudaapps.net.medilive.utils;

/**
 * Created by muawia.ibrahim on 11/16/2015.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import medilive.sudaapps.net.medilive.model.SuggestGetSet;

public class JsonParse {

    public JsonParse() {
    }



    public List<SuggestGetSet> getParseJsonWCF(String sName) {
        List<SuggestGetSet> ListData = new ArrayList<SuggestGetSet>();
        try {
            String temp = sName.replace(" ", "%20");
            URL js = new URL("http://sudaapps.net/android/medilife/food_api/medi_serach.php?med_name="
                            + temp);
            URLConnection jc = js.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    jc.getInputStream()));
            String line = reader.readLine();
            JSONObject jsonResponse = new JSONObject(line);
            JSONArray jsonArray = jsonResponse.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject r = jsonArray.getJSONObject(i);
                ListData.add(new SuggestGetSet(r.getString("medi_id"), r
                        .getString("med_name")));
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return ListData;

    }

}
