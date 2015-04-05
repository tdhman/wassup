package enst.infsi351.wassup;

//import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.Random;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class MesEvenementsFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "evenements_number";
	
	// A static dataset 
    public final static Integer[] imageResIds = new Integer[] {
            R.drawable.affiche_1, R.drawable.affiche_2, R.drawable.affiche_3,
            R.drawable.affiche_4, R.drawable.affiche_5, R.drawable.affiche_6,
            R.drawable.affiche_7, R.drawable.affiche_8, R.drawable.affiche_9};
    
 // A static dataset 
    public final static Integer[] avaResIds = new Integer[] {
            R.drawable.example_avatar, R.drawable.facebook_avatar, R.drawable.messi_avatar,
            R.drawable.robot_avatar, R.drawable.book_avatar, R.drawable.idea_avatar,
            R.drawable.bim_avatar, R.drawable.growth_avatar, R.drawable.penguin_avatar, R.drawable.smile_avatar};
	
	private LinearLayout gallery;

    public MesEvenementsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mes_evenements, container, false);
        int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
        String text = getResources().getStringArray(R.array.menu_array)[i];
        getActivity().setTitle(text);
        
        gallery = (LinearLayout) rootView.findViewById(R.id.gallery);
        for (int k=0; k<imageResIds.length; k++){
        	gallery.addView(addEvenementView(imageResIds[k]));
        }
        
        return rootView;
    }
    
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
	
	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }
	
	    return inSampleSize;
    }
    
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
            int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    
    public View addEvenementView(final int imageId){
    	//LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    	ImageView imageView = new ImageView(getActivity());
    	imageView.setScaleType(ScaleType.CENTER_CROP);
    	//imageView.setLayoutParams(imageParams);
    	imageView.setOnClickListener(new OnClickListener(){
    		   @Override
    		   public void onClick(View v) {
    			   // TODO: Test Evenement view
    			   Fragment fragment = new EvenementFragment();
	   	           Bundle args = new Bundle();
	   	           args.putInt(EvenementFragment.ARG_FRAGMENT_NUMBER, imageId);
	   	           fragment.setArguments(args);
	   	
	   	           FragmentManager fragmentManager = getFragmentManager();
	   	           fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    		   }});
    	BitmapWorkerTask task = new BitmapWorkerTask(imageView);
    	task.execute(imageId);
    	
    	TableLayout infoView = new TableLayout(getActivity());
    	infoView.setBackgroundColor(Color.WHITE);
    	infoView.setStretchAllColumns(true);
    	TableRow row1 = new TableRow(getActivity());
    	row1.setPadding(5, 10, 0, 0);
    	TextView locationText = new TextView(getActivity());
    	locationText.setTextSize(24);
    	locationText.setText(R.string.location);
    	TextView dateText = new TextView(getActivity());
    	dateText.setTextSize(24);
    	dateText.setText(R.string.date);
    	row1.addView(locationText);
    	row1.addView(dateText);
    	TableRow row2 = new TableRow(getActivity());
    	row2.setPadding(5, 10, 0, 0);
    	TextView someText1 = new TextView(getActivity());
    	someText1.setMaxLines(4);
    	someText1.setText(R.string.some_text);
    	TextView someText2 = new TextView(getActivity());
    	someText2.setText(R.string.some_text);
    	someText2.setMaxLines(4);
    	row2.addView(someText1);
    	row2.addView(someText2);
    	TableRow row3 = new TableRow(getActivity());
    	row3.setPadding(5, 10, 0, 0);
    	TextView someText3 = new TextView(getActivity());
    	someText3.setTextSize(24);
    	someText3.setText(R.string.friends);
    	row3.addView(someText3);
    	infoView.addView(row1);
    	infoView.addView(row2);
    	infoView.addView(row3);
    	
    	LinearLayout layoutListFriend = new LinearLayout(getActivity());
    	LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    	listParams.setMargins(24, 0, 24, 0);
    	layoutListFriend.setLayoutParams(listParams);
    	LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(100, 100);
    	imageParams.setMargins(10, 0, 10, 0);
    	Random r = new Random();
    	for(int i=0; i<4; i++){
    		ImageView avatarView = new ImageView(getActivity());
    		avatarView.setScaleType(ScaleType.CENTER);
    		avatarView.setAdjustViewBounds(true);
    		avatarView.setLayoutParams(imageParams);
    		int i1 = r.nextInt(9 - 0 + 1) + 0; // range 0-9
    		avatarView.setBackgroundResource(avaResIds[i1]);
    		layoutListFriend.addView(avatarView);
    	}
    	
    	
    	LinearLayout layout = new LinearLayout(getActivity());
    	LayoutParams LLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(LLParams);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        FrameLayout frameView = new FrameLayout(getActivity());   
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        frameView.setLayoutParams(new LayoutParams(displaymetrics.widthPixels, displaymetrics.heightPixels/2));
    	
        LayoutParams btnParams = new LayoutParams(64,64);       
        Button shareBtn = new Button(getActivity());
        shareBtn.setLayoutParams(btnParams);
        shareBtn.setBackgroundResource(R.drawable.add_friends);
        shareBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), 
		    		      "Partager avec les amis - " + imageId, 
		    		      Toast.LENGTH_LONG).show();
			}
		});
        
        frameView.addView(imageView);
        frameView.addView(shareBtn);
        
        layout.addView(frameView);
        layout.addView(infoView);
        layout.addView(layoutListFriend);
    	
    	return layout;
    }
    
    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            return decodeSampledBitmapFromResource(getResources(), data, 250, 250);
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
