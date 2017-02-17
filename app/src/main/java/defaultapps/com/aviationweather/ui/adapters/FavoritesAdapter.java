package defaultapps.com.aviationweather.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconButton;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import defaultapps.com.aviationweather.R;

/**
 * Created on 1/31/2017.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<String> favAirports;
    private Listener listener;

    public FavoritesAdapter(List<String> favorite) {
        favAirports = favorite;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        ViewHolder(CardView v) {
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
    public void onBindViewHolder(final FavoritesAdapter.ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        IconTextView iconTextView = (IconTextView) cardView.findViewById(R.id.fav_air_code);
        iconTextView.setText(favAirports.get(position));
        IconButton deleteButton = (IconButton) cardView.findViewById(R.id.fav_delete_button);
        deleteButton.setText("{md-clear}");
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(favAirports.get(holder.getAdapterPosition()));
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPosition = holder.getAdapterPosition();
                listener.onDeleteClick(favAirports.get(newPosition));
                favAirports.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemRangeChanged(newPosition, favAirports.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return favAirports.size();
    }

    public interface Listener {
        void onClick(String airportCode);
        void onDeleteClick(String airportCode);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
