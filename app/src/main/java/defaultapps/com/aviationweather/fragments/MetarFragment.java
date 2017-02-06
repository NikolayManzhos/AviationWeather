package defaultapps.com.aviationweather.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import defaultapps.com.aviationweather.R;


/**
 * Created on 2/1/2017.
 */

public class MetarFragment extends Fragment{

    private Unbinder unbinder;

    @BindView(R.id.metar_raw)
    CardView metarRawCard;


    @BindView(R.id.text_view_raw_metar)
    TextView rawMetar;

    @BindView(R.id.progress_bar_metar)
    ProgressBar progressBar;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            String saved = savedInstanceState.getString("rawMetar");
            rawMetar.setText(saved);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_metar, container, false);
        unbinder = ButterKnife.bind(this, rootView);


        return rootView;
    }

    public void updateViews(String rawMetar) {
//        Log.i(MetarFragment.class.getName(), rawMetar);
        if (this.rawMetar != null) {
            this.rawMetar.setText(rawMetar);
        }
    }

    public void showProgressBar() {
        metarRawCard.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        metarRawCard.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!rawMetar.getText().toString().equals("")) {
            outState.putString("rawMetar", rawMetar.getText().toString());
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
