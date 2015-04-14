package enst.infsi351.wassup;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MonCompteFragment extends ActionBarActivity implements OnTabChangeListener{
	public static final String ARG_FRAGMENT_NUMBER = "compte_number";
	private static final String TAG = "FragmentTabs";
	public static final String TAB_AMIS = "Mes Amis";
	public static final String TAB_INVI = "Mes";
	private TabHost myTabHost;
	private int mCurrentTab;
    public MonCompteFragment() {
        // Empty constructor required for fragment subclasses
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mon_compte);
     // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        myTabHost =(TabHost) findViewById(R.id.tabhost);
        myTabHost.setup();
        myTabHost.addTab(myTabHost.newTabSpec("Mes Amis").setIndicator("Mes Amis",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.onglet1));
        myTabHost.addTab(myTabHost.newTabSpec("Mes").setIndicator("A venir",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.onglet2));
//        
        myTabHost.setOnTabChangedListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_search:
            /** TODO:
             * Add new activity for advanced search here!
             */
            return true;
        case android.R.id.home: {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
        
    }
	@Override
	public void onTabChanged(String tabId) {
		Log.d(TAG, "onTabChanged(): tabId=" + tabId);
//		Context context = getApplicationContext();
//		CharSequence text = "Hello!";
//		int duration = Toast.LENGTH_SHORT;
//
//		Toast toast = Toast.makeText(context, tabId, duration);
//		toast.show();
		// TODO Auto-generated method stub
		
		if (MonCompteFragment.TAB_AMIS.equals(tabId)) {
			//updateTab(tabId, R.id.onglet1);
			
			myTabHost.setCurrentTab(0);
			return;
		}
		if (MonCompteFragment.TAB_INVI.equals(tabId)) {			
			//updateTab(tabId, R.id.onglet2);
			myTabHost.setCurrentTab(1);
			return;
		}
	}
	private void updateTab(String tabId, int placeholder) {
		FragmentManager fm = getFragmentManager();
		if (fm.findFragmentByTag(tabId) == null) {
			fm.beginTransaction()
					.replace(placeholder, new MesAmies(tabId), tabId)
					.commit();
		}
	}

	
}
