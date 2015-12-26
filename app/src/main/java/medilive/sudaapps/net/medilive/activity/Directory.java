package medilive.sudaapps.net.medilive.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import medilive.sudaapps.net.medilive.Category;
import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.ServiceHandler;
import medilive.sudaapps.net.medilive.adapter.SuggestionAdapter;

/**
 * Created by muawia.ibrahim on 8/25/2015.
 */
public class Directory extends FragmentActivity implements LocationListener {
    Spinner spinner, spinner1;
    AutoCompleteTextView text;
    GoogleMap googleMap;
    Context context;
    ServiceHandler db;
    private ArrayList<Category> categoriesList;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory);
        final AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.auto01);

        String search = acTextView.getText().toString().trim();


        acTextView.setAdapter(new SuggestionAdapter(this, search));
        acTextView.setThreshold(1);
        acTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String str = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(Directory.this, str, Toast.LENGTH_SHORT).show();
            }
        });

     //   setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
  //      if (!isGooglePlayServicesAvailable()) {
   //         finish();
   //     }
   //     if (googleMap == null) {
   //         googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
   //         if (googleMap != null) {
   ///             setUpMap();
     //       }
    //    }
    }


    @SuppressLint("NewApi")
    private void setUpMap() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);


        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        Location mylocation = locationManager.getLastKnownLocation(provider);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        double latitude = mylocation.getLatitude();
        double longitude = mylocation.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(20));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here"));

    }


    @SuppressLint("NewApi")
    @Override
    public void onLocationChanged(Location location) {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
          //      public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
           // public void onRequestPermissionsResult(int requestCode, String[] permissions,
          //                                         int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        Location mylocation = locationManager.getLastKnownLocation(provider);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        double latitude = mylocation.getLatitude();
        double longitude = mylocation.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(20));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here"));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
//            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }

    }
}