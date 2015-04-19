package enst.infsi351.wassup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import com.yahoo.mobile.client.android.util.RangeSeekBar;


public class RechercheTarifFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "recherche_par_tarif_number";
	
	private static final String[] KEYWORDS = new String[] {
        "Pâques", "enfant", "festival", "bicyclette", "parc", "Noel", "Parisienne", "famille", "art", "culture", "jeu", "camping"
    };

    @SuppressWarnings("unchecked")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recherche_tarif, container, false);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, KEYWORDS);
        MultiAutoCompleteTextView textView = (MultiAutoCompleteTextView) rootView.findViewById(R.id.multiAutoCompleteTextView);
        textView.setAdapter(adapter);
        textView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        
        RangeSeekBar<Integer> seekBar = (RangeSeekBar<Integer>)rootView.findViewById(R.id.seekBarPrice);
        seekBar.setUnit("€");
        return rootView;
    }
}
