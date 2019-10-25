package com.developer.android.quickveggis.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.model.Card;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.quickveggies.quickveggies.ui.custom.PagerContainer;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends PagerAdapter {
    List<Card> cards;
    Context context;
    Listener listener;
    PagerContainer pagerContainer;

    /* renamed from: com.quickveggies.quickveggies.ui.adapter.CardAdapter.1 */
    class myOnClickListener implements OnClickListener {
        final /* synthetic */ Card mCard;

        myOnClickListener(Card card) {
            mCard = card;
        }

        public void onClick(View v) {
            listener.onCardSelected(mCard);
        }
    }

    public interface Listener {
        void onCardSelected(Card card);
    }

    public CardAdapter(Context context, List<Card> stats, PagerContainer container, Listener listener) {
        this.context = context;
        this.cards = stats;
        pagerContainer = container;
        this.listener = listener;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Card card = (Card) cards.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, container, false);

        RoundedTransformationBuilder rt = new RoundedTransformationBuilder()
                .borderWidthDp(0.0f)
                .cornerRadiusDp(15.0f)
                .oval(false);

        Picasso.with(context).load(Uri.parse(card.getLink())).fit().centerCrop().transform(rt.build()).into((ImageView) view.findViewById(R.id.img));

        view.setTag(Integer.valueOf(position));
        container.addView(view);
//        view.setOnClickListener(new C02701(card));
        pagerContainer.onPageSelected(0);
        view.setOnClickListener(new myOnClickListener(card));

        return view;
    }



    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public int getItemPosition(Object object) {
        return -2;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public int getCount() {
        return cards.size();
    }
}
