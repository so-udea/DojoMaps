package cm.gs.dojomaps.Providers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

public class LocationProvider {

    private FusedLocationProviderClient fusedLocationClient;

    public LocationProvider(Activity activity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
    }

    public Task<Location> getLastLocation(Activity activity, Context context) {
        if (ActivityCompat.checkSelfPermission(context
                , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context
                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                    , Manifest.permission.ACCESS_COARSE_LOCATION},44);
        }
        return fusedLocationClient.getLastLocation();
    }

    public void addMarker(GoogleMap googleMap, cm.gs.dojomaps.models.Location location) {
        LatLng lastLocation = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(lastLocation).title("Estoy acá"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 10));
    }

}