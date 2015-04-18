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
	public static final String TAB_INVI = "Chercher Amis";
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
        myTabHost.addTab(myTabHost.newTabSpec("Mes").setIndicator("A venir",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.onglet2));
      
        myTabHost.setOnTabChangedListener(this);
        
		return rootView;
    }
    
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_mon_compte);
//     // enable ActionBar app icon to behave as action to toggle nav drawer
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        
//        myTabHost =(TabHost) findViewById(R.id.tabhost);
//        myTabHost.setup();
//        myTabHost.addTab(myTabHost.newTabSpec("Mes Amis").setIndicator("Mes Amis",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.onglet1));
//        myTabHost.addTab(myTabHost.newTabSpec("Mes").setIndicator("A venir",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.onglet2));
//        
//        myTabHost.setOnTabChangedListener(this);
//
//    }
//    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//    
//    /* Called whenever we call invalidateOptionsMenu() */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content view
//        return super.onPrepareOptionsMenu(menu);
//    }
//    
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action buttons
//        switch(item.getItemId()) {
//        case R.id.action_search:
//            /** TODO:
//             * Add new activity for advanced search here!
//             */
//            return true;
//        case android.R.id.home: {
//            NavUtils.navigateUpFromSameTask(this);
//            return true;
//        }
//        default:
//            return super.onOptionsItemSelected(item);
//        }
//    }

//    @Override
//    public void onBackPressed() {
//            super.onBackPressed();
//        
//    }
    
	@Override
	public void onTabChanged(String tabId) {
		Log.d(TAG, "onTabChanged(): tabId=" + tabId);
		
		if (MonCompteFragment.TAB_AMIS.equals(tabId)) {
			
			myTabHost.setCurrentTab(0);
			return;
		}
		if (MonCompteFragment.TAB_INVI.equals(tabId)) {			
			
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
