package enst.infsi351.wassup;

import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MesAmies extends Fragment{
	public MesAmies (String tabid){		
		CharSequence text = "Haha!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(getActivity(), text, duration);
		toast.show();
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	Activity b = getActivity();
        View rootView = inflater.inflate(R.layout.mesamies, container, false);
        //int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
        
        //String text = getResources().getStringArray(R.array.menu_array)[i];

//        TextView textView = (TextView) rootView.findViewById(R.id.textView1);
//        textView.setText(text);
//        getActivity().setTitle(text);
                

        return rootView;
    }
}
