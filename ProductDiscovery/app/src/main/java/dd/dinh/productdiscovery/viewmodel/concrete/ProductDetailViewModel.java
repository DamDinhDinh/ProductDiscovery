package dd.dinh.productdiscovery.viewmodel.concrete;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dd.dinh.productdiscovery.data.Repository;
import dd.dinh.productdiscovery.data.model.Product;
import dd.dinh.productdiscovery.viewmodel.ProductDetailActivityContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class ProductDetailViewModel extends ViewModel implements ProductDetailActivityContract.ViewModel {

    private static final String TAG = ProductDetailViewModel.class.getSimpleName();

    private Repository mRepo;
    private long mId;
    private MutableLiveData<List<Product>> mSameKindProducts = new MutableLiveData<>();
    private MutableLiveData<Product> mProduct = new MutableLiveData<>();
    private CompositeDisposable mDisposes = new CompositeDisposable();
    private PublishSubject<Product> mProductObservable = PublishSubject.create();

    public ProductDetailViewModel(Repository mRepo, long id) {
        this.mRepo = mRepo;
        this.mId = id;
        getProductFromSource();
        getSameKindProductList();

    }

    @Override
    public LiveData<Product> getProduct() {
        return mProduct;
    }

    @Override
    public LiveData<List<Product>> listSameKindProduct() {
        return mSameKindProducts;
    }

    private void getSameKindProductList() {
        mDisposes.add(
            mProductObservable
                .switchMap(product -> mRepo.getSameKindProduct(product))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> products) throws Exception {
                        mSameKindProducts.setValue(products);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                })
                .subscribe()
        );
    }

    private void getProductFromSource() {
        mDisposes.add(
            mRepo.getProductById(mId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(product -> {
                    mProduct.setValue(product);
                    mProductObservable.onNext(product);
                    Log.d(TAG, "setProduct: " + product.getId());
                })
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
