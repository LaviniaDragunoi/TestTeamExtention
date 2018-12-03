package com.example.user.testteamextention.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.user.testteamextention.R;
import com.example.user.testteamextention.SkuDetailsActivity;
import com.example.user.testteamextention.model.ItemObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    public static final String SKU_NAME = "skuName" ;
    public static final String SKU_AMOUNT = "amount" ;
    public static final String SKU_CURRENCY = "currency";
    public static final String ITEMS = "itemsList" ;
    private ArrayList<ItemObject> items;
    private Context context;

    public ItemAdapter(Context context, ArrayList<ItemObject> items){
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
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        final ItemObject itemObjects = items.get(position);
       TextView skuName = holder.skuNameTextView;
       final String skuNameString =itemObjects.getSku();
       skuName.setText(skuNameString);
       TextView amount = holder.amountTextView;
       final String amountString = itemObjects.getAmount().toString();
       amount.setText(amountString);
       TextView currency = holder.currencyTextView;
       final String currencySTring = itemObjects.getCurrency();
       currency.setText(currencySTring);

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(holder.itemView.getContext(), SkuDetailsActivity.class);
               intent.putParcelableArrayListExtra(ITEMS, items);
               intent.putExtra(SKU_NAME,skuNameString);
             //  intent.putExtra(SKU_AMOUNT, itemObjects.getAmount());
               intent.putExtra(SKU_CURRENCY, currencySTring);
               holder.itemView.getContext().startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        if(items.size() == 0) return 0;
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
