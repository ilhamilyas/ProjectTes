package id.britech.myprojecttes;

import androidx.appcompat.app.AppCompatActivity;
import id.britech.myprojecttes.service.ApiClient;
import id.britech.myprojecttes.service.ApiInterface;
import id.britech.myprojecttes.service.ResponseJobs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class DetailJobsActivity extends AppCompatActivity {

    TextView txtTitle, txtType, txtDesc, txtCompany, txtLocation, txtGoWeb;
    ImageView imgCompanyLogo;
    ApiInterface apiInterface;
    String id;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jobs);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        txtTitle = findViewById(R.id.title);
        txtType = findViewById(R.id.type);
        txtDesc = findViewById(R.id.desc);
        txtCompany = findViewById(R.id.companyName);
        txtLocation =findViewById(R.id.location);
        txtGoWeb = findViewById(R.id.goWebsite);
        imgCompanyLogo =findViewById(R.id.companyLogo);
        responseGetDetail();

        txtGoWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });



    }
    private void responseGetDetail() {
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        Call<ResponseJobs> api = apiInterface.getDetail(id+".json");
        api.enqueue(new Callback<ResponseJobs>() {
            @Override
            public void onResponse(Call<ResponseJobs> call, Response<ResponseJobs> response) {
                if (response.isSuccessful()) {
                    txtCompany.setText(response.body().getCompany());
                    txtLocation.setText(response.body().getLocation());
                    txtTitle.setText(response.body().getTitle());
                    txtDesc.setText(Html.fromHtml(response.body().getDescription()));
                    url=response.body().getUrl();

                    Picasso.get()
                            .load(response.body().getCompanyLogo())
                            .into(imgCompanyLogo);
                    if (response.body().getType().equals("Full Time")){
                        txtType.setText("Yes");
                    }else {
                        txtType.setText("No");
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseJobs> call, Throwable t) {
                Toast.makeText(DetailJobsActivity.this,"gagal login", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}