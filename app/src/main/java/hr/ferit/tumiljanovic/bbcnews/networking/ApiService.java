package hr.ferit.tumiljanovic.bbcnews.networking;

import hr.ferit.tumiljanovic.bbcnews.pojo.ArticleList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("articles?apiKey=6946d0c07a1c4555a4186bfcade76398&sortBy=top&source=bbc-news")
    Call<ArticleList> getArticles();

}
