package enst.infsi351.wassup;

import java.util.Random;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class RechercheParCarteFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "recherche_par_carte_number";
	GoogleMap map;
	Spinner cityCmb;
	EditText km;
	Circle circle;
	Boolean detectLocation=false;
	public String[] CITY_LONGS, CITY_LATS, EVENT_LONGS, EVENT_LATS;
	Location myLocation;
	// A static dataset
	public final static Integer[] markersID = new Integer[] {
			R.drawable.marker1, R.drawable.marker2, R.drawable.marker3,
			R.drawable.marker4 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_recherche_carte,
				container, false);
		map = ((MapFragment) getActivity().getFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		// get data
		CITY_LONGS = getResources().getStringArray(R.array.city_long);
		CITY_LATS = getResources().getStringArray(R.array.city_lat);

		EVENT_LONGS = getResources().getStringArray(R.array.event_long);
		EVENT_LATS = getResources().getStringArray(R.array.event_lat);

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

		km.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				// TODO Auto-generated method stub
				if (actionId == EditorInfo.IME_ACTION_SEARCH
						|| actionId == EditorInfo.IME_ACTION_DONE
						|| event.getAction() == KeyEvent.ACTION_DOWN
						&& event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
					if (event==null || !event.isShiftPressed()) {
						// the user is done typing.
						nearMe();
						return true; // consume.
					}
				}
				return false; // pass on to other listeners.
			}
		});

		if (map != null) {
			initilizeMap();

		}

		return rootView;
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		System.out.println("destroy");
		super.onResume();
		if (map != null) {
			getActivity()
					.getFragmentManager()
					.beginTransaction()
					.remove(getActivity().getFragmentManager()
							.findFragmentById(R.id.map)).commit();
			map = null;
		}
	}

	private void nearMe() {
		System.out.println("NEAR ME");

		
		if (circle!=null)	{
			circle.remove();
		}
		
		if (myLocation == null) {
			myLocation = getCurrentLocation();
		}
		LatLng _me;
		if (myLocation != null) {

			// add makers to current Location
			_me = new LatLng(myLocation.getLatitude(),
					myLocation.getLongitude());

			// Find the circle with the radius from "near me"
			// CircleOptions circle=new CircleOptions();
			// circle.center(_me);
			double radius = Double.parseDouble(km.getText().toString().trim()) * 1000;
			// circle.radius(radius);
			// map.addCircle(circle);
			circle = map.addCircle(new CircleOptions().center(_me)
					.radius(radius)
					.strokeColor(Color.RED)
					.fillColor(Color.argb(20, 50, 0, 255))
					.strokeWidth(2)
					);

			// to calculate the bound of markers
			LatLngBounds.Builder builder = new LatLngBounds.Builder();

			int count=0;
			// add the events inside the circle
			for (int i = 0; i < EVENT_LONGS.length; i++) {
				double _lat = Double.parseDouble(EVENT_LATS[i]);
				double _long = Double.parseDouble(EVENT_LONGS[i]);
				LatLng eventLocation = new LatLng(_lat, _long);

				float[] results = new float[1];
				Location.distanceBetween(myLocation.getLatitude(),
						myLocation.getLongitude(), _lat, _long, results);
				if (results[0] <= radius) {
					// map.addMarker(new
					// MarkerOptions().position(eventLocation).title("evenement"));
					builder.include(eventLocation);
					count++;
				}
			}
			if (count > 0){
				builder.include(_me);
				// zoom maps to current view
				LatLngBounds bounds = builder.build();
	
				int padding = 0; // offset from edges of the map in pixels
				map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
			}
		}
	}

	private void changeCity(int position) {
		// change maps to the city selected
		double _lat = Double.parseDouble(CITY_LATS[position]);
		double _long = Double.parseDouble(CITY_LONGS[position]);
		LatLng cityLocation = new LatLng(_lat, _long);
		map.moveCamera(CameraUpdateFactory.newLatLng(cityLocation));
	}

	public void initilizeMap() {
		// add events to maps
		// add the events inside the circle
		map.setMyLocationEnabled(true);
		map.animateCamera(CameraUpdateFactory.zoomTo(16.0f));
		map.setOnMyLocationChangeListener(myLocationChangeListener);
		
		Random randomGenerator = new Random();

		for (int i = 0; i < EVENT_LONGS.length; i++) {
			int randomInt = randomGenerator.nextInt(4);

			double _lat = Double.parseDouble(EVENT_LATS[i]);
			double _long = Double.parseDouble(EVENT_LONGS[i]);
			LatLng eventLocation = new LatLng(_lat, _long);
			map.addMarker(new MarkerOptions()
					.position(eventLocation)
					.title("evenement")
					.icon(BitmapDescriptorFactory
							.fromResource(markersID[randomInt])));
		}

		// detect current location
		// map.setMyLocationEnabled(true);
		// map.getUiSettings().setMyLocationButtonEnabled(true);

		myLocation = getCurrentLocation();

		if (myLocation != null) {

			System.out.println("current Loc NOT NULL");
			// add makers to current Location
			LatLng _me = new LatLng(myLocation.getLatitude(),
					myLocation.getLongitude());

			// map.moveCamera(CameraUpdateFactory.newLatLng(_me));
			map.addMarker(new MarkerOptions().position(_me).title(
					"Vous êtes ici")).showInfoWindow();
			map.moveCamera(CameraUpdateFactory.newLatLng(_me));

		}
	}

	public Location getCurrentLocation() {
		// Enabling MyLocation Layer of Google Map
		// map.setMyLocationEnabled(true);

		// Getting LocationManager object from System Service LOCATION_SERVICE
//		LocationManager locationManager = (LocationManager) getActivity()
//				.getSystemService(Context.LOCATION_SERVICE);
//
//		// Creating a criteria object to retrieve provider
//		Criteria criteria = new Criteria();
//
//		// Getting the name of the best provider
//		String provider = locationManager.getBestProvider(criteria, true);
//
//		// Getting Current Location
//		Location location = locationManager.getLastKnownLocation(provider);
//		return location;
		return map.getMyLocation();
	}
	
	private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
	    @Override
	    public void onMyLocationChange(Location location) {
	        LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
//	        mMarker = mMap.addMarker(new MarkerOptions().position(loc));
	        if(map != null){
	        	if (!detectLocation){
		        	map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
	    			map.addMarker(new MarkerOptions().position(loc).title(
	    					"Vous êtes ici")).showInfoWindow();
	    			detectLocation=true;
	        	}
	        }
	    }
	};
}
