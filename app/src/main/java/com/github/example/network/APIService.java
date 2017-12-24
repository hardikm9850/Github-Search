package com.github.example.network;

import com.github.example.model.RepoResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;

/**
 * Created by hardik on 23/12/17.
 */

public interface APIService {

    /**
     *
     * @param repoName remo name to search
     * @param sortBy sort by watch count
     * @param orderBy order by desc
     * @param limit 10
     * @param pageIndex 1
     * @return repo response containing result
     */
    @GET("search/repositories")
    Observable<RepoResponse> getRepositories(@Query("q") String repoName, @Query("sort") String sortBy,
                                             @Query("order") String orderBy, @Query("per_page") int limit, @Query("page") int pageIndex);

}
