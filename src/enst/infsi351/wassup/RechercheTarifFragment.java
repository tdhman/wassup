package enst.infsi351.wassup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yahoo.mobile.client.android.util.RangeSeekBar;


public class RechercheTarifFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "recherche_par_tarif_number";

    @SuppressWarnings("unchecked")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recherche_tarif, container, false);
        
        RangeSeekBar<Integer> seekBar = (RangeSeekBar<Integer>)rootView.findViewById(R.id.seekBarPrice);
        seekBar.setUnit("€");
        return rootView;
    }
}
