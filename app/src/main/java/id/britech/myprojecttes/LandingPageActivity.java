package id.britech.myprojecttes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.britech.myprojecttes.service.ApiClient;
import id.britech.myprojecttes.service.ApiInterface;
import id.britech.myprojecttes.service.ResponseJobs;
import id.britech.myprojecttes.service.itemModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import android.os.Bundle;
import android.util.Property;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LandingPageActivity extends AppCompatActivity {
    RecyclerView rvJobs;
    ApiInterface apiInterface;
    JobsAdapter jobsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        responseGetLokasi();
        rvJobs = findViewById(R.id.rvJobs);
        rvJobs.setHasFixedSize(true);
        rvJobs.setLayoutManager(new LinearLayoutManager(this));
        jobsAdapter = new JobsAdapter(this);
        rvJobs.setAdapter(jobsAdapter);
    }

    private void responseGetLokasi() {
        Call<List<ResponseJobs>> api = apiInterface.getJobApi();
        api.enqueue(new Callback<List<ResponseJobs>>() {
            @Override
            public void onResponse(Call<List<ResponseJobs>> call, Response<List<ResponseJobs>> response) {
                if (response.isSuccessful()) {
                        jobsAdapter.setGetItemList(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<ResponseJobs>> call, Throwable t) {
                Toast.makeText(LandingPageActivity.this,"gagal login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}