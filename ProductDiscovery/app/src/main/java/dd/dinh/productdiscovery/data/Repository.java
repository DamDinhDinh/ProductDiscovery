package dd.dinh.productdiscovery.data;

import java.util.List;

import dd.dinh.productdiscovery.data.model.Product;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface Repository {

    Single<Product> getProductById(long id);

    Observable<List<Product>> getProductList();

    Observable<List<Product>> getSameKindProduct(Product product);

    Observable<List<Product>> searchProductByName(String search);

    Completable updateFromRemote();

    void clear();
}
