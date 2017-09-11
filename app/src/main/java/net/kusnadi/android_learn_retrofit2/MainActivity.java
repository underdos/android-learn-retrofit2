package net.kusnadi.android_learn_retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import net.kusnadi.android_learn_retrofit2.data.model.Item;
import net.kusnadi.android_learn_retrofit2.data.model.SOAnswerResponse;
import net.kusnadi.android_learn_retrofit2.data.remote.ApiUtils;
import net.kusnadi.android_learn_retrofit2.data.remote.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AnswersAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;

    @Override
    protected void onCreate (Bundle savedInstanceState)  {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_main );

        mService = ApiUtils.getSOService();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        loadAnswers();
    }

    public void loadAnswers() {
        mService.getAnswers().enqueue(new Callback<SOAnswerResponse>() {
            @Override
            public void onResponse(Call<SOAnswerResponse> call, Response<SOAnswerResponse> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOAnswerResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    private void showErrorMessage() {
        Toast.makeText(this, "Error loading Api", Toast.LENGTH_SHORT).show();
    }

}
