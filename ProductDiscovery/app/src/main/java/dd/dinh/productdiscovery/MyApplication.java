package dd.dinh.productdiscovery;

import android.app.Application;
import android.content.Context;

import dd.dinh.productdiscovery.inject.AppContainer;

public class MyApplication extends Application {
    public AppContainer appContainer;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(getApplicationContext());
        mAppContext = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        appContainer.clear();
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
