package enst.infsi351.wassup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InscriptionFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "Inscription_number";
	EditText username, password, repassword, email;
	Button createAccount;

    public InscriptionFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inscription, container, false);
        
        username = (EditText) rootView.findViewById(R.id.userTxt);
        username.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Validation.hasText(username);
		        Validation.checkExisted(username,"account existed");

			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
        
        password = (EditText) rootView.findViewById(R.id.pwdTxt);
        password.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Validation.hasText(password);
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});

        repassword = (EditText) rootView.findViewById(R.id.pwd2Txt);
        repassword.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Validation.isEqual(repassword, password);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
        
        email = (EditText) rootView.findViewById(R.id.emailTxt);
        email.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Validation.isEmailAddress(email, true);
				Validation.checkExisted(email,"email existed");
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
        createAccount = (Button) rootView.findViewById(R.id.createAccountBtn);
        createAccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (checkValidation()){
					// TODO Auto-generated method stub
					//Back to mon compte
					ConnextionFragment.connected=true;
					Fragment fragment = new MonCompteFragment();
		            Bundle args = new Bundle();
		            args.putInt(MonCompteFragment.ARG_FRAGMENT_NUMBER, 4);
		            fragment.setArguments(args);
		
		            FragmentManager fragmentManager = getFragmentManager();
		            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("compte").commit();
						
				}else{
					Toast.makeText(getActivity(), "Erreus! Verifiez", Toast.LENGTH_LONG).show();
				}
			}
		});
        
        
        return rootView;
    }
    
    private boolean checkValidation() {
        boolean ret = true;
 
        if (!Validation.hasText(username)) ret = false;
        if (!Validation.checkExisted(username,"account existed")) ret = false;
        
		String regex= "^(?=.*\\d)(?=.*[a-zA-Z])(?!.*[\\W_\\x7B-\\xFF]).{6,15}$";
		String errMsg = "Requires 6-20 characters including at least 1 upper or lower alpha, and 1 digit";
		if (!Validation.isValid(password, regex, errMsg, true)) ret = false;
        
        if (!Validation.isEmailAddress(email, true)) ret = false;
        if (!Validation.checkExisted(email,"email existed")) ret = false;
        
        if (!Validation.isEqual(repassword, password)) ret = false;
        return ret;
    }
}
