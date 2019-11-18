package com.example.paraggelies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.paraggelies.models.loginRespone;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class map extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {

    GoogleMap gmap;

    private static final String FINE_LOCATION=Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION=Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE=101;
    Location currentlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    Button pickLocButton;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        pickLocButton=findViewById(R.id.pickLocationButton);
        pickLocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);


    }

    private void fetchLastLocation() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_REQUEST_CODE);
    return;
    }
        Task<Location> task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location !=null){
                    currentlocation=location;
                    Toast.makeText(getApplicationContext(),currentlocation.getLatitude()+""+currentlocation.getLongitude(),Toast.LENGTH_LONG).show();
                    SupportMapFragment supportMapFragment=(SupportMapFragment)
                            getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(map.this);
                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        gmap=googleMap;

        gmap.setMyLocationEnabled(true);
        gmap.setOnMyLocationButtonClickListener(this);
        gmap.setOnMyLocationClickListener(this);

        gmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude+" : "+ latLng.longitude);
                gmap.clear();
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                gmap.addMarker(markerOptions);
            }
        });
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;

    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location.getLongitude()+" "+location.getLatitude(), Toast.LENGTH_LONG).show();
        sharedPreferences=getSharedPreferences("SaveShop", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("latitude", String.valueOf(location.getLatitude()));
        editor.putString("longtitude", String.valueOf(location.getLongitude()));
        editor.apply();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch(requestCode){
           case LOCATION_PERMISSION_REQUEST_CODE:if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
               fetchLastLocation();
           }
           break;
       }
    }
}