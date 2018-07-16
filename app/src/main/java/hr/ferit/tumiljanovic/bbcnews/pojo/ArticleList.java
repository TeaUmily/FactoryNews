package hr.ferit.tumiljanovic.bbcnews.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleList {

    @Expose
    @SerializedName("articles")
    public List<Article> mArticleList;

}
