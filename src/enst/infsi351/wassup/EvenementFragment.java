package enst.infsi351.wassup;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("ClickableViewAccessibility")
public class EvenementFragment extends Fragment{
	public static final String ARG_FRAGMENT_NUMBER = "evenement_number";
	
	private ImageView afficheView;
	private FrameLayout frameView;
	private boolean hidden = true;
	
	public EvenementFragment() {
        // Empty constructor required for fragment subclasses
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_evenement, container, false);
        getActivity().setTitle(R.string.event);
        
        afficheView = (ImageView) rootView.findViewById(R.id.afficheView);
        final int imageId = getArguments().getInt(ARG_FRAGMENT_NUMBER);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), imageId);
        afficheView.setImageBitmap(bm);
        
        final Button favoriteBtn = (Button) rootView.findViewById(R.id.favoriteBtn);
        favoriteBtn.getLayoutParams().width = 64;
        favoriteBtn.getLayoutParams().height = 64;
        favoriteBtn.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), 
		    		      "Enregistré aux mes évenements - " + imageId, 
		    		      Toast.LENGTH_LONG).show();
			}
		});
        
        final Button shareBtn = (Button) rootView.findViewById(R.id.shareBtn);
        shareBtn.getLayoutParams().width = 64;
        shareBtn.getLayoutParams().height = 64;
        shareBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), 
		    		      "Partager avec les amis - " + imageId, 
		    		      Toast.LENGTH_LONG).show();
			}
		});
        
        frameView = (FrameLayout) rootView.findViewById(R.id.frameView);
        frameView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if(hidden == true){
                        favoriteBtn.setVisibility(View.GONE);
                        shareBtn.setVisibility(View.GONE);
                        hidden = false;
                    }else{
                        favoriteBtn.setVisibility(View.VISIBLE);
                        shareBtn.setVisibility(View.VISIBLE);
                        hidden = true;
                    }
                }
                return true;
			}
		});
        
		return rootView;
	}

}
