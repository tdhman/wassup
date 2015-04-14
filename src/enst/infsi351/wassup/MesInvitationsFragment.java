package enst.infsi351.wassup;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MesInvitationsFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "invitations_number";
	
	private ListView listView;
	private ArrayAdapter<Integer> invitationAdapter;
	
	// A static dataset 
    public final static Integer[] imageResIds = new Integer[] {
            R.drawable.affiche_1, R.drawable.affiche_2, R.drawable.affiche_3,
            R.drawable.affiche_4, R.drawable.affiche_5, R.drawable.affiche_6,
            R.drawable.affiche_7, R.drawable.affiche_8, R.drawable.affiche_9,
            R.drawable.affiche_10, R.drawable.affiche_11, R.drawable.affiche_12};

    public MesInvitationsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mes_invitations, container, false);
        
        listView = (ListView) rootView.findViewById(R.id.listVew);
        invitationAdapter = new InvitationArrayAdapter(getActivity(), imageResIds, getFragmentManager());
        listView.setAdapter(invitationAdapter);
        
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
        
        return rootView;
    }

}
