package cm.gs.dojomaps;

import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import cm.gs.dojomaps.Providers.LocationProvider;


public class MapFragment extends Fragment {

    LocationProvider mLocation;

    cm.gs.dojomaps.models.Location lastLocation = new cm.gs.dojomaps.models.Location();

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inicializamos la vista
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mLocation = new LocationProvider(getActivity());
        //Inicializamos el map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        //Mapa asincrono
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //Esta parte sucede cuando el mapa esté cargado
                mLocation.getLastLocation(getActivity(),getContext()).addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        lastLocation.setLatitude(location.getLatitude());
                        lastLocation.setLongitude(location.getLongitude());
                        mLocation.addMarker(googleMap, lastLocation);
                    }
                });
            }
        });

        return view;
    }
}