package enst.infsi351.wassup;

import java.util.Random;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationArrayAdapter extends ArrayAdapter<Integer> {
	private final Context context;
	private final Integer[] values;
	
	static class ViewHolder{
		ImageView imageView;
		TextView titleText;
		TextView descriptionText;
		Button btnAccept;
		Button btnRefuse;
	}

	public NotificationArrayAdapter(Context context, Integer[] values) {
	    super(context, R.layout.fragment_list_item, values);
	    this.context = context;
	    this.values = values;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup container) {
		
		View rowView = convertView;
		final ViewHolder holder;
		
		if(convertView == null){
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
		
		holder.imageView.setImageResource(values[position]);
		holder.imageView.setTag(values[position]);
		Random r = new Random();
		if (r.nextInt() % 2 == 0)
			holder.titleText.setText("Hanzhi a accepté votre invitation");
		else
			holder.titleText.setText("Hanzhi a refusé votre invitation");
		holder.descriptionText.setText("Titre du événement ou le message indiqué la raison de refuse");
		holder.btnAccept.setVisibility(View.GONE);
		holder.btnRefuse.setVisibility(View.GONE);

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
