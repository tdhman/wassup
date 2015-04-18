package enst.infsi351.wassup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;


public class RechercheDateFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "recherche_par_date_number";
	private CalendarPickerView calendar;
	private Calendar nextYear;
	private ArrayList<Date> dates;
	Switch switchMode;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recherche_date, container, false);
        
        
        //Add Calender view 
        nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        calendar = (CalendarPickerView) rootView.findViewById(R.id.calendar_view);
        
        Calendar today = Calendar.getInstance();
        dates = new ArrayList<Date>();
        today.add(Calendar.DATE, 3);
        dates.add(today.getTime());
        today.add(Calendar.DATE, 5);
        dates.add(today.getTime());
        calendar.init(new Date(), nextYear.getTime()) //
        .inMode(SelectionMode.MULTIPLE) //
        .withSelectedDates(dates);
        
        //get Switch selection
        switchMode = (Switch) rootView.findViewById(R.id.switchMode);
        switchMode.setText(R.string.Multiple);
        switchMode.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				//Range
				if (isChecked){
			        calendar.init(new Date(), nextYear.getTime()) //
		            .inMode(SelectionMode.RANGE) //
		            .withSelectedDates(dates);
			        switchMode.setText(R.string.Range);
				}
				//Multiple
				else{
			        calendar.init(new Date(), nextYear.getTime()) //
		            .inMode(SelectionMode.MULTIPLE) //
		            .withSelectedDates(dates);
			        switchMode.setText(R.string.Multiple);
				}
				
			}
		});
 
        
        return rootView;
    }
}
