package medilive.sudaapps.net.medilive.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONObject;

import java.util.ArrayList;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.listener.ApiResponseCallback;
import medilive.sudaapps.net.medilive.model.DirectoryMedicine;
import medilive.sudaapps.net.medilive.parser.DirectoryMedicineParser;
import medilive.sudaapps.net.medilive.utils.ApiCallManager;
import medilive.sudaapps.net.medilive.utils.MapClusterItemRanderer;

/**
 * Created by muawia.ibrahim on 8/25/2015.
 */
public class Directory extends AppCompatBaseActivity implements ApiResponseCallback.ApiResponseCallbackListener, OnMapReadyCallback,ClusterManager.OnClusterClickListener<DirectoryMedicine>, ClusterManager.OnClusterInfoWindowClickListener<DirectoryMedicine>, ClusterManager.OnClusterItemClickListener<DirectoryMedicine>, ClusterManager.OnClusterItemInfoWindowClickListener<DirectoryMedicine>{
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
        searchView.setDropDownBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primaryColor)));
    }

    private void makeDirectionsApiCall(){
        ApiCallManager.makeGetRequestForJSON(getApplicationContext(), GET_MEDICINES_DIRECTION_LIST_URL + searchView.getText(), null, this);
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
            Log.v(this.getClass().getName(),url);
            setUpClusterer(DirectoryMedicineParser.getDirectoryMedicinesListWithDirections((JSONObject) jsonObject));
        }
    }

    ClusterManager<DirectoryMedicine> mClusterManager;
    private void setUpClusterer(ArrayList<DirectoryMedicine> listOfMedicines){
        mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myPosition.latitude, myPosition.longitude), 10));
        mClusterManager = new ClusterManager<DirectoryMedicine>(this, mapFragment.getMap());

        mapFragment.getMap().setOnCameraChangeListener(mClusterManager);
        mapFragment.getMap().setOnMarkerClickListener(mClusterManager);

        mClusterManager.setRenderer(new MapClusterItemRanderer(this, mapFragment.getMap(), mClusterManager));
        mClusterManager.addItems(listOfMedicines);
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
        Toast.makeText(Directory.this, "something went wrong.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMyLocationEnabled(true);
        loadMyLocation(googleMap);
    }

    LatLng myPosition;
    private void loadMyLocation(GoogleMap googleMap) {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        String provider = locationManager.getBestProvider(criteria, true);
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
        googleMap.addMarker(new MarkerOptions().title(getString(R.string.you_r_here)).position(myPosition)).showInfoWindow();
        setMyLocationCamera(location,googleMap);
    }
    private void setMyLocationCamera(Location location,GoogleMap googleMap){
        CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
    }

    @Override
    public boolean onClusterClick(Cluster<DirectoryMedicine> cluster) {
        return false;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<DirectoryMedicine> cluster) {

    }

    @Override
    public boolean onClusterItemClick(DirectoryMedicine directoryMedicine) {
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(DirectoryMedicine directoryMedicine) {

    }
}