package hr.ferit.tumiljanovic.bbcnews.interaction;

import hr.ferit.tumiljanovic.bbcnews.pojo.ArticleList;
import retrofit2.Callback;

public interface ApiInteractor {

    void getArticles(Callback<ArticleList> callback);

}
