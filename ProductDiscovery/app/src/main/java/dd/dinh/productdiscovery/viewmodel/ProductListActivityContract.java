package dd.dinh.productdiscovery.viewmodel;

import androidx.lifecycle.LiveData;

import java.util.List;

import dd.dinh.productdiscovery.data.model.Product;

public interface ProductListActivityContract {
    interface ViewModel {
        //list product from database
        LiveData<List<Product>> listProduct();
        //search product by search key from database
        void searchProduct(String search);
        //get current search word for highlight when show
        List<String> searchWords();
        //refresh data function
        void refreshDataFromRemote();
        //for observe when refresh is completed
        LiveData<Boolean> isRefreshing();
    }

    interface View {
        //handle product click for navigate
        void onProductClick(long id);
    }
}
