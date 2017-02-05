package defaultapps.com.aviationweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import defaultapps.com.aviationweather.R;
import defaultapps.com.aviationweather.controllers.TafController;

/**
 * Created on 2/1/2017.
 */

public class TafFragment extends Fragment {

    private Unbinder unbinder;

    public TafController tafController;

    @BindView(R.id.text_view_raw_taf)
    TextView rawTaf;

    @BindView(R.id.refresh_layout_taf)
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
                if (savedInstanceState != null) {
            rawTaf.setText(savedInstanceState.getString("rawTaf"));
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_taf, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!rawTaf.getText().toString().equals("")) {
            outState.putString("rawTaf", rawTaf.getText().toString());
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void updateViews(String tafRaw) {
        if (rawTaf != null) {
            rawTaf.setText(tafRaw);
        }

    }
}
