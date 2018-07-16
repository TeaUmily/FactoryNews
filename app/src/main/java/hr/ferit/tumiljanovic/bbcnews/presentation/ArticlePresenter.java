package hr.ferit.tumiljanovic.bbcnews.presentation;


import hr.ferit.tumiljanovic.bbcnews.localDatabase.RealmDatabase;
import hr.ferit.tumiljanovic.bbcnews.pojo.Article;
import hr.ferit.tumiljanovic.bbcnews.ui.articleDisplay.ArticleDisplayContract;

public class ArticlePresenter implements ArticleDisplayContract.Presenter {

    private ArticleDisplayContract.View mArticleDisplayView;
    private RealmDatabase mDatabase;

    public ArticlePresenter() {
        mDatabase = RealmDatabase.getInstance();
    }

    @Override
    public void setView(ArticleDisplayContract.View view) {
        mArticleDisplayView = view;
    }

    @Override
    public void getArticle(int position) {

        Article tempArticle = mDatabase.getArticleByPosition(position);
        mArticleDisplayView.displayArticle(tempArticle);
    }
}
