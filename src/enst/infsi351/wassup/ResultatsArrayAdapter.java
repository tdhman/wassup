package enst.infsi351.wassup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ResultatsArrayAdapter extends ArrayAdapter<Integer> {

	private final Context context;
	private final Integer[] images;
	private final Integer[] values;
	private final Integer[] order;
	private final String suffix;
	private final String prefix;
	
	static class ViewHolder {
		ImageView imageView;
		TextView titleText;
		TextView descriptionText;
		Button btnAccept;
		Button btnAddToFavorite;
	}

	public ResultatsArrayAdapter(Context context, Integer[] images, Integer[] values, Integer[] order, String prefix, String suffix) {
	    super(context, R.layout.fragment_list_resultats_item, images);
	    this.context = context;
	    this.images = images;
	    this.values = values;
	    this.order = order;
	    this.suffix = suffix;
	    this.prefix = prefix;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		
		View rowView = convertView;
		ViewHolder holder = null;
		
		if(rowView == null){
			LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = mLayoutInflater.inflate(R.layout.fragment_list_resultats_item, container, false);
			holder = new ViewHolder();
			holder.titleText = (TextView) rowView.findViewById(R.id.firstLine);
			holder.descriptionText = (TextView) rowView.findViewById(R.id.secondLine);
			holder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
			holder.btnAccept = (Button) rowView.findViewById(R.id.btnAccept);
			holder.btnAddToFavorite = (Button) rowView.findViewById(R.id.btnAddToFavorite);
			rowView.setTag(holder);
		} else
			holder = (ViewHolder) rowView.getTag();
	    
		// Set data by position
		holder.imageView.setImageResource(images[order[position]]);
		holder.imageView.setTag(order[position]);
		holder.imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		holder.titleText.setText(R.string.title);
		holder.descriptionText.setText(prefix + " " + Integer.toString(values[order[position]]) + " " + suffix);
//		holder.descriptionText.setText("something");
		holder.btnAccept.setTag(order[position]);
		holder.btnAccept.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), 
		    		      "Accepter l'invitation du évenement - " + images[(Integer)v.getTag()], 
		    		      Toast.LENGTH_LONG).show();
			}
		});
		holder.btnAddToFavorite.setTag(order[position]);
		holder.btnAddToFavorite.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), 
		    		      "Ajouter aux favoris - " + images[(Integer)v.getTag()], 
		    		      Toast.LENGTH_LONG).show();
			}
		});


	    return rowView;
	}
	
	@Override
    public int getCount() {
        return images.length;
    }
	
	@Override
    public Integer getItem(int position) {
        return images[position];
    }
	
	@Override
    public long getItemId(int position) {
        return position;
    }
} 
