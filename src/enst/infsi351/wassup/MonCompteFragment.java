package enst.infsi351.wassup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MonCompteFragment extends Fragment implements OnTabChangeListener{
	public static final String ARG_FRAGMENT_NUMBER = "compte_number";
	private static final String TAG = "FragmentTabs";
	public static final String TAB_AMIS = "Mes Amis";
	public static final String TAB_SETTINGS = "Settings";
	private TabHost myTabHost;

    public MonCompteFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mon_compte, container, false);
        
        myTabHost =(TabHost) rootView.findViewById(R.id.tabhost);
        myTabHost.setup();
        myTabHost.addTab(myTabHost.newTabSpec("Mes Amis").setIndicator("Mes Amis",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.onglet1));
        myTabHost.addTab(myTabHost.newTabSpec("Settings").setIndicator("Settings",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.onglet2));
      
        myTabHost.setOnTabChangedListener(this);
        
		return rootView;
    }
    
	@Override
	public void onTabChanged(String tabId) {
		Log.d(TAG, "onTabChanged(): tabId=" + tabId);
		
		if (MonCompteFragment.TAB_AMIS.equals(tabId)) {
			
			myTabHost.setCurrentTab(0);
			return;
		}
		if (MonCompteFragment.TAB_SETTINGS.equals(tabId)) {			
			
			myTabHost.setCurrentTab(1);
			return;
		}
	}
	
	public void updateTab(String tabId, int placeholder) {
		FragmentManager fm = getFragmentManager();
		if (fm.findFragmentByTag(tabId) == null) {
			fm.beginTransaction().replace(placeholder, new MesAmis(), tabId).commit();
		}
	}

	
}
