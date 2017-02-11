package defaultapps.com.aviationweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;

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

    @BindView(R.id.taf_data)
    LinearLayout tafData;

    @BindView(R.id.progress_bar_taf)
    ProgressBar progressBar;

    @BindView(R.id.text_view_raw_taf)
    TextView rawTaf;

    @BindView(R.id.welcome_hint)
    IconTextView welcomeHint;

    private final String PROGRESS_BAR_STATE = "progressBarState";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            rawTaf.setText(savedInstanceState.getString("rawTaf"));
            if (savedInstanceState.getInt(PROGRESS_BAR_STATE) == View.VISIBLE) {
                showProgressBar();
            } else {
                displayDataBlock();
            }
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
        outState.putInt(PROGRESS_BAR_STATE, progressBar.getVisibility());
    }

    public void showProgressBar() {
        tafData.setVisibility(View.GONE);
        welcomeHint.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        displayDataBlock();
        progressBar.setVisibility(View.GONE);
    }

    private void displayDataBlock() {
        if (rawTaf.getText().toString().equals("")) {
            tafData.setVisibility(View.GONE);
            welcomeHint.setVisibility(View.VISIBLE);
        } else {
            tafData.setVisibility(View.VISIBLE);
            welcomeHint.setVisibility(View.GONE);
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
            hideProgressBar();
        }
    }
}
