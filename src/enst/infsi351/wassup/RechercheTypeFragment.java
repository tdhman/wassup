package enst.infsi351.wassup;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.app.Application;

public class RechercheTypeFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "recherche_par_type_number";

	GridView gridView;

	static final String[] TYPES = new String[] { "Concert", "Ciné", "Thêatre",
			"Sport", "Cuisine", "Conférence", "Exposition", "Danse",
			"Pique-nique", "Fêtes" };

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
				Toast.makeText(
						getActivity().getApplicationContext(),
						((TextView) v.findViewById(R.id.gridItemCheckBox))
								.getText(), Toast.LENGTH_SHORT).show();

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
				gridView = inflater.inflate(R.layout.type_item, null);


			} else {
				gridView = (View) convertView;
			}

			// set value into checkbox
			CheckBox checkBox = (CheckBox) gridView
					.findViewById(R.id.gridItemCheckBox);
			checkBox.setText(typeValues[position]);

			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.gridThumbImage);
			System.out.println("pos " + position);

			switch (position) {
			case 0:
				imageView.setImageResource(R.drawable.concert);
				break;
			case 1:
				imageView.setImageResource(R.drawable.movie);
				break;
			case 2:
				imageView.setImageResource(R.drawable.theatre);
				break;
			case 3:
				imageView.setImageResource(R.drawable.sport);
				break;
			case 4:
				imageView.setImageResource(R.drawable.cuisine);
				break;
			case 5:
				imageView.setImageResource(R.drawable.conference);
				break;
			case 6:
				imageView.setImageResource(R.drawable.exhibition);
				break;
			case 7:
				imageView.setImageResource(R.drawable.dance);
				break;
			case 8:
				imageView.setImageResource(R.drawable.picnic);
				break;
			case 9:
				imageView.setImageResource(R.drawable.fetes);
				break;
			default:
				imageView.setImageResource(R.drawable.concert);
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
