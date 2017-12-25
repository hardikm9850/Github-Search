package com.github.example.network;

import com.github.example.model.Item;
import com.github.example.model.RepoResponse;
import com.github.example.model.contributor.Contributor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;

/**
 * Created by hardik on 23/12/17.
 */

public interface APIService {

    /**
     * @param repoName  remo name to search
     * @param sortBy    sort by watch count
     * @param orderBy   order by desc
     * @param limit     10
     * @param pageIndex 1
     * @return repo response containing result
     */
    @GET("search/repositories")
    Observable<RepoResponse> getRepositories(@Query("q") String repoName, @Query("sort") String sortBy,
                                             @Query("order") String orderBy, @Query("per_page") int limit, @Query("page") int pageIndex);


    /**
     * Returns list of contributors of this repo
     *
     * @param owner    repo owner
     * @param repoName repo name
     * @return list of contributors
     */
    ///repos/:owner/:repo/contributors
    @GET("repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> getContributors(@Path("owner") String owner, @Path("repo") String repoName);

    /**
     * Returns list of repositories if this owner
     *
     * @param str owner name
     * @return list containing repositories of this contributors
     */

    @GET("users/{owner}/repos")
    Observable<ArrayList<Item>> getOwnerRepositories(@Path("owner") String str);

}
