package asiantech.quocnp.manager_battery.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EFragment;

import asiantech.quocnp.manager_battery.R;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment
public class CustomFloatButtonFragment extends Fragment {

    Context mContext;

    /**
     * This is method constructor Fragment Floatting button
     */
    public CustomFloatButtonFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_floatting_button, container, false);
    }


}
