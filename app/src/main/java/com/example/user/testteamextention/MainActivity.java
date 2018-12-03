package com.example.user.testteamextention;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.testteamextention.adapter.ItemAdapter;
import com.example.user.testteamextention.data.ApiClient;
import com.example.user.testteamextention.data.ApiInterface;
import com.example.user.testteamextention.model.ItemObject;
import com.example.user.testteamextention.model.ItemResponse;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private Context context;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getBttn = findViewById(R.id.get_button);
        getBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadList(v);
            }
        });
    }


    private void loadList(View view){


            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            final Call<ItemResponse> call = apiInterface.getItemsList();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(@NonNull Call<ItemResponse> call, @NonNull Response<ItemResponse> response) {
                    List<ItemObject> items = response.body().getResults();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new ItemAdapter(context, items));
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.e(TAG, t.toString());

                }
            });

        }

    }


