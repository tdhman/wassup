package enst.infsi351.wassup;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

public class RechercheAvanceActivity extends ActionBarActivity {
	
	//un ViewPage pour changer des ecrans car c'est un tache avec multi etapes
	private ViewPager mPager;
	private static final int NUM_PAGES = 4;
	private PagerAdapter mPagerAdapter;
	private TextView title;

	public static final RechercheTypeFragment step1 = new RechercheTypeFragment();
	public static final RechercheDateFragment step2 = new RechercheDateFragment();
	public static final RechercheTarifFragment step3 = new RechercheTarifFragment();
	public static final RechercheParCarteFragment step4 = new RechercheParCarteFragment();

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recherche_avance);
		
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.step_page);
        mPagerAdapter = new MultiStepAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        
        
      //Bind the title indicator to the adapter
        final CirclePageIndicator circlePageIndicator = (CirclePageIndicator)findViewById(R.id.titles);
        circlePageIndicator.setFillColor(R.color.foursquare_blue);
        circlePageIndicator.setStrokeColor(R.color.gray);
        circlePageIndicator.setStrokeWidth(1);        
        circlePageIndicator.setViewPager(mPager);
        circlePageIndicator.setVisibility(View.VISIBLE);
        circlePageIndicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                System.out.println("page"+position);
                setTitleByStep(position);
            }
        });
        
      //Style of the title
        title = (TextView) findViewById(R.id.step_title);
        title.setTextColor(R.color.foursquare_blue);
        title.setVisibility(View.VISIBLE);
        
        //set title of the first step
        setTitleByStep(0);
        
      //Get Result of searching
        final Button searchBtn = (Button) this.findViewById(R.id.SearchBtn);
        searchBtn.setVisibility(View.VISIBLE);
        searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//circlePageIndicator.setVisibility(View.GONE);
				//searchBtn.setVisibility(View.GONE);
				//title.setVisibility(View.GONE);
	    		Fragment fragment = new ResultatsFragment();
	            Bundle args = new Bundle();
	            args.putInt(ResultatsFragment.ARG_FRAGMENT_NUMBER, 8);
	            fragment.setArguments(args);
	
	            FragmentManager fragmentManager = getSupportFragmentManager();
	            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("resultats").commit();
			}
		});
	}
	
	private void setTitleByStep(int position){
		switch (position){
			case 0:
				title.setText(R.string.event_type);
				break;
			case 1:
				title.setText(R.string.event_date);
				break;
			case 3:
				title.setText(R.string.event_price);
				break;
			case 2:
				title.setText(R.string.event_maps);
				break;
			default:
				title.setText(R.string.event_type);
				break;
		}
	}

    @Override
    public void onBackPressed() {
    	System.out.println("back"+mPager.getCurrentItem());
    	if (!returnBackStackImmediate(getSupportFragmentManager()))
	        if (mPager.getCurrentItem() == 0) {
	            // If the user is currently looking at the first step, allow the system to handle the
	            // Back button. This calls finish() on this activity and pops the back stack.
	        	System.out.println("nhan nut back");
	            super.onBackPressed();
	        } else {
	            // Otherwise, select the previous step.
	            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
	        }
    	
    	//set title for the step
		setTitleByStep(mPager.getCurrentItem());
    }
    
    private boolean returnBackStackImmediate(FragmentManager fm) {
        if (fm.getBackStackEntryCount() > 0){
        	fm.popBackStackImmediate();
        	return true;
        }
        return false;
    }
    
    /**
     * A simple pager adapter that represents ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class MultiStepAdapter extends FragmentStatePagerAdapter {
        public MultiStepAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
			//set title for the step
        	switch (position){
        		case 0:
        			return RechercheAvanceActivity.step1;
        		case 1:
        			return RechercheAvanceActivity.step2;
        		case 3:
        			return RechercheAvanceActivity.step3;
        		case 2:
        			return RechercheAvanceActivity.step4;
        		default:
        			return null;
        	}
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
        
    }
}
