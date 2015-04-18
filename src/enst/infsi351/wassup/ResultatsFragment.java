package enst.infsi351.wassup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ResultatsFragment extends Fragment {

	public static final String ARG_FRAGMENT_NUMBER = "resultats_number";
	
	private ListView listView;
	private ArrayAdapter<Integer> resultAdapter;
	private Spinner spinnerCriteria;

	// A static dataset 
	public final static Integer[] imageResIds = new Integer[] {
	        R.drawable.affiche_1, R.drawable.affiche_2, R.drawable.affiche_3,
	        R.drawable.affiche_4, R.drawable.affiche_5, R.drawable.affiche_6,
	        R.drawable.affiche_7, R.drawable.affiche_8, R.drawable.affiche_9,
	        R.drawable.affiche_10, R.drawable.affiche_11, R.drawable.affiche_12};
		
	public final static Integer[] prices = new Integer[] {
		50, 30, 120, 10, 60, 20, 70, 80, 40, 90, 100, 110
	};
	
	public final static Integer[] distances = new Integer[] {
		10, 1, 5, 4, 6, 8, 9, 2, 3, 12, 11, 7
	};
	
	public ResultatsFragment() {
	    // Empty constructor required for fragment subclasses
	}
	
	Integer[] sortByField(Integer[] field, boolean ascending) {
		int n = field.length;
		
		Integer[] result = new Integer[n];
		for (int i = 0; i < n; ++i)
			result[i] = i;
		
		for (int i = 0; i < n - 1; ++i)
			for (int j = i + 1; j < n; ++j)
				if (	(ascending && field[result[i]] > field[result[j]]) ||
						(!ascending && field[result[i]] < field[result[j]]))
				{
					int temp = result[i];
					result[i] = result[j];
					result[j] = temp;
				}
		
		return result;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	    View rootView = inflater.inflate(R.layout.fragment_resultats, container, false);
	    
	    listView = (ListView) rootView.findViewById(R.id.listView);
	    resultAdapter = new ResultatsArrayAdapter(	getActivity(), 
	    											imageResIds, 
	    											prices, 
	    											sortByField(prices, true),
	    											"Prix:",
	    											"E");
	    
	    listView.setAdapter(resultAdapter);
	    
	    listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Fragment fragment = new EvenementFragment();
	 	        Bundle args = new Bundle();
	 	        args.putInt(EvenementFragment.ARG_FRAGMENT_NUMBER, imageResIds[position]);
	 	        fragment.setArguments(args);
	 	        
	 	        FragmentManager fragmentManager = getFragmentManager();
	 	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("evenement").commit();
			}
		});
	    
	    //spinner
	    spinnerCriteria = (Spinner) rootView.findViewById(R.id.spinnerCriteria);
	    ArrayAdapter<CharSequence> adapterSpinnerCriteria =
	    		ArrayAdapter.createFromResource(getActivity(), R.array.list_criterias, android.R.layout.simple_spinner_item);
	    
	    spinnerCriteria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				if (position == 0)
				{
					resultAdapter = new ResultatsArrayAdapter(	getActivity(), 
							imageResIds, 
							prices, 
							sortByField(prices, true),
							"Prix:",
							"E");
					
					listView.setAdapter(resultAdapter);
				}
				else
				{
					resultAdapter = new ResultatsArrayAdapter(	getActivity(), 
							imageResIds, 
							distances, 
							sortByField(distances, true),
							"Distance:",
							"km");
					
					listView.setAdapter(resultAdapter);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				parent.setSelection(0);
			}
		});
	    
	    adapterSpinnerCriteria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	    spinnerCriteria.setAdapter(adapterSpinnerCriteria);
	    return rootView;
	}

}