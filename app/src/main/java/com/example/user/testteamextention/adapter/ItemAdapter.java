package com.example.user.testteamextention.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.user.testteamextention.R;
import com.example.user.testteamextention.model.ItemObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<ItemObject> items;
    private Context context;

    public ItemAdapter(Context context, List<ItemObject> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemObject itemObjects = items.get(position);
       TextView skuName = holder.skuNameTextView;
       String skuNameString =itemObjects.getSku();
       skuName.setText(skuNameString);
       TextView amount = holder.amountTextView;
       String amountString = itemObjects.getAmount().toString();
       amount.setText(amountString);
       TextView currency = holder.currencyTextView;
       String currencySTring = itemObjects.getCurrency();
       currency.setText(currencySTring);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.sku)
        TextView skuNameTextView;
        @BindView(R.id.amount)
        TextView amountTextView;
        @BindView(R.id.currency)
        TextView currencyTextView;

        public ItemViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
