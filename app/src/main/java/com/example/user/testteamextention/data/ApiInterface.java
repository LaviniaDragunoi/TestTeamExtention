package com.example.user.testteamextention.data;

import com.example.user.testteamextention.model.ExchangeObject;
import com.example.user.testteamextention.model.ItemObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("transactions.json")
    Call<List<ItemObject>> getItemsList();

    @GET("rates.json")
    Call<List<ExchangeObject>> getExchangeRate();
}
