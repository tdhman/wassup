package enst.infsi351.wassup;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MesEvenementsFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "evenements_number";

    public MesEvenementsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mes_evenements, container, false);
        int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
        String text = getResources().getStringArray(R.array.menu_array)[i];

        TextView textView = (TextView) rootView.findViewById(R.id.textView1);
        textView.setText(text);
        getActivity().setTitle(text);
        return rootView;
    }
}
