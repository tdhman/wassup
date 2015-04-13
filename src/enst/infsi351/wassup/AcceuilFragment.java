package enst.infsi351.wassup;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    	Activity b = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_acceuil, container, false);
        //int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
        
        //String text = getResources().getStringArray(R.array.menu_array)[i];

//        TextView textView = (TextView) rootView.findViewById(R.id.textView1);
//        textView.setText(text);
//        getActivity().setTitle(text);
        
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        PagerAdapter adapter = new CustomAdapter(b);
        viewPager.setAdapter(adapter);
        
        
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return rootView;
    }
    @Override
    public void onMapReady(GoogleMap map) {
        LatLng Julien_Dore = new LatLng(48.894487, 2.393321);

        map.setMyLocationEnabled(true);
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Julien Dore")
                .snippet("Sam 11 Avr. 20:00 \n Zénith de Paris, Paris, France.\n A partir de 49 €.")
                .position(Julien_Dore));
    }
}
