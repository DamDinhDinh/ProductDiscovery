package dd.dinh.productdiscovery.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.content.Intent;
import android.os.Bundle;

import dd.dinh.productdiscovery.MyApplication;
import dd.dinh.productdiscovery.data.AppRepository;
import dd.dinh.productdiscovery.data.Repository;
import dd.dinh.productdiscovery.databinding.ActivityProductDetailBinding;
import dd.dinh.productdiscovery.inject.AppContainer;
import dd.dinh.productdiscovery.ui.detail.adapter.ProductListHorizontalAdapter;
import dd.dinh.productdiscovery.ui.detail.adapter.ProductTechniqueInfoPagerAdapter;
import dd.dinh.productdiscovery.viewmodel.concrete.ProductDetailViewModel;
import dd.dinh.productdiscovery.viewmodel.concrete.ProductDetailViewModelFactory;

public class ProductDetailActivity extends AppCompatActivity {

    private static final String TAG = ProductDetailActivity.class.getSimpleName();

    public static String PRODUCT_ID_KEY = "product_id";

    private ActivityProductDetailBinding mBinding;
    private ProductDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        long id = intent.getLongExtra(PRODUCT_ID_KEY, -1);

        AppContainer appContainer = ((MyApplication) getApplication()).appContainer;
        Repository repository = appContainer.getRepository();
        mViewModel = new ViewModelProvider(this, new ProductDetailViewModelFactory(repository, id)).get(ProductDetailViewModel.class);

        mBinding.setLifecycleOwner(this);
        mBinding.setVm(mViewModel);

        mBinding.layoutTechniqueInfo.pager.setAdapter(new ProductTechniqueInfoPagerAdapter(getSupportFragmentManager(), 1, id));
        mBinding.layoutTechniqueInfo.tabLayout.setupWithViewPager(mBinding.layoutTechniqueInfo.pager);
        mBinding.layoutTechniqueInfo.pager.setCurrentItem(1);
        mBinding.layoutSameKindProduct.rvSameKindProduct.setAdapter(new ProductListHorizontalAdapter(this, mViewModel));
        mBinding.layoutSameKindProduct.rvSameKindProduct.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}