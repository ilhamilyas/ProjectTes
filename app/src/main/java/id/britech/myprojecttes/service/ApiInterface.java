package id.britech.myprojecttes.service;

import android.provider.Contacts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("positions.json")
    Call<List<ResponseJobs>> getJobApi();

    @GET("positions/{id}")
    Call<ResponseJobs> getDetail(@Path("id") String id);

}
