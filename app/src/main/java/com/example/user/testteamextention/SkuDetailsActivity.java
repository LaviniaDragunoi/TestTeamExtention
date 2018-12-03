package com.example.user.testteamextention;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

     @BindView(R.id.all_transactions)
     TextView allTransactionsAmount;

     String skuIdString;
     ArrayList<ItemObject> itemsList;
     String currencySKU;

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
    }

    private void display() {
        skuIdTV.setText(skuIdString);

    }

    private double getTotal(ArrayList<ItemObject> itemsList){
        double total = 0;
        for(int i=0; i < itemsList.size(); i++){
            if(itemsList.get(i).getSku().equals(skuIdString)){

            }
        }
        return total;
    }

    private double exchangeCurrency(final String currencySKU){

        final double[] exchangeRateForTheCurrencySKU = new double[0];
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<List<ExchangeObject>> call = apiInterface.getExchangeRate();
                call.enqueue(new Callback<List<ExchangeObject>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ExchangeObject>> call,
                                           @NonNull Response<List<ExchangeObject>> response) {

                        List<ExchangeObject> exchangeRates = response.body();
                        for(int i=0;i< exchangeRates.size(); i++){
                            if(currencySKU.equals(exchangeRates.get(i).getFrom())&& exchangeRates.get(i).getTo().equals("Gold")){
                               exchangeRateForTheCurrencySKU[0] = exchangeRates.get(i).getRate(); 
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ExchangeObject>> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }

                });

            }

        });
return exchangeRateForTheCurrencySKU[0];
    }
}
