package enst.infsi351.wassup;

import java.lang.ref.WeakReference;
import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ScreenSlidePageFragment extends Fragment {
	
	private int imageId;
	
	// A static dataset 
	public final static Integer[] imageResIds = new Integer[] {
	        R.drawable.affiche_1, R.drawable.affiche_2, R.drawable.affiche_3,
	        R.drawable.affiche_4, R.drawable.affiche_5, R.drawable.affiche_6,
	        R.drawable.affiche_7, R.drawable.affiche_8, R.drawable.affiche_9};
	
    public final static Integer[] avaResIds = new Integer[] {
            R.drawable.example_avatar, R.drawable.facebook_avatar, R.drawable.messi_avatar,
            R.drawable.robot_avatar, R.drawable.book_avatar, R.drawable.idea_avatar,
            R.drawable.bim_avatar, R.drawable.growth_avatar, R.drawable.penguin_avatar, R.drawable.smile_avatar};
    
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageId = getArguments().getInt("imageId", 0); 
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        
        ImageView imageView = (ImageView) rootView.findViewById(R.id.afficheView);
        imageView.setOnClickListener(new OnClickListener(){
 		   @Override
 		   public void onClick(View v) {
 			  Toast.makeText(getActivity(), 
	    		      "Image cliquée - " + imageResIds[imageId], 
	    		      Toast.LENGTH_LONG).show();
 		   }});
    	BitmapWorkerTask task = new BitmapWorkerTask(imageView);
    	task.execute(imageResIds[imageId]);
        
    	FrameLayout frameView = (FrameLayout) rootView.findViewById(R.id.frameView);   
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        frameView.setLayoutParams(new LinearLayout.LayoutParams(displaymetrics.widthPixels, displaymetrics.heightPixels/2));
        
        LinearLayout layoutListFriend = (LinearLayout) rootView.findViewById(R.id.layoutList);
    	LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    	listParams.setMargins(24, 0, 24, 0);
    	layoutListFriend.setLayoutParams(listParams);
    	LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(70, 70);
    	imageParams.setMargins(10, 0, 10, 0);
    	Random r = new Random();
    	for(int i=0; i<5; i++){
    		ImageView avatarView = new ImageView(getActivity());
    		avatarView.setScaleType(ScaleType.CENTER);
    		avatarView.setAdjustViewBounds(true);
    		avatarView.setLayoutParams(imageParams);
    		int i1 = r.nextInt(9 - 0 + 1) + 0; // range 0-9
    		avatarView.setBackgroundResource(avaResIds[i1]);
    		layoutListFriend.addView(avatarView);
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
	
	// newInstance constructor for creating fragment with arguments
    public static ScreenSlidePageFragment newInstance(int imageId) {
    	ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt("imageId", imageId);
        fragment.setArguments(args);
        return fragment;
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
