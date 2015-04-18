package enst.infsi351.wassup;

import com.yahoo.mobile.client.android.util.RangeSeekBar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RechercheTarifFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "recherche_par_tarif_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recherche_tarif, container, false);
        
        RangeSeekBar<Integer> seekBar = (RangeSeekBar<Integer>)rootView.findViewById(R.id.seekBarPrice);
        seekBar.setUnit("€");
        return rootView;
    }
}
