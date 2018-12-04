package com.example.user.testteamextention;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.user.testteamextention.adapter.ItemAdapter;
import com.example.user.testteamextention.data.ApiClient;
import com.example.user.testteamextention.data.ApiInterface;
import com.example.user.testteamextention.model.ExchangeObject;
import com.example.user.testteamextention.model.ItemObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.user.testteamextention.adapter.ItemAdapter.ITEMS;
import static com.example.user.testteamextention.adapter.ItemAdapter.SKU_CURRENCY;
import static com.example.user.testteamextention.adapter.ItemAdapter.SKU_NAME;

public class SkuDetailsActivity extends AppCompatActivity {

    private static final String TAG = SkuDetailsActivity.class.getName() ;
    @BindView(R.id.sku_id_tv)
     TextView skuIdTV;

     @BindView(R.id.all_transactions_rv)
     RecyclerView skuRecyclerView;

     String skuIdString;
     ArrayList<ItemObject> itemsList;
     String currencySKU;
    List<ExchangeObject> exchangeRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sku_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if(intent != null){
            skuIdString = intent.getStringExtra(SKU_NAME);
            itemsList = intent.getParcelableArrayListExtra(ITEMS);
            currencySKU = intent.getStringExtra(SKU_CURRENCY);

        }
       display();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<List<ExchangeObject>> call = apiInterface.getExchangeRate();
                call.enqueue(new Callback<List<ExchangeObject>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ExchangeObject>> call,
                                           @NonNull Response<List<ExchangeObject>> response) {

                        exchangeRates = response.body();
                        for (int i = 0; i < Objects.requireNonNull(exchangeRates).size(); i++){

                        }

                    }

                    @Override
                    public void onFailure(Call<List<ExchangeObject>> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }

                });

            }

        });


    }

    private void display() {

        skuIdTV.setText(skuIdString);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_separator));

        skuRecyclerView.addItemDecoration(itemDecoration);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SkuDetailsActivity.this);
        skuRecyclerView.setLayoutManager(layoutManager);
        skuRecyclerView.setAdapter(new ItemAdapter(SkuDetailsActivity.this, itemsList));
    }

    private double exchangeRateInGold(double currency, double rate){
        return currency/rate;
    }


}
