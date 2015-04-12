package enst.infsi351.wassup;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class InvitationFragment extends Fragment{
	
	public static final String ARG_FRAGMENT_NUMBER = "invitation_number";
	
	private static final String[] AMIS = new String[] {
        "Hung", "Hoang", "Huy", "Hanzhi", "Hiep", "Duy", "Duong", "Marie", "Mai", "Man"
    };
	
	public InvitationFragment() {
        // Empty constructor required for fragment subclasses
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_invitation, container, false);
        getActivity().setTitle(R.string.invitation);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, AMIS);
        MultiAutoCompleteTextView textView = (MultiAutoCompleteTextView) rootView.findViewById(R.id.multiAutoCompleteTextView);
        textView.setAdapter(adapter);
        textView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        
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
}
