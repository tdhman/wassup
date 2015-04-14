package enst.infsi351.wassup;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AcceuilFragment extends Fragment implements OnMapReadyCallback{
	public static final String ARG_FRAGMENT_NUMBER = "acceuil_number";
	ViewPager viewPager;
    public AcceuilFragment() {
        // Empty constructor required for fragment subclasses
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.fragment_acceuil, container, false);
        //int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
        
        //String text = getResources().getStringArray(R.array.menu_array)[i];

//        TextView textView = (TextView) rootView.findViewById(R.id.textView1);
//        textView.setText(text);
//        getActivity().setTitle(text);
                
        
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return rootView;
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
                .snippet("Sam 11 Avr. 20:00 \n Z�nith de Paris, Paris, France.\n A partir de 49 �.")
                .position(Julien_Dore));
        

       map.addMarker(new MarkerOptions()
        .title("Julien Dore")
        .snippet("Sam 11 Avr. 20:00 \n Z�nith de Paris.\n A partir de 49 �.")
        .position(Julien_Dore_2));
        map.addMarker(new MarkerOptions()
        .title("Julien Dore")
        .snippet("Sam 11 Avr. 20:00 \n Z�nith de Paris.\n A partir de 49 �.")
        .position(Julien_Dore_3));
        map.addMarker(new MarkerOptions()
        .title("Julien Dore")
        .snippet("Sam 11 Avr. 20:00 \n Z�nith de Paris.\n A partir de 49 �.")
        .position(Julien_Dore_4));
        map.addMarker(new MarkerOptions()
        .title("Julien Dore")
        .snippet("Sam 11 Avr. 20:00 \n Z�nith de Paris.\n A partir de 49 �.")
        .position(Julien_Dore_5));
        map.addMarker(new MarkerOptions()
        .title("Julien Dore")
        .snippet("Sam 11 Avr. 20:00 \n Z�nith de Paris.\n A partir de 49 �.")
        .position(Julien_Dore_6));
    }
}
