package hr.ferit.tumiljanovic.bbcnews.ui.newsDisplay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.tumiljanovic.bbcnews.App;
import hr.ferit.tumiljanovic.bbcnews.R;
import hr.ferit.tumiljanovic.bbcnews.listener.OnArticleClickListener;
import hr.ferit.tumiljanovic.bbcnews.pojo.Article;
import hr.ferit.tumiljanovic.bbcnews.presentation.NewsPresenter;
import hr.ferit.tumiljanovic.bbcnews.ui.adapter.ArticleAdapter;
import hr.ferit.tumiljanovic.bbcnews.ui.articleDisplay.ArticleDisplayActivity;

public class NewsDisplayActivity extends AppCompatActivity implements NewsDisplayContract.View, SwipeRefreshLayout.OnRefreshListener, OnArticleClickListener {

    private String EXTRA_ARTICLE_POSITION= "position";

    @BindView(R.id.articles)
    RecyclerView mArticles;
    @BindView(R.id.simpleSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ArticleAdapter mAdapter;
    private NewsDisplayContract.Presenter mPresenter;


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getArticles();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_display);
        ButterKnife.bind(this);

        mPresenter = new NewsPresenter(App.getInstance().getApiInteractor());
        mPresenter.setView(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        setUpRecyclerView();

        mPresenter.getArticles();

    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ArticleAdapter(this);
        mArticles.setLayoutManager(linearLayout);
        mArticles.setAdapter(mAdapter);
    }

    @Override
    public void showArticles(List<Article> articles) {
        mAdapter.updateArticles(articles);
    }

    @Override
    public void onFailure() {
        displayPopUpDialog();
    }

    private void displayPopUpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Greška")
                .setMessage("Ups, došlo je do pogreške.")
                .setCancelable(false)
                .setPositiveButton("U REDU", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
        mPresenter.getArticles();
    }

    @Override
    public void onClick(int adapterPosition) {
        Intent showArticle = new Intent();
        showArticle.setClass(this, ArticleDisplayActivity.class);
        showArticle.putExtra(EXTRA_ARTICLE_POSITION, adapterPosition);
        startActivity(showArticle);
    }
}
