package dd.dinh.productdiscovery.ui.detail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dd.dinh.productdiscovery.data.model.Product;
import dd.dinh.productdiscovery.databinding.ItemColProductBinding;
import dd.dinh.productdiscovery.viewmodel.concrete.ProductDetailViewModel;

public class ProductListHorizontalAdapter extends RecyclerView.Adapter<ProductListHorizontalAdapter.ViewHolder> {

    private Context mContext;
    private ProductDetailViewModel mViewModel;
    private ArrayList<Product> mList = new ArrayList<>();
    private ItemColProductBinding mBinding;

    public ProductListHorizontalAdapter(Context mContext, ProductDetailViewModel viewModel) {
        this.mContext = mContext;
        this.mViewModel = viewModel;

        subscribeProductList();
    }

    private void subscribeProductList() {
        mViewModel.listSameKindProduct().observe((LifecycleOwner) mContext, this::updateList);
    }

    private void updateList(List<Product> products) {
        mList.clear();
        mList.addAll(products);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mBinding = ItemColProductBinding.inflate(inflater, parent, false);

        return new ProductListHorizontalAdapter.ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setProduct(mList.get(position));
        holder.binding.setLifecycleOwner((LifecycleOwner) mContext);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemColProductBinding binding;

        public ViewHolder(ItemColProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
