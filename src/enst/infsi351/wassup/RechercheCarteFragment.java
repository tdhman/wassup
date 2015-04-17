package enst.infsi351.wassup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.maps.GoogleMap;

public class RechercheCarteFragment extends Fragment{
	public static final String ARG_FRAGMENT_NUMBER = "recherche_par_carte_number";

	Spinner cityCmb;
	EditText km;
//	GoogleMap map;
	public final String[] CITY_LONGS = getResources().getStringArray(R.array.city_long);
	public final String[] CITY_LATS = getResources().getStringArray(R.array.city_lat);

	public final String[] EVENT_LONGS = getResources().getStringArray(R.array.event_long);
	public final String[] EVENT_LATS = getResources().getStringArray(R.array.event_lat);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_recherche_carte,
				container, false);

		// to select the city
		cityCmb = (Spinner) rootView.findViewById(R.id.cityCombobox);
		cityCmb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int i, long l) {
				// Your code here
				System.out.println(cityCmb.getSelectedItem());
				changeCity(cityCmb.getSelectedItemPosition());
			}

			public void onNothingSelected(AdapterView<?> adapterView) {
				return;
			}
		});

		// cityCmb.setSelection(0);

		// to enter the km
		km = (EditText) rootView.findViewById(R.id.nearMeTxt);

		km.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				/*
				 * When focus is lost check that the text field has valid
				 * values.
				 */
				if (!hasFocus) {
					System.out.println(km.getText());

					nearMe();
				}
			}
		});

		// map
//		SupportMapFragment mapFragment = (SupportMapFragment) getActivity()
//				.getSupportFragmentManager().findFragmentById(R.id.map);
//
//		mapFragment.getMapAsync(this);
		
		return rootView;
	}

	private void nearMe(){
//		//clear old markers
//		map.clear();
//		
//		//detect current location
//		map.setMyLocationEnabled(true);
//		Location myLocation = map.getMyLocation();
//		
//		if (myLocation!=null){
//			
//			//add makers to current Location
//			LatLng _me = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
//			
//			//map.moveCamera(CameraUpdateFactory.newLatLng(_me));
//		    map.addMarker(new MarkerOptions()
//	        .position(_me)
//	        .title("Vous êtes ici"));
//
//		    //Find the circle with the radius from "near me"
//		    CircleOptions circle=new CircleOptions();
//		    circle.center(_me);
//		    double radius = Double.parseDouble(km.getText().toString())*1000;
//		    circle.radius(radius);
//		    map.addCircle(circle);
//		    
//		    	
//		    //to calculate the bound of markers
//		    LatLngBounds.Builder builder = new LatLngBounds.Builder();
//		    
//		    //add the events inside the circle
//		    for (int i=0;i<EVENT_LONGS.length;i++){
//				double _lat = Double.parseDouble(EVENT_LATS[i]);
//				double _long = Double.parseDouble(EVENT_LONGS[i]);
//				LatLng eventLocation = new LatLng(_lat, _long);
//
//				float[] results = new float[1];
//				Location.distanceBetween(myLocation.getLatitude(), myLocation.getLongitude(), _lat, _long, results);
//				if (results[0] <= radius){
//					//map.addMarker(new MarkerOptions().position(eventLocation).title("evenement"));
//					builder.include(eventLocation);
//				}
//		    }
//		    
//		    //zoom maps to current view
//	    	LatLngBounds bounds = builder.build();
//
//	    	int padding = 0; // offset from edges of the map in pixels
//	    	map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
//		}
	}
	
	
	private void changeCity(int position){
		//clear the map
//		map.clear();
//		
//		//change maps to the city selected
//		double _lat = Double.parseDouble(CITY_LATS[position]);
//		double _long = Double.parseDouble(CITY_LONGS[position]);
//		LatLng cityLocation = new LatLng(_lat, _long);
//		map.moveCamera(CameraUpdateFactory.newLatLng(cityLocation));
	}
	

	public void onMapReady(GoogleMap googleMap) {
		// TODO Auto-generated method stub
		
//		add events to maps
//	    add the events inside the circle
//	    for (int i=0;i<EVENT_LONGS.length;i++){
//			double _lat = Double.parseDouble(EVENT_LATS[i]);
//			double _long = Double.parseDouble(EVENT_LONGS[i]);
//			LatLng eventLocation = new LatLng(_lat, _long);
//			map.addMarker(new MarkerOptions().position(eventLocation).title("evenement"));
//	    }
//	    
//	    cityCmb.setSelection(0);
	}


}
