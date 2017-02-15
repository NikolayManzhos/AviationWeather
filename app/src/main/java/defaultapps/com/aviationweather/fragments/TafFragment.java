package defaultapps.com.aviationweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import defaultapps.com.aviationweather.R;
import icepick.Icepick;
import icepick.State;

/**
 * Created on 2/1/2017.
 */

public class TafFragment extends Fragment {

    private Unbinder unbinder;
    @State
    ArrayList<String> data;

    @State
    int progressBarState;

    @BindView(R.id.taf_data)
    LinearLayout tafData;

    @BindView(R.id.progress_bar_taf)
    ProgressBar progressBar;

    @BindView(R.id.text_view_raw_taf)
    TextView rawTaf;

    @BindView(R.id.welcome_hint)
    IconTextView welcomeHint;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            rawTaf.setText(savedInstanceState.getString("rawTaf"));
            if (progressBarState == View.VISIBLE) {
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
        progressBarState = progressBar.getVisibility();
        Icepick.saveInstanceState(this, outState);
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


    public void updateViews(List<String> data) {
        this.data = (ArrayList<String>) data;
        if (rawTaf != null) {
            rawTaf.setText(data.get(0));
            hideProgressBar();
        }
    }
}
