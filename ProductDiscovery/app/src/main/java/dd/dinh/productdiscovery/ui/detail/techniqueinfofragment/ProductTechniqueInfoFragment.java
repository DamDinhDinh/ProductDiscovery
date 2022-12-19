package dd.dinh.productdiscovery.ui.detail.techniqueinfofragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dd.dinh.productdiscovery.MyApplication;
import dd.dinh.productdiscovery.data.AppRepository;
import dd.dinh.productdiscovery.data.Repository;
import dd.dinh.productdiscovery.databinding.FragmentProductTechniqueInfoBinding;
import dd.dinh.productdiscovery.inject.AppContainer;
import dd.dinh.productdiscovery.viewmodel.concrete.ProductDetailViewModel;
import dd.dinh.productdiscovery.viewmodel.concrete.ProductDetailViewModelFactory;
public class ProductTechniqueInfoFragment extends Fragment {

    private static final String TAG = ProductTechniqueInfoFragment.class.getSimpleName();

    public static String PRODUCT_ID_KEY = "product_id";

    private FragmentProductTechniqueInfoBinding mBinding;
    private ProductDetailViewModel mViewModel;

    public static ProductTechniqueInfoFragment newInstance(long id) {
        ProductTechniqueInfoFragment fragment = new ProductTechniqueInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(PRODUCT_ID_KEY, id);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentProductTechniqueInfoBinding.inflate(inflater);

        long id = -1;
        if (getArguments() != null) {
            id = getArguments().getLong(PRODUCT_ID_KEY, -1);
        }

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        Repository repository = appContainer.getRepository();
        mViewModel = new ViewModelProvider(this, new ProductDetailViewModelFactory(repository, id)).get(ProductDetailViewModel.class);

        mBinding.setVm(mViewModel);
        mBinding.setLifecycleOwner(this);

        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
