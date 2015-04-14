package enst.infsi351.wassup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AcceuilFragment extends Fragment implements OnMapReadyCallback{
	public static final String ARG_FRAGMENT_NUMBER = "acceuil_number";
	
	private static View rootView;
	
    public AcceuilFragment() {
        // Empty constructor required for fragment subclasses
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
    	
        try {
        	rootView = inflater.inflate(R.layout.fragment_acceuil, container, false);
        	
        	SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null)
            	mapFragment.getMapAsync(this);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        
        return rootView;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SupportMapFragment f = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        if (f != null) 
            getFragmentManager().beginTransaction().remove(f).commit();
    }
    
    @Override
    public void onMapReady(GoogleMap map) {
    	LatLng curLocation = new LatLng(48.826245, 2.346392);
        LatLng Julien_Dore = new LatLng(48.894487, 2.393321);
        LatLng Julien_Dore_2 = new LatLng(48.822353, 2.340330);
        LatLng Julien_Dore_3 = new LatLng(48.832920, 2.337069);
        LatLng Julien_Dore_4 = new LatLng(48.836987, 2.352003);
        LatLng Julien_Dore_5 = new LatLng(48.829925, 2.350458);
        LatLng Julien_Dore_6 = new LatLng(48.819076, 2.360157);
        
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(curLocation, 12));

        map.addMarker(new MarkerOptions()
                .title("Julien Dore")
                .snippet("Sam 11 Avr. 20:00 \n Zénith de Paris, Paris, France.\n A partir de 49 euros.")
                .position(Julien_Dore));
        

       map.addMarker(new MarkerOptions()
        .title("Julien Dore")
        .snippet("Sam 11 Avr. 20:00 \n Zénith de Paris.\n A partir de 49 euros.")
        .position(Julien_Dore_2));
        map.addMarker(new MarkerOptions()
        .title("Julien Dore")
        .snippet("Sam 11 Avr. 20:00 \n Zénith de Paris.\n A partir de 49 euros.")
        .position(Julien_Dore_3));
        map.addMarker(new MarkerOptions()
        .title("Julien Dore")
        .snippet("Sam 11 Avr. 20:00 \n Zénith de Paris.\n A partir de 49 euros.")
        .position(Julien_Dore_4));
        map.addMarker(new MarkerOptions()
        .title("Julien Dore")
        .snippet("Sam 11 Avr. 20:00 \n Zénith de Paris.\n A partir de 49 euros.")
        .position(Julien_Dore_5));
        map.addMarker(new MarkerOptions()
        .title("Julien Dore")
        .snippet("Sam 11 Avr. 20:00 \n Zénith de Paris.\n A partir de 49 euros.")
        .position(Julien_Dore_6));
    }
}
