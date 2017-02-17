package defaultapps.com.aviationweather.ui.fragments;

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

public class MetarFragment extends Fragment{

    private Unbinder unbinder;

    @State
    ArrayList<String> data;

    @State
    int progressBarState;


    @BindView(R.id.metar_data)
    LinearLayout metarData;


    @BindView(R.id.text_view_raw_metar)
    TextView rawMetar;

    @BindView(R.id.metar_data_altimeter)
    TextView altimeterMetar;

    @BindView(R.id.metar_data_clouds)
    TextView cloudsMetar;

    @BindView(R.id.metar_data_dewpoint)
    TextView dewpointMetar;

    @BindView(R.id.metar_data_temperature)
    TextView temperatureMetar;

    @BindView(R.id.metar_data_visibility)
    TextView visibilityMetar;

    @BindView(R.id.metar_data_wind)
    TextView windMetar;

    @BindView(R.id.metar_data_other)
    TextView otherMetar;

    @BindView(R.id.welcome_hint)
    IconTextView welcomeHint;

    @BindView(R.id.progress_bar_metar)
    ProgressBar progressBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            if (data != null) {
                updateViews(data);
            }
            if (progressBarState == View.VISIBLE) {
                showProgressBar();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_metar, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    public void updateViews(List<String> data) {
//        Log.i(MetarFragment.class.getName(), rawMetar);
        this.data = (ArrayList<String>) data;
        if (rawMetar != null) {
            rawMetar.setText(data.get(0));
            altimeterMetar.setText(data.get(1));
            cloudsMetar.setText(data.get(2));
            dewpointMetar.setText(data.get(3));
            temperatureMetar.setText(data.get(4));
            visibilityMetar.setText(data.get(5));
            windMetar.setText(data.get(6));
            otherMetar.setText(data.get(7));
            hideProgressBar();
        }
    }

    public void showProgressBar() {
        metarData.setVisibility(View.GONE);
        welcomeHint.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        displayDataBlock();
        progressBar.setVisibility(View.GONE);
    }

    private void displayDataBlock() {
        if (data == null) {
            metarData.setVisibility(View.GONE);
            welcomeHint.setVisibility(View.VISIBLE);
        } else {
            metarData.setVisibility(View.VISIBLE);
            welcomeHint.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        progressBarState = progressBar.getVisibility();
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
