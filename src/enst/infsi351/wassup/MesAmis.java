package enst.infsi351.wassup;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MesAmis extends Fragment{
	public MesAmis (String tabid){		

	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.mesamies, container, false);
                

        return rootView;
    }
}
