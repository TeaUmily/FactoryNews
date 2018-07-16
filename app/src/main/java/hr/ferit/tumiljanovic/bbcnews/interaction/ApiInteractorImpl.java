package hr.ferit.tumiljanovic.bbcnews.interaction;

import hr.ferit.tumiljanovic.bbcnews.networking.ApiService;
import hr.ferit.tumiljanovic.bbcnews.pojo.ArticleList;
import retrofit2.Callback;

public class ApiInteractorImpl implements ApiInteractor {

    private final ApiService mApiService;

    public ApiInteractorImpl(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void getArticles(Callback<ArticleList> callback) {
        mApiService.getArticles().enqueue(callback);
    }
}
