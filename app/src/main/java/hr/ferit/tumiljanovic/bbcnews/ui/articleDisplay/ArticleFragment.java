package hr.ferit.tumiljanovic.bbcnews.ui.articleDisplay;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hr.ferit.tumiljanovic.bbcnews.App;
import hr.ferit.tumiljanovic.bbcnews.R;
import hr.ferit.tumiljanovic.bbcnews.constants.Constants;
import hr.ferit.tumiljanovic.bbcnews.pojo.Article;
import hr.ferit.tumiljanovic.bbcnews.presentation.ArticlePresenter;


public class ArticleFragment extends Fragment implements ArticleDisplayContract.View {

    private Unbinder mUnbinder;

    @BindView(R.id.imageview_imageArticle)
    ImageView mImageView;
    @BindView(R.id.text_title)
    TextView mTitle;
    @BindView(R.id.textview_description)
    TextView mDescription;


    private ArticleDisplayContract.Presenter mPresenter;

    public static ArticleFragment newInstance(int position) {
        Bundle data = new Bundle();
        data.putInt(Constants.DATA_POSITION_KEY, position);
        ArticleFragment f = new ArticleFragment();
        f.setArguments(data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter = new ArticlePresenter();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int position = getArguments().getInt(Constants.DATA_POSITION_KEY);

        mPresenter.setView(this);

        mPresenter.getArticle(position);
    }

    @Override
    public void displayArticle(Article article) {

        mTitle.setText(article.getTitle());
        Glide.with(App.getInstance().getApplicationContext())
                .load(article.getImageUrl()).into(mImageView);
    }

}
