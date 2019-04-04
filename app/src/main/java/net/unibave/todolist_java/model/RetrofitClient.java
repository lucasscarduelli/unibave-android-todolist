package net.unibave.todolist_java.model;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl("https://mother-heart-api.herokuapp.com/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        }

        return retrofit;
    }

}

