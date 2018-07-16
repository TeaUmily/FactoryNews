package hr.ferit.tumiljanovic.bbcnews.pojo;


import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
public class Article extends RealmObject{

    @Required
    @PrimaryKey
    @Expose
    @SerializedName("title")
    private String mTitle;
    @Expose
    @SerializedName("urlToImage")
    private String mImageUrl;
    private String mText;


    public Article(String mTitle, String mImageUrl, String mText) {
        this.mTitle = mTitle;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
    }

    public Article() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }


}
