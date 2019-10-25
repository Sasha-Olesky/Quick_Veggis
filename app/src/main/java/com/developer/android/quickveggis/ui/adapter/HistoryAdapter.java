package com.developer.android.quickveggis.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.App;
import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.model.History;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import java.util.List;

public class HistoryAdapter extends Adapter<ViewHolder> implements StickyRecyclerHeadersAdapter<ViewHolder> {
    Context context;
    List<History> data;

    public class HeaderHolder extends ViewHolder {
        @Bind(R.id.txtTitle)
        TextView txtTitle;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind((Object) this, itemView);
        }
    }

    public class HistoryHolder extends ViewHolder {
        @Bind(R.id.txtStatus)
        TextView txtStatus;
        @Bind(R.id.txtTitle)
        TextView txtTitle;

        public HistoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind((Object) this, itemView);
        }
    }

    public HistoryAdapter(List<History> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryHolder historyHolder = (HistoryHolder) holder;
        History history = (History) this.data.get(position);
        historyHolder.txtTitle.setText(history.getName());
        if (history.getStatus().equals(History.STATUS_COMPLETED)) {
            historyHolder.txtStatus.setText("67$");
            historyHolder.txtStatus.setTextColor(ContextCompat.getColor(App.getContext(), R.color.mainGreen));
            return;
        }
        historyHolder.txtStatus.setText(history.getStatus());
        historyHolder.txtStatus.setTextColor(ContextCompat.getColor(App.getContext(), R.color.mainRed));
    }

    public int getItemCount() {
        return this.data.size();
    }

    public long getHeaderId(int position) {
        int hash = ((History) this.data.get(position)).getStatus().hashCode();
        return hash < 0 ? (long) (-hash) : (long) hash;
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new HeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_header, parent, false));
    }

    public void onBindHeaderViewHolder(ViewHolder holder, int position) {
        ((HeaderHolder) holder).txtTitle.setText(((History) this.data.get(position)).getStatus());
    }
}
