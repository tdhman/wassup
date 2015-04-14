package enst.infsi351.wassup;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class InvitationFragment extends Fragment{
	
	public static final String ARG_FRAGMENT_NUMBER = "invitation_number";
	private Activity parentActivity;
	private MultiAutoCompleteTextView textView;
	
	private static final String[] AMIS = new String[] {
        "Hung", "Hoang", "Huy", "Hanzhi", "Hiep", "Duy", "Duong", "Marie", "Mai", "Man", "Alaine"
    };
	
	public InvitationFragment() {
        // Empty constructor required for fragment subclasses
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_invitation, container, false);
        getActivity().setTitle(R.string.invitation);
        parentActivity = getActivity();
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, AMIS);
        textView = (MultiAutoCompleteTextView) rootView.findViewById(R.id.multiAutoCompleteTextView);
        textView.setAdapter(adapter);
        textView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        
        Button btnAjouter = (Button) rootView.findViewById(R.id.btnAjouter);
        btnAjouter.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				textView.setText("");
				hideSoftKeyboard(parentActivity);
				Toast.makeText(v.getContext(), 
		    		      "Amis ajouté(s)!", 
		    		      Toast.LENGTH_LONG).show();
			}
		});
        
        Button btnEnvoyer = (Button) rootView.findViewById(R.id.btnEnvoyer);
        btnEnvoyer.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), 
		    		      "Invitation envoyée!", 
		    		      Toast.LENGTH_LONG).show();
			}
		});
        
		return rootView;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    
	}
	
	public static void hideSoftKeyboard(Activity activity) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
}
