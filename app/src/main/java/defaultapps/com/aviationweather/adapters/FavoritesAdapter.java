package defaultapps.com.aviationweather.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Set;

import defaultapps.com.aviationweather.R;

/**
 * Created on 1/31/2017.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> implements View.OnClickListener {

    private Set<String> favAirports;

    public FavoritesAdapter(Set<String> favorite) {
        favAirports = favorite;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView inflatedView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(FavoritesAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        cardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getItemCount() {
        return favAirports.size();
    }
}
