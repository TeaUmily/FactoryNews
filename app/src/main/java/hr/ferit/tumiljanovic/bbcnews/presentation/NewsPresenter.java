package hr.ferit.tumiljanovic.bbcnews.presentation;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;

import java.util.List;

import hr.ferit.tumiljanovic.bbcnews.interaction.ApiInteractor;
import hr.ferit.tumiljanovic.bbcnews.localDatabase.RealmDatabase;
import hr.ferit.tumiljanovic.bbcnews.pojo.Article;
import hr.ferit.tumiljanovic.bbcnews.pojo.ArticleList;
import hr.ferit.tumiljanovic.bbcnews.ui.newsDisplay.NewsDisplayContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresenter implements NewsDisplayContract.Presenter {

    private final ApiInteractor mApiInteractor;
    private NewsDisplayContract.View mNewsView;
    private RealmDatabase mRealmDatabase;

    private CountDownTimer mTimer;
    private Boolean canRefresh = true;

    public NewsPresenter(ApiInteractor apiInteractor) {
        this.mApiInteractor = apiInteractor;
        mRealmDatabase = RealmDatabase.getInstance();
    }

    public void setView(NewsDisplayContract.View view){
        this.mNewsView = view;
    }

    @Override
    public void getArticles() {
        if(canRefresh){
            mApiInteractor.getArticles(getArticleCallback());
        }
        else {
            mNewsView.showArticles(mRealmDatabase.getArticles());
        }
    }


    private Callback<ArticleList> getArticleCallback() {
        return new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    canRefresh = false;
                    setTimer();
                    List<Article> mArticles = response.body().mArticleList;
                    mRealmDatabase.updateArticles(mArticles);
                    mNewsView.showArticles(mRealmDatabase.getArticles());
                }
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {
                mNewsView.onFailure();
            }
        };
    }


    private void setTimer() {
        mTimer = new CountDownTimer(300000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                canRefresh = true;
            }
        }.start();
    }
}
