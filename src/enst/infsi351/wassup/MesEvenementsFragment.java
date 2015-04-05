package enst.infsi351.wassup;

//import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.Toast;


public class MesEvenementsFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "evenements_number";
	
	// A static dataset 
    public final static Integer[] imageResIds = new Integer[] {
            R.drawable.affiche_1, R.drawable.affiche_2, R.drawable.affiche_3,
            R.drawable.affiche_4, R.drawable.affiche_5, R.drawable.affiche_6,
            R.drawable.affiche_7, R.drawable.affiche_8, R.drawable.affiche_9};
	
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
    	ImageView imageView = new ImageView(getActivity());
    	imageView.setScaleType(ScaleType.FIT_START);
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
    	
    	ImageView infoView = new ImageView(getActivity());
    	infoView.setBackgroundResource(R.drawable.info);
    	
    	LinearLayout layout = new LinearLayout(getActivity());
    	layout.setOrientation(LinearLayout.VERTICAL);
    	
    	LayoutParams LLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        layout.setWeightSum(6f);
        layout.setLayoutParams(LLParams);
        
        FrameLayout frameView = new FrameLayout(getActivity());
        
    	
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
