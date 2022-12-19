package dd.dinh.productdiscovery.ui.listproduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import dd.dinh.productdiscovery.MyApplication;
import dd.dinh.productdiscovery.data.AppRepository;
import dd.dinh.productdiscovery.data.Repository;
import dd.dinh.productdiscovery.databinding.ActivityProductListBinding;
import dd.dinh.productdiscovery.inject.AppContainer;
import dd.dinh.productdiscovery.ui.detail.ProductDetailActivity;
import dd.dinh.productdiscovery.viewmodel.ProductListActivityContract;
import dd.dinh.productdiscovery.viewmodel.concrete.ProductListViewModel;
import dd.dinh.productdiscovery.viewmodel.concrete.ProductListViewModelFactory;

public class ProductListActivity extends AppCompatActivity implements ProductListActivityContract.View {

    private static final String TAG = ProductListActivity.class.getSimpleName();

    private ActivityProductListBinding mBinding;
    private ProductListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityProductListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        AppContainer appContainer = ((MyApplication) getApplication()).appContainer;
        Repository repository = appContainer.getRepository();
        mViewModel = new ViewModelProvider(this, new ProductListViewModelFactory(repository)).get(ProductListViewModel.class);

        mBinding.setVm(mViewModel);
        mBinding.listProduct.setAdapter(new ProductAdapter(this, mViewModel, this::onProductClick));
        mBinding.listProduct.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mBinding.setLifecycleOwner(this);
    }

    @Override
    public void onProductClick(long id) {
        Log.d(TAG, "onProductClick: " + id);
        Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
        intent.putExtra(ProductDetailActivity.PRODUCT_ID_KEY, id);
        startActivity(intent);
    }
}
