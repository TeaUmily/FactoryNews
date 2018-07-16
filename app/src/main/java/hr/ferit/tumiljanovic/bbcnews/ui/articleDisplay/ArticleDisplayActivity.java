package hr.ferit.tumiljanovic.bbcnews.ui.articleDisplay;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.tumiljanovic.bbcnews.R;
import hr.ferit.tumiljanovic.bbcnews.localDatabase.RealmDatabase;
import hr.ferit.tumiljanovic.bbcnews.ui.adapter.ViewPagerFragmentAdapter;

public class ArticleDisplayActivity extends AppCompatActivity {

    public static String EXTRA_ARTICLE_POSITION = "position";

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private ViewPagerFragmentAdapter mAdapter;
    private RealmDatabase mDatabase;
    private int mPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_display);
        ButterKnife.bind(this);

        mDatabase = RealmDatabase.getInstance();

        setAdapter(getSupportFragmentManager());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    public void setAdapter(FragmentManager supportFragmentManager) {

        mPosition = getIntent().getIntExtra(EXTRA_ARTICLE_POSITION, 0);

        this.mAdapter = new ViewPagerFragmentAdapter(supportFragmentManager);
        mAdapter.setAdapterData(mDatabase.getArticles());

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mPosition);

    }
}
