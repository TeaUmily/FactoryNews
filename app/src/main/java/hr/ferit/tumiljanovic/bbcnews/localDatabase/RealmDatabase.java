package hr.ferit.tumiljanovic.bbcnews.localDatabase;



import java.util.List;

import hr.ferit.tumiljanovic.bbcnews.pojo.Article;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmDatabase {


    private static RealmDatabase sDatabase = null;
    private Realm mRealm;

    private RealmDatabase(){
        mRealm = Realm.getDefaultInstance();
    }

    public static synchronized RealmDatabase getInstance(){
        if(sDatabase == null){
            sDatabase = new RealmDatabase();
        }
        return sDatabase;
    }

    public List<Article> getArticles() {
        RealmResults<Article> articles = mRealm.where(Article.class).findAll();
        return articles;
    }

    public void updateArticles(List<Article> articles){
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(articles);
        mRealm.commitTransaction();
    }

    public Article getArticleByPosition(int position) {

        RealmResults<Article> articles = mRealm.where(Article.class).findAll();
        Article article = articles.get(position);

        return article;
    }
}
