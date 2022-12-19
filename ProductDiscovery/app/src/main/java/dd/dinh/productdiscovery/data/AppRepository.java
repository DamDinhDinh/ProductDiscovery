package dd.dinh.productdiscovery.data;

import android.util.Log;

import java.util.Date;
import java.util.List;

import dd.dinh.productdiscovery.data.local.AppDatabase;
import dd.dinh.productdiscovery.data.model.Product;
import dd.dinh.productdiscovery.data.network.ProductService;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AppRepository implements Repository {

    private static final String TAG = AppRepository.class.getSimpleName();
    private ProductService mProductService;
    private AppDatabase mDatabase;
    private CompositeDisposable mDisposes;

    public AppRepository(ProductService mProductService, AppDatabase mDatabase) {
        this.mProductService = mProductService;
        this.mDatabase = mDatabase;

        mDisposes = new CompositeDisposable();
    }

    @Override
    public Single<Product> getProductById(long id) {
        return mDatabase.productDao().getProductById(id);
    }

    @Override
    public Observable<List<Product>> getProductList() {
        return mDatabase.productDao().getAllProduct();
    }

    @Override
    public Observable<List<Product>> getSameKindProduct(Product product) {
        Log.d(TAG, "getSameKindProduct: "+product.getBrand());
        return mDatabase.productDao().getSameKindProduct(product.getBrand());
    }

    @Override
    public Observable<List<Product>> searchProductByName(String search) {
        Log.d(TAG, "searchProductByName: " + search);
        return mDatabase.productFts4Dao().searchWith(search);
    }

    @Override
    public Completable updateFromRemote() {
        Log.d(TAG, "updateFromRemote: start request"+new Date(System.currentTimeMillis()));
        return mProductService.listProduct()
            .flatMapCompletable(new Function<List<Product>, CompletableSource>() {
                @Override
                public CompletableSource apply(@NonNull List<Product> products) {
                    Log.d(TAG, "updateFromRemote: completed request "+new Date(System.currentTimeMillis()));
                    return mDatabase.productDao().insertAll(products);
                }
            });
    }

    @Override
    public void clear() {
        Log.d(TAG, "clearSubscribe: ");
        if (mDisposes != null && !mDisposes.isDisposed()) {
            mDisposes.clear();
        }
    }
}
