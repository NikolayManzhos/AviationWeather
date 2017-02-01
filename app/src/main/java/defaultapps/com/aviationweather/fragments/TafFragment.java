package defaultapps.com.aviationweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import defaultapps.com.aviationweather.R;
import defaultapps.com.aviationweather.controllers.TafController;
import defaultapps.com.aviationweather.views.TafView;

/**
 * Created on 2/1/2017.
 */

public class TafFragment extends Fragment implements TafView {

    private Unbinder unbinder;

    public TafController tafController;

    @BindView(R.id.text_view_raw_taf)
    TextView rawTaf;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_taf, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        tafController = new TafController(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void updateRawTaf(String taf) {
        rawTaf.setText(taf);
    }
}
