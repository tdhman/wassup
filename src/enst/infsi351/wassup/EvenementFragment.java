package enst.infsi351.wassup;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
        
        
        Button favoriteBtn = (Button) rootView.findViewById(R.id.favoriteBtn);
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
        
        Button shareBtn = (Button) rootView.findViewById(R.id.shareBtn);
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
        
		return rootView;
	}

}
