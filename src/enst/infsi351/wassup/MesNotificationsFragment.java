package enst.infsi351.wassup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MesNotificationsFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "notifications_number";
	
	private ListView listView;
	private ArrayAdapter<Integer> notificationAdapter;
	
	// A static dataset 
    public final static Integer[] userResIds = new Integer[] {
            R.drawable.book_avatar, R.drawable.bim_avatar, R.drawable.idea_avatar,
            R.drawable.facebook_avatar, R.drawable.robot_avatar, R.drawable.example_avatar,
            R.drawable.messi_avatar, R.drawable.smile_avatar, R.drawable.penguin_avatar};
	

    public MesNotificationsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mes_notifications, container, false);
        
        listView = (ListView) rootView.findViewById(R.id.listVew);
        notificationAdapter = new NotificationArrayAdapter(getActivity(), userResIds);
        listView.setAdapter(notificationAdapter);
        
        return rootView;
    }

}
