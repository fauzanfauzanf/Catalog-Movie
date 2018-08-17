package io.fikrims.cataloguemovie.data.remote;

import io.fikrims.cataloguemovie.data.model.response.Movie;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by mfahm on 11/24/2017.
 */

public interface BaseApiService {
    @GET("movie/now_playing")
    Flowable<Movie> getNowPlayingMovie(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Flowable<Movie> getUpcomingMovie(@Query("api_key") String apiKey);

    @GET("search/movie/")
    Flowable<Movie> getMovieBySearch(@Query("query") String query, @Query("api_key") String apiKey);
}
