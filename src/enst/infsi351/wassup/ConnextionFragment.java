package enst.infsi351.wassup;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConnextionFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "connexion_number";
	public static Boolean connected = false;
	EditText username, password;

    public ConnextionFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_connexion, container, false);
        int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
        String text = getResources().getStringArray(R.array.menu_array)[i];
        
        //validate username and password
        username = (EditText) rootView.findViewById(R.id.usernameTxt);
        username.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Validation.hasText(username);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
        
        password = (EditText) rootView.findViewById(R.id.passwordTxt);
        password.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Validation.hasText(password);
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
        

        Button connectBtn = (Button)rootView.findViewById(R.id.connectBtn);
        connectBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Validation the form
				if (checkValidation()){
					// TODO Auto-generated method stub
					//Back to Mon compte
					ConnextionFragment.connected=true;
					Fragment fragment = new MonCompteFragment();
		            Bundle args = new Bundle();
		            args.putInt(MonCompteFragment.ARG_FRAGMENT_NUMBER, 4);
		            fragment.setArguments(args);
		
		            FragmentManager fragmentManager = getFragmentManager();
		            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("compte").commit();
					
				}else{
					Toast.makeText(getActivity(), "Erreurs", Toast.LENGTH_LONG).show();
				}
			}
		});
        
        Button registerBtn = (Button)rootView.findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("dang ky");
				// TODO Auto-generated method stub
				// Go to registration page
	    		Fragment fragment = new InscriptionFragment();	
	            
	            FragmentManager fragmentManager = getFragmentManager();
	            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("inscription").commit();
			}
		});
        
        return rootView;
    }
    private boolean checkValidation() {
        boolean ret = true;
 
        if (!Validation.hasText(username)) ret = false;
		String regex= "^(?=.*\\d)(?=.*[a-zA-Z])(?!.*[\\W_\\x7B-\\xFF]).{6,15}$";
		String errMsg = "Requires 6-20 characters including at least 1 upper or lower alpha, and 1 digit";
		if (!Validation.isValid(password, regex, errMsg, true)) ret = false;
        return ret;
    }
    
}
