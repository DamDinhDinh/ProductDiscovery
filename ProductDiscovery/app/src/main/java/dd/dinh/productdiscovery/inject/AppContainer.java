package dd.dinh.productdiscovery.inject;

import android.content.Context;

import androidx.room.Room;

import dd.dinh.productdiscovery.data.AppRepository;
import dd.dinh.productdiscovery.data.Repository;
import dd.dinh.productdiscovery.data.local.AppDatabase;
import dd.dinh.productdiscovery.data.network.ProductService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppContainer {
    private Context mAppContext;

    public AppContainer(Context mAppContext) {
        this.mAppContext = mAppContext;
    }

    private ProductService mProductService;
    private AppRepository mRepo;
    private AppDatabase mDatabase;

    public AppDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = Room.databaseBuilder(mAppContext, AppDatabase.class, AppDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
        }

        return mDatabase;
    }

    public ProductService getProductService() {
        if (mProductService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            mProductService = retrofit.create(ProductService.class);
        }

        return mProductService;
    }

    public Repository getRepository() {
        if (mRepo == null) {
            mRepo = new AppRepository(getProductService(), getDatabase());
        }

        return mRepo;
    }

    public void clear() {
        getRepository().clear();
    }
}
