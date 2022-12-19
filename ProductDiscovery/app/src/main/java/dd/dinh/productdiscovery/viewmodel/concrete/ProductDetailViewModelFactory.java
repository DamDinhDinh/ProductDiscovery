package dd.dinh.productdiscovery.viewmodel.concrete;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dd.dinh.productdiscovery.data.AppRepository;
import dd.dinh.productdiscovery.data.Repository;

public class ProductDetailViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;
    private long id;

    public ProductDetailViewModelFactory(Repository repository, long id) {
        this.repository = repository;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductDetailViewModel(repository, id);
    }
}
