package dd.dinh.productdiscovery.ui.detail.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import dd.dinh.productdiscovery.MyApplication;
import dd.dinh.productdiscovery.R;
import dd.dinh.productdiscovery.ui.detail.techniqueinfofragment.ProductCompareFragment;
import dd.dinh.productdiscovery.ui.detail.techniqueinfofragment.ProductDescribeFragment;
import dd.dinh.productdiscovery.ui.detail.techniqueinfofragment.ProductTechniqueInfoFragment;

public class ProductTechniqueInfoPagerAdapter extends FragmentStatePagerAdapter {

    private long id;

    public ProductTechniqueInfoPagerAdapter(@NonNull FragmentManager fm, int behavior, long id) {
        super(fm, behavior);
        this.id = id;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new ProductDescribeFragment();
            }
            case 1: {
                return ProductTechniqueInfoFragment.newInstance(id);
            }
            case 2: {
                return new ProductCompareFragment();
            }

            default:
                return new ProductDescribeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: {
                return MyApplication.getAppContext().getString(R.string.describe_fragment_title);
            }
            case 1: {
                return MyApplication.getAppContext().getString(R.string.technique_info_fragment_title);
            }
            case 2: {
                return MyApplication.getAppContext().getString(R.string.compare_fragment_title);
            }
            default:
                return "";
        }
    }
}
