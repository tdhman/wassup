package enst.infsi351.wassup;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ClickableViewAccessibility")
public class EvenementFragment extends Fragment{
	public static final String ARG_FRAGMENT_NUMBER = "evenement_number";
	
	private ImageView afficheView;
	
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
        
        TextView title = (TextView) rootView.findViewById(R.id.textTitle);
        Animation animText = AnimationUtils.loadAnimation(getActivity(), R.anim.text);
        animText.setRepeatCount(Animation.INFINITE);
        title.startAnimation(animText);
        
        Button favoriteBtn = (Button) rootView.findViewById(R.id.favoriteBtn);
        favoriteBtn.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), 
		    		      "Enregistré aux mes évenements - " + imageId, 
		    		      Toast.LENGTH_LONG).show();
			}
		});
        
        Button shareBtn = (Button) rootView.findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), 
		    		      "Partager avec les amis - " + imageId, 
		    		      Toast.LENGTH_LONG).show();
				Fragment fragment = new InvitationFragment();
	   	        Bundle args = new Bundle();
	   	        args.putInt(EvenementFragment.ARG_FRAGMENT_NUMBER, imageId);
	   	        fragment.setArguments(args);
	   	
	   	        FragmentManager fragmentManager = getFragmentManager();
	   	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("invitation").commit();
			}
		}); 
        
		return rootView;
	}

}
