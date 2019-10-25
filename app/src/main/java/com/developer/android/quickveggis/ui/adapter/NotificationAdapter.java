package com.developer.android.quickveggis.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.android.quickveggis.App;
import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.model.History;
import com.developer.android.quickveggis.model.Notification;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotificationAdapter extends Adapter<ViewHolder> {
    Context context;
    List<Notification> data;

    public class NotificationHolder extends ViewHolder {
        @Bind(R.id.txtDesc)
        TextView txtDesc;
        @Bind(R.id.txtDateTime)
        TextView txtTime;

        public NotificationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind((Object) this, itemView);
        }
    }

    public NotificationAdapter(List<Notification> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NotificationHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationHolder notificationHolder = (NotificationHolder) holder;
        Notification notification = (Notification) this.data.get(position);
        ((NotificationHolder) holder).txtDesc.setText(notification.getContent());
        ((NotificationHolder) holder).txtTime.setText(notification.getDateTime());
    }

    public int getItemCount() {
        return this.data.size();
    }
}
