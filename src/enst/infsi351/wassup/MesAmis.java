package enst.infsi351.wassup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MesAmis extends Fragment{
	
	public MesAmis (){		

	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.mesamies, container, false);
                

        return rootView;
    }
}
