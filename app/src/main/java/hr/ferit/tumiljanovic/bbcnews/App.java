package hr.ferit.tumiljanovic.bbcnews;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import hr.ferit.tumiljanovic.bbcnews.constants.Constants;
import hr.ferit.tumiljanovic.bbcnews.interaction.ApiInteractor;
import hr.ferit.tumiljanovic.bbcnews.interaction.ApiInteractorImpl;
import hr.ferit.tumiljanovic.bbcnews.networking.ApiService;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {


    private static App sInstance;
    private static ApiInteractor mApiInteractor;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance= this;
        initializeRealmDatabase();
        Retrofit retrofit = provideRestClient();
        ApiService apiService = createAPIService(retrofit);

        mApiInteractor = new ApiInteractorImpl(apiService);


    }

    public static App getInstance() {
        return sInstance;
    }

    private ApiService createAPIService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    private Retrofit provideRestClient() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BBCNEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void initializeRealmDatabase() {
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("BBCNews.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }


    public ApiInteractor getApiInteractor() {
        return mApiInteractor;
    }
}
