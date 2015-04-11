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


public class InvitationArrayAdapter extends ArrayAdapter<Integer> {

	private final Context context;
	private final Integer[] values;
	
	static class ViewHolder{
		ImageView imageView;
		TextView titleText;
		TextView descriptionText;
		Button btnAccept;
		Button btnRefuse;
	}

	public InvitationArrayAdapter(Context context, Integer[] values) {
	    super(context, R.layout.fragment_list_item, values);
	    this.context = context;
	    this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		
		View rowView = convertView;
		ViewHolder holder = null;
		
		if(rowView == null){
			LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = mLayoutInflater.inflate(R.layout.fragment_list_item, container, false);
			holder = new ViewHolder();
			holder.titleText = (TextView) rowView.findViewById(R.id.firstLine);
			holder.descriptionText = (TextView) rowView.findViewById(R.id.secondLine);
			holder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
			holder.btnAccept = (Button) rowView.findViewById(R.id.btnAccept);
			holder.btnRefuse = (Button) rowView.findViewById(R.id.btnRefuse);
			rowView.setTag(holder);
		} else
			holder = (ViewHolder) rowView.getTag();
	    
		// Set data by position
		holder.imageView.setImageResource(values[position]);
		holder.imageView.setTag(position);
		holder.imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		holder.titleText.setText("Hanzhi a vous envoyé une invitation");
		holder.descriptionText.setText("Titre du événement et description");
		holder.btnAccept.setTag(position);
		holder.btnAccept.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), 
		    		      "Accepter l'invitation du évenement - " + values[(Integer) v.getTag()], 
		    		      Toast.LENGTH_LONG).show();
			}
		});
		holder.btnRefuse.setTag(position);
		holder.btnRefuse.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), 
		    		      "Refuser l'invitation du évenement - " + values[(Integer)v.getTag()], 
		    		      Toast.LENGTH_LONG).show();
			}
		});


	    return rowView;
	}
	
	@Override
    public int getCount() {
        return values.length;
    }
	
	@Override
    public Integer getItem(int position) {
        return values[position];
    }
	
	@Override
    public long getItemId(int position) {
        return position;
    }
} 
