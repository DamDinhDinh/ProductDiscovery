package dd.dinh.productdiscovery.viewmodel.concrete;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import dd.dinh.productdiscovery.data.AppRepository;
import dd.dinh.productdiscovery.data.Repository;
import dd.dinh.productdiscovery.data.model.Product;
import dd.dinh.productdiscovery.viewmodel.ProductListActivityContract;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class ProductListViewModel extends ViewModel implements ProductListActivityContract.ViewModel {
    private static final String TAG = ProductListViewModel.class.getSimpleName();

    private Repository mRepo;
    private MutableLiveData<List<Product>> mListProduct = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsRefreshing = new MutableLiveData<>();
    private CompositeDisposable mDisposes = new CompositeDisposable();
    private PublishSubject<String> mSearchQuerySource = PublishSubject.create();
    private String mSearch = "";

    public ProductListViewModel(Repository mRepo) {
        this.mRepo = mRepo;

        getProductListData();
        getListSearchProduct();
    }

    @Override
    public LiveData<List<Product>> listProduct() {
        return mListProduct;
    }

    @Override
    public void searchProduct(String search) {
        if (!search.trim().isEmpty()) {
            Log.d(TAG, "searchProduct: " + search);
            mSearchQuerySource.onNext(search);
            mSearch = search;
        } else {
            mSearch = "";
            getProductListData();
        }
    }

    @Override
    public List<String> searchWords() {
        return Arrays.asList(mSearch.split(" "));
    }

    @Override
    public void refreshDataFromRemote() {
        mIsRefreshing.setValue(true);
        mDisposes.add(
            mRepo.updateFromRemote()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(() -> Log.d(TAG, "refreshDataFromRemote finished")
                    , error -> {
                        Log.d(TAG, "refreshDataFromRemote: " + error.getLocalizedMessage());
                        error.printStackTrace();
                        if (error instanceof UnknownHostException){
                            Log.d(TAG, "refreshDataFromRemote: Unknown Host");
                        }
                    })
        );
    }

    @Override
    public LiveData<Boolean> isRefreshing() {
        return mIsRefreshing;
    }

    private void getProductListData() {
        mIsRefreshing.setValue(true);
        mDisposes.add(
            mRepo.getProductList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> products) throws Exception {
                        mListProduct.setValue(products);
                        mIsRefreshing.setValue(false);
                        Log.d(TAG, "getProductListData: updated " + products.size());
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "getProductListData: " + throwable.getMessage());
                        throwable.printStackTrace();
                    }
                })
                .subscribe()
        );
    }

    private void getListSearchProduct() {
        mDisposes.add(
            mSearchQuerySource
                .subscribeOn(Schedulers.io())
                .map(search -> "*" + search + "*")
                .switchMap(new Function<String, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> apply(@NonNull String search) {
                        return mRepo.searchProductByName(search);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> products) {
                        mListProduct.setValue(products);
                        Log.d(TAG, "getListSearchProduct: updated " + products.size());
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.d(TAG, "getListSearchProduct: " + throwable.getMessage());
                        throwable.printStackTrace();
                    }
                })
                .subscribe()
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDisposes != null && !mDisposes.isDisposed()) {
            mDisposes.clear();
        }
    }
}
