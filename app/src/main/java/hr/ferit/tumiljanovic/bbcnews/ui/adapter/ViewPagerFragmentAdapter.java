package hr.ferit.tumiljanovic.bbcnews.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import hr.ferit.tumiljanovic.bbcnews.App;
import hr.ferit.tumiljanovic.bbcnews.pojo.Article;
import hr.ferit.tumiljanovic.bbcnews.ui.articleDisplay.ArticleFragment;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {


    private final List<Article> mArticles;

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        mArticles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return ArticleFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mArticles.size();
    }

    public void setAdapterData(List<Article> articles) {
            this.mArticles.clear();
            this.mArticles.addAll(articles);
            notifyDataSetChanged();

    }

}
