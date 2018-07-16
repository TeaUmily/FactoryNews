package hr.ferit.tumiljanovic.bbcnews.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.ferit.tumiljanovic.bbcnews.App;
import hr.ferit.tumiljanovic.bbcnews.R;
import hr.ferit.tumiljanovic.bbcnews.listener.OnArticleClickListener;
import hr.ferit.tumiljanovic.bbcnews.pojo.Article;
import io.realm.Realm;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.TaskViewHolder> {

    private List<Article> mArticles;
    private Realm mRealm;
    private OnArticleClickListener mOnClickListener;


    public ArticleAdapter(OnArticleClickListener onClickListener) {
        mArticles = new ArrayList<>();
        mRealm = Realm.getDefaultInstance();
        mOnClickListener = onClickListener;
    }

    public void updateArticles(List<Article> articles) {
        mArticles.clear();
        mArticles.addAll(articles);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.TaskViewHolder holder, int position) {

        final Article current = mArticles.get(position);

       Glide.with(App.getInstance().getApplicationContext())
                .load(current.getImageUrl()).into(holder.mImage);
        holder.mTitle.setText(current.getTitle());

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }




    public class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview_articleImage)
        ImageView mImage;
        @BindView(R.id.textview_title)
        TextView mTitle;

        public TaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnClick
        public void onArticleClick(){
            mOnClickListener.onClick(getAdapterPosition());
        }
    }

}
