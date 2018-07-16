package hr.ferit.tumiljanovic.bbcnews.ui.newsDisplay;

import java.util.List;

import hr.ferit.tumiljanovic.bbcnews.pojo.Article;

public interface NewsDisplayContract {

    public interface View{

        void showArticles(List<Article> articles);

        void onFailure();
    }


    public interface Presenter{

        void setView(NewsDisplayContract.View view);

        void getArticles();

    }
}
