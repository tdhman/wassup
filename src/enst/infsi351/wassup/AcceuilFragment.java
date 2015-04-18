package enst.infsi351.wassup;


import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AcceuilFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "acceuil_number";
	
	private static View rootView;
	private GoogleMap map;
	private HashMap<Marker, MyMarker> mMarkersHashMap;
    private ArrayList<MyMarker> mMyMarkersArray = new ArrayList<MyMarker>();
	
    public AcceuilFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    public void initMarkers(){
    	// Initialize the HashMap for Markers and MyMarker object
        mMarkersHashMap = new HashMap<Marker, MyMarker>();
        
        mMyMarkersArray.add(new MyMarker("Brasil", R.drawable.affiche_1, Double.parseDouble("-28.5971788"), Double.parseDouble("-52.7309824")));
        mMyMarkersArray.add(new MyMarker("United States", R.drawable.affiche_2, Double.parseDouble("33.7266622"), Double.parseDouble("-87.1469829")));
        mMyMarkersArray.add(new MyMarker("Canada", R.drawable.affiche_3, Double.parseDouble("51.8917773"), Double.parseDouble("-86.0922954")));
        mMyMarkersArray.add(new MyMarker("England", R.drawable.affiche_4, Double.parseDouble("52.4435047"), Double.parseDouble("-3.4199249")));
        mMyMarkersArray.add(new MyMarker("España", R.drawable.affiche_5, Double.parseDouble("41.8728262"), Double.parseDouble("-0.2375882")));
        mMyMarkersArray.add(new MyMarker("Portugal", R.drawable.affiche_6, Double.parseDouble("40.8316649"), Double.parseDouble("-4.936009")));
        mMyMarkersArray.add(new MyMarker("Deutschland", R.drawable.affiche_7, Double.parseDouble("51.1642292"), Double.parseDouble("10.4541194")));
    }
    
    private void plotMarkers(ArrayList<MyMarker> markers, LayoutInflater inflater, ViewGroup container)
    {
        if(markers.size() > 0)
        {
            for (MyMarker myMarker : markers)
            {

                // Create user marker with custom icon and other options
                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(myMarker.getmLatitude(), myMarker.getmLongitude()));
                //markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.pin_red);
                bm = Bitmap.createScaledBitmap(bm, 60, 60, false);
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(bm));

                Marker currentMarker = map.addMarker(markerOption);
                mMarkersHashMap.put(currentMarker, myMarker);

                map.setInfoWindowAdapter(new MarkerInfoWindowAdapter(inflater, container));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
    	
        try {
        	rootView = inflater.inflate(R.layout.fragment_acceuil, container, false);
        	
        	Button btnComparer = (Button) rootView.findViewById(R.id.btnComparer);
        	btnComparer.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), 
			    		      "Comparer les évenements", 
			    		      Toast.LENGTH_LONG).show();
				}
			});
        	//MapsInitializer.initialize(getActivity());
        	SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null){
            	map = mapFragment.getMap();
            }
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        
    	if (map != null){
    		initMarkers();
    		plotMarkers(mMyMarkersArray, inflater, container);
    		map.setOnMarkerClickListener(new OnMarkerClickListener() {						
				@Override
				public boolean onMarkerClick(Marker marker) {
					marker.showInfoWindow();
					return true;
				}
			});
    	} else
            Toast.makeText(getActivity(), "Unable to create Maps", Toast.LENGTH_SHORT).show();
        
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
	public void onResume() {
		super.onResume();
//		if (map == null)
//			map = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMap();
//		
//		initMarkers();
		//plotMarkers(mMyMarkersArray, inflater, container);
	}
    
    public class MarkerInfoWindowAdapter implements InfoWindowAdapter {
    	
    	private LayoutInflater inflater;
    	private ViewGroup container;
    	
    	public MarkerInfoWindowAdapter(LayoutInflater inflater, ViewGroup container)
        {
    		super();
    		this.inflater = inflater;
    		this.container = container;
        }

    	@Override
    	public View getInfoContents(Marker marker) {
    		// TODO Auto-generated method stub
    		return null;
    	}

    	@Override
    	public View getInfoWindow(Marker marker) {
    		View v  = inflater.inflate(R.layout.infowindow_layout, container, false);

            MyMarker myMarker = mMarkersHashMap.get(marker);

            ImageView markerIcon = (ImageView) v.findViewById(R.id.marker_icon);

            TextView markerLabel = (TextView)v.findViewById(R.id.marker_label);

            markerIcon.setImageResource(myMarker.getmIcon());

            markerLabel.setText(myMarker.getmLabel());

            return v;
    	}

    }

}