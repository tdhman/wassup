package enst.infsi351.wassup;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MonCompteInvitation extends Fragment {
	public MonCompteInvitation() {
		// TODO Auto-generated constructor stub
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.invitationevent, container, false);
        //int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
        
        //String text = getResources().getStringArray(R.array.menu_array)[i];

//        TextView textView = (TextView) rootView.findViewById(R.id.textView1);
//        textView.setText(text);
//        getActivity().setTitle(text);
                

        return rootView;
    }

}
