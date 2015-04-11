package enst.infsi351.wassup;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MesEvenementsFragment extends ActionBarActivity {
	public static final String ARG_FRAGMENT_NUMBER = "evenements_number";
	
	// A static dataset 
    public final static Integer[] imageResIds = new Integer[] {
            R.drawable.affiche_1, R.drawable.affiche_2, R.drawable.affiche_3,
            R.drawable.affiche_4, R.drawable.affiche_5, R.drawable.affiche_6,
            R.drawable.affiche_7, R.drawable.affiche_8, R.drawable.affiche_9};
	
	//private LinearLayout gallery;
	private ViewPager mPager;
	private static final int NUM_PAGES = 9;
	private PagerAdapter mPagerAdapter;
	

    public MesEvenementsFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);


        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        
        Button shareBtn = (Button) findViewById(R.id.shareBtn);
        shareBtn.setBackgroundResource(R.drawable.add_friends);
        shareBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), 
		    		      "Partager avec les amis - " + imageResIds[mPager.getCurrentItem()], 
		    		      Toast.LENGTH_LONG).show();
				Fragment fragment = new InvitationFragment();
	   	        Bundle args = new Bundle();
	   	        args.putInt(EvenementFragment.ARG_FRAGMENT_NUMBER, imageResIds[mPager.getCurrentItem()]);
	   	        fragment.setArguments(args);
	   	
	   	        FragmentManager fragmentManager = getFragmentManager();
	   	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("invitation").commit();
			}
		});
    }

    @Override
    public void onBackPressed() {
    	if (!returnBackStackImmediate(getFragmentManager()))
	        if (mPager.getCurrentItem() == 0) {
	            // If the user is currently looking at the first step, allow the system to handle the
	            // Back button. This calls finish() on this activity and pops the back stack.
	            super.onBackPressed();
	        } else {
	            // Otherwise, select the previous step.
	            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
	        }
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
    
    private boolean returnBackStackImmediate(FragmentManager fm) {
        if (fm.getBackStackEntryCount() > 0){
        	fm.popBackStackImmediate();
        	return true;
        }
        
        return false;
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
        	if (!returnBackStackImmediate(getFragmentManager()))
        		NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    
    /**
     * A simple pager adapter that represents ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
        	return ScreenSlidePageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
