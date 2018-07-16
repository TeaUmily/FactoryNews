package hr.ferit.tumiljanovic.bbcnews.ui.articleDisplay;

import hr.ferit.tumiljanovic.bbcnews.pojo.Article;

public interface ArticleDisplayContract {

    public interface View {

        void displayArticle(Article article);
    }

    public interface Presenter {

        void setView(ArticleDisplayContract.View view);

        void getArticle(int position);
    }


}
