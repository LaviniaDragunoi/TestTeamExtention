package com.example.user.testteamextention;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private Context context;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.get_button)
    Button getBttn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_separator));

        recyclerView.addItemDecoration(itemDecoration);
        getBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {

                        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                        Call<List<ItemObject>> call = apiInterface.getItemsList();
                        call.enqueue(new Callback<List<ItemObject>>() {
                            @Override
                            public void onResponse(@NonNull Call<List<ItemObject>> call,
                                                   @NonNull Response<List<ItemObject>> response) {

                                List<ItemObject> items = response.body();
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

                                recyclerView.setLayoutManager(layoutManager);

                                ArrayList<ItemObject> itemsArray = convert(items);
                                recyclerView.setAdapter(new ItemAdapter(MainActivity.this, itemsArray));

                            }

                            @Override
                            public void onFailure(Call<List<ItemObject>> call, Throwable t) {
                                Log.e(TAG, t.toString());
                            }

                        });

                    }

                });

                        Toast.makeText(MainActivity.this, getString(R.string.loading_message), Toast.LENGTH_SHORT).show();
            }
        });



    }
    public ArrayList<ItemObject> convert(List<ItemObject> convert) {
        return new ArrayList<>(convert);
    }

    }


