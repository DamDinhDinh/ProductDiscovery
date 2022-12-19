package dd.dinh.productdiscovery.viewmodel;

import androidx.lifecycle.LiveData;

import java.util.List;

import dd.dinh.productdiscovery.data.model.Product;

public interface ProductDetailActivityContract {
    interface ViewModel {
        //product that will be show
        LiveData<Product> getProduct();
        //same kind product list
        LiveData<List<Product>> listSameKindProduct();
    }

    interface View{}

}
