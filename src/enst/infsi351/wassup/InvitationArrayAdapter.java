package enst.infsi351.wassup;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class InvitationArrayAdapter extends ArrayAdapter<Integer> {

	private final Context context;
	private final Integer[] values;
	private final FragmentManager fragmentManager;
	
	static class ViewHolder{
		ImageView imageView;
		TextView titleText;
		TextView descriptionText;
		Button btnAccept;
		Button btnRefuse;
		Spinner spinner;
		boolean answered = false;
		
		ViewHolder(){
			if (answered)
				hideButtons();
		}
		
		void hideButtons(){
			if (btnAccept != null)
				btnAccept.setVisibility(View.GONE);
			if (btnRefuse != null)
				btnRefuse.setVisibility(View.GONE);
			spinner.setVisibility(View.VISIBLE);
		}
		
		void answer(){
			answered = true;
			hideButtons();
		}
	}

	public InvitationArrayAdapter(Context context, Integer[] values, FragmentManager fragmentManager) {
	    super(context, R.layout.fragment_list_item, values);
	    this.context = context;
	    this.values = values;
	    this.fragmentManager = fragmentManager;
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
			holder.spinner = (Spinner) rowView.findViewById(R.id.spinner);
			rowView.setTag(holder);
		} else
			holder = (ViewHolder) rowView.getTag();
	    
		// Set data by position
		final EditText refuseText = (EditText) rowView.findViewById(R.id.refuseText);
		refuseText.setOnFocusChangeListener(new OnFocusChangeListener() {			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				showOrHideSoftKeyboard(v, hasFocus);
			}
		});
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.invite_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		holder.spinner.setAdapter(adapter);
		holder.spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub				
			}
		});
		
		holder.imageView.setImageResource(values[position]);
		holder.imageView.setTag(values[position]);
		holder.imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment = new EvenementFragment();
	 	        Bundle args = new Bundle();
	 	        args.putInt(EvenementFragment.ARG_FRAGMENT_NUMBER, (Integer) v.getTag());
	 	        fragment.setArguments(args);
	 	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("evenement").commit();
			}
		});
		holder.titleText.setText("Hanzhi vous a envoyé une invitation");
		holder.descriptionText.setText("Titre du événement et description");
		holder.btnAccept.setTag(values[position]);
		holder.btnAccept.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				holder.answer();
				Toast.makeText(v.getContext(), 
		    		      "Accepter l'invitation du évenement - " + v.getTag(), 
		    		      Toast.LENGTH_LONG).show();
				if (refuseText.getVisibility() == View.VISIBLE)
					refuseText.setVisibility(View.GONE);
			}
		});
		
		holder.btnRefuse.setTag(values[position]);
		holder.btnRefuse.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				if (refuseText.getVisibility() == View.GONE){
					refuseText.setVisibility(View.VISIBLE);
					if (refuseText.requestFocus())
						showOrHideSoftKeyboard(v, true);
				}
				else {
					refuseText.setVisibility(View.GONE);
					showOrHideSoftKeyboard(v, false);
					holder.spinner.setSelection(1);
					holder.answer();
					Toast.makeText(v.getContext(), 
			    		      "Refuser l'invitation du évenement - " + v.getTag(), 
			    		      Toast.LENGTH_LONG).show();
				}
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
	
	
	public void showOrHideSoftKeyboard(View view, boolean show) {
	    InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    if(show)
	    	inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
	    else
	    	inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
} 
