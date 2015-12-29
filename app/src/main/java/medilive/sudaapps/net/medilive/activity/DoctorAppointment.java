package medilive.sudaapps.net.medilive.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
import medilive.sudaapps.net.medilive.app.AppConfig;

/**
 * Created by muawia.ibrahim on 10/12/2015.
 */
public class DoctorAppointment extends AppCompatActivity implements Spinner.OnItemSelectedListener {
    //Declaring an Spinner
    private Spinner spinner, spinner1;

    //An ArrayList for Spinner Items
    private ArrayList<String> students;
    private ArrayList<String> name_arraylist;
    private JSONArray doctors_name_arraylist;
    //JSON Array
    private JSONArray result;

    //TextViews to display details
    private TextView textViewName;
    private TextView textViewCourse;
    private TextView textViewSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_app);
        //Initializing the ArrayList
        //Initializing the ArrayList

//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff00ff")));
        students = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner2);
        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        spinner.setOnItemSelectedListener(this);
        spinner.setPrompt("اختار اخصائي");
        //Initializing TextViews
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewCourse = (TextView) findViewById(R.id.textViewCourse);
        textViewSession = (TextView) findViewById(R.id.textViewSession);

        //This method will fetch the data from the URL
        getData();
    }

    private void getData() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(AppConfig.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(AppConfig.JSON_ARRAY);
                            doctors_name_arraylist = j.getJSONArray(AppConfig.JSON_ARRAY);
                            //Calling method getStudents to get the students from the JSON Array
                            getDoctorNames(doctors_name_arraylist);
                            getSpeci(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    private void getSpeci(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                students.add(json.getString(AppConfig.TAG_DOC_SP));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(DoctorAppointment.this, android.R.layout.simple_spinner_dropdown_item, students));
    }

    private void getDoctorNames(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
//                name_arraylist.add(json.getString(AppConfig.TAG_DOC_NAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    //Method to get student name of a particular position
    private String getName(int position) {
        String name = "";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            name = json.getString(AppConfig.TAG_ADDRESS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }

    //Doing the same with this method as we did with getName()
    private String getCourse(int position) {
        String course = "";
        try {
            JSONObject json = result.getJSONObject(position);
            course = json.getString(AppConfig.TAG_DOC_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return course;
    }

    //Doing the same with this method as we did with getName()
    private String getSession(int position) {
        String session = "";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(AppConfig.TAG_DOC_TIME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }


    //this method will execute when we pic an item from the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item
//        textViewName.setText(getName(position));
        textViewName.setText(getCourse(position));
        textViewCourse.setText(getName(position));
        textViewSession.setText(getSession(position));
    }

    //When no item is selected this method would execute
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewName.setText("");
        textViewCourse.setText("");
        textViewSession.setText("");
    }
}
