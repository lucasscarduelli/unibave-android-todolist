package net.unibave.todolist_java.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TaskRestApi {

    String HEADER_APP_CONTEXT = "app-context: my-app";
    String HEADER_APP_TOKEN = "app-token: 762e43c0-8af8-4e38-af28-1ae7c2eda943";

    @Headers({ HEADER_APP_CONTEXT, HEADER_APP_TOKEN })
    @GET("mother-heart/")
    Call<List<Task>> findAll();

    @Headers({ HEADER_APP_CONTEXT, HEADER_APP_TOKEN })
    @POST("mother-heart/")
    Call<Response> create(@Body Task task);

    @Headers({ HEADER_APP_CONTEXT, HEADER_APP_TOKEN })
    @PUT("mother-heart/{id}")
    Call<Response> edit(@Path("id") String id, @Body Task task);

    @Headers({ HEADER_APP_CONTEXT, HEADER_APP_TOKEN })
    @DELETE("mother-heart/{id}")
    Call<Response> delete(@Path("id") String id);

}



