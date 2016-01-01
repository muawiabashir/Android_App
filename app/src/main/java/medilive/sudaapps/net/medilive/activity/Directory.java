package medilive.sudaapps.net.medilive.activity;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.listener.ApiResponseCallback;
import medilive.sudaapps.net.medilive.model.DirectoryMedicine;
import medilive.sudaapps.net.medilive.model.DirectoryMedicineClusterItem;
import medilive.sudaapps.net.medilive.parser.DirectoryMedicineParser;
import medilive.sudaapps.net.medilive.utils.ApiCallManager;

/**
 * Created by muawia.ibrahim on 8/25/2015.
 */
public class Directory extends AppCompatBaseActivity implements ApiResponseCallback.ApiResponseCallbackListener, OnMapReadyCallback{
    private static final String GET_MEDICINES_LIST_URL="http://sudaapps.net/android/medilife/food_api/get_All_medicin.php";
    private static final String GET_MEDICINES_DIRECTION_LIST_URL="http://sudaapps.net/android/medilife/food_api/medi_serach.php?med_name=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        ApiCallManager.makeGetRequestForJSON(getApplicationContext(),GET_MEDICINES_LIST_URL,null,this);
    }

    SupportMapFragment mapFragment;
    AutoCompleteTextView searchView;
    ArrayList<String> listOfMedicineNames=new ArrayList<>();
    @Override
    public void initViews() {
        super.initViews();
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        searchView=(AutoCompleteTextView)findViewById(R.id.directory_search_view);
    }


    @Override
    public void initValues() {
        super.initValues();
    }

    @Override
    public void initValuesInViews() {
        super.initValuesInViews();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void setOnViewClickListener() {
        super.setOnViewClickListener();
        searchView.setOnEditorActionListener(new AutoCompleteTextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    makeDirectionsApiCall();
                }
                return false;
            }
        });
    }

    private void makeDirectionsApiCall(){
        ApiCallManager.makeGetRequestForJSON(getApplicationContext(), GET_MEDICINES_DIRECTION_LIST_URL + searchView.getText(), null, this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void onSuccessFullResponse(Object jsonObject, String url) {
        if(url.equals(GET_MEDICINES_LIST_URL)){
            updateMedicineAdapter((JSONObject)jsonObject);
        }else if(url.contains(GET_MEDICINES_DIRECTION_LIST_URL)){
            setUpClusterer(DirectoryMedicineParser.getDirectoryMedicinesListWithDirections((JSONObject)jsonObject));
        }
    }

    ClusterManager<DirectoryMedicineClusterItem> mClusterManager;
    private void setUpClusterer(ArrayList<DirectoryMedicine> listOfMedicines){
        // Position the map.
        mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myPosition.latitude, myPosition.longitude), 2));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<DirectoryMedicineClusterItem>(this, mapFragment.getMap());

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mapFragment.getMap().setOnCameraChangeListener(mClusterManager);
        mapFragment.getMap().setOnMarkerClickListener(mClusterManager);

        List<DirectoryMedicineClusterItem> list=new ArrayList<>();
        for(DirectoryMedicine medicine:listOfMedicines){
//            for (int i = 0; i < 10; i++) {
//                double offset = i / 60d;
//                list.add(new DirectoryMedicineClusterItem(myPosition.latitude + offset, myPosition.longitude + offset));
//            }
            list.add(new DirectoryMedicineClusterItem(Double.parseDouble(medicine.getMedicineLat()), Double.parseDouble(medicine.getMedicineLng())));
        }

        mClusterManager.addItems(list);
        // Add cluster items (markers) to the cluster manager.
//         addItems(listOfMedicines,mClusterManager);
    }



    private void updateMedicineAdapter(JSONObject jsonObject){
        ArrayList<DirectoryMedicine> searchMedicinesList=DirectoryMedicineParser.getDirectoryMedicinesList(jsonObject);
        for(DirectoryMedicine medicine:searchMedicinesList){
            listOfMedicineNames.add(medicine.getMedicineName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,listOfMedicineNames);

        searchView.setThreshold(1);//will start working from first character
        searchView.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
    }
    @Override
    public void onErrorResponse(VolleyError error, String url) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMyLocationEnabled(true);
        loadMyLocation(googleMap);
    }

    LatLng myPosition;
    private void loadMyLocation(GoogleMap googleMap) {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            setMyLocation(location,googleMap);
        }else{
            location = googleMap.getMyLocation();
            if(location!=null) {
                setMyLocation(location, googleMap);
            }
            else{
                Toast.makeText(Directory.this, "Switch on Your GPS.", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void setMyLocation(Location location,GoogleMap googleMap){
        double latitude = location.getLatitude();
        // Getting longitude of the current location
        double longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);
        myPosition = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().title("You are here.").position(myPosition));
        setMyLocationCamera(location,googleMap);
    }
    private void setMyLocationCamera(Location location,GoogleMap googleMap){
        CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
    }
}