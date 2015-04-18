package enst.infsi351.wassup;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class RechercheTypeFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "recherche_par_type_number";

	GridView gridView;

	static final String[] TYPES = new String[] { "Concert", "Cinéma", "Thêatre",
			"Sport", "Exposition", "Conférence", "Musique", "Festival" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_recherche_type,
				container, false);

		gridView = (GridView) rootView.findViewById(R.id.gridview);

		gridView.setAdapter(new ImageAdapter(getActivity(), TYPES));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {				
			}
		});
		return rootView;
	}

	public class ImageAdapter extends BaseAdapter {
		private Context context;
		private final String[] typeValues;

		public ImageAdapter(Context context, String[] typeValues) {
			this.context = context;
			this.typeValues = typeValues;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View gridView;

			if (convertView == null) {

				gridView = new View(context);

				// get layout
				gridView = inflater.inflate(R.layout.type_item, parent, false); // null


			} else {
				gridView = (View) convertView;
			}

			// set image based on selected text
			ImageView imageView = (ImageView) gridView.findViewById(R.id.gridThumbImage);
			
			TextView textView = (TextView) gridView.findViewById(R.id.textView);

			switch (position) {
			case 0:
				imageView.setImageResource(R.drawable.event_concert);
				textView.setText("Concert");
				break;
			case 1:
				imageView.setImageResource(R.drawable.event_cinema);
				textView.setText("Cinéma");
				break;
			case 2:
				imageView.setImageResource(R.drawable.event_theatre);
				textView.setText("Théatre");
				break;
			case 3:
				imageView.setImageResource(R.drawable.event_sport);
				textView.setText("Sport");
				break;
			case 4:
				imageView.setImageResource(R.drawable.event_exposition);
				textView.setText("Exposition");
				break;
			case 5:
				imageView.setImageResource(R.drawable.event_business);
				textView.setText("Conférence");
				break;
			case 6:
				imageView.setImageResource(R.drawable.event_music);
				textView.setText("Musique");
				break;
			case 7:
				imageView.setImageResource(R.drawable.event_festival);
				textView.setText("Festival");
				break;
			default:
				imageView.setImageResource(R.drawable.event_concert);
				break;
			}
			return gridView;
		}

		@Override
		public int getCount() {
			System.out.println("count="+typeValues.length);
			return typeValues.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

	}
}
