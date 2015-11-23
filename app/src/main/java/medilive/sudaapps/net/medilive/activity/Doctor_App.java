package medilive.sudaapps.net.medilive.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import medilive.sudaapps.net.medilive.R;

/**
 * Created by muawia.ibrahim on 10/12/2015.
 */
public class Doctor_App extends FragmentActivity implements Spinner.OnItemSelectedListener {
    public static final String DATA_URL = "http://sudaapps.net/android/medilife/food_api/spinnerfill.php";

    //Tags used in the JSON String
    public static final String TAG_USERNAME = "doc_sp";
    public static final String TAG_NAME = "doc_name";
    public static final String TAG_COURSE = "time_av";
    public static final String TAG_SESSION = "address";
    //JSON array name
    public static final String JSON_ARRAY = "result";
    //Declaring an Spinner
    //JSON Array
    private JSONArray result;
    private Spinner spinner,doc_name;
    private TextView tim_av;

    //An ArrayList for Spinner Items
    private ArrayList<String> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_app);
        students = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.spec);
        doc_name=(Spinner)findViewById(R.id.doc_name);
        tim_av=(TextView)findViewById(R.id.tim_av);
        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        spinner.setOnItemSelectedListener(this);

    
        getData();

    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject j = null;
                try {
                    //Parsing the fetched Json String to JSON Object
                    j = new JSONObject(response);

                    //Storing the Array of JSON String to our JSON Array
                    result = j.getJSONArray(JSON_ARRAY);

                    //Calling method getStudents to get the students from the JSON Array
                    getStudents(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    private void getStudents(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                students.add(json.getString(TAG_USERNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(Doctor_App.this, android.R.layout.simple_spinner_dropdown_item, students));
    }

    //Method to get student name of a particular position
  //private String getName(int position){
   //    String doc_name="";
   //    try {
            //Getting object of given index
   //      JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
   //        doc_name = json.getString(TAG_NAME);
   //    } catch (JSONException e) {
    //       e.printStackTrace();
   //    }
        //Returning the name
   //    return doc_name;
// }
    //Doing the same with this method as we did with getName()
 ///  private String getTime_av(int position){
   // String Time_av="";

   //    try {
    //       JSONObject json = result.getJSONObject(position);
       //    Time_av = json.getString(TAG_COURSE);
     //  } catch (JSONException e) {
     //      e.printStackTrace();
    //    }
     //   return Time_av;
    //}

    //Doing the same with this method as we did with getName()
  //  private String getSession(int position){
  //      String session="";
  //      try {
  //          JSONObject json = result.getJSONObject(position);
  //          session = json.getString(TAG_SESSION);
  //      } catch (JSONException e) {
  //          e.printStackTrace();
 //       }
 //       return session;
 //   }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
    // tim_av.setText(getTime_av(position));
      // doc_name.setPrompt(getName(position));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
