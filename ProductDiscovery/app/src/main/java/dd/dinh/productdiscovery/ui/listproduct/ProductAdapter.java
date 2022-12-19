package dd.dinh.productdiscovery.ui.listproduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dd.dinh.productdiscovery.data.model.Product;
import dd.dinh.productdiscovery.databinding.ItemProductRowBinding;
import dd.dinh.productdiscovery.viewmodel.concrete.ProductListViewModel;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context mContext;
    private ProductListViewModel mViewModel;
    private ItemProductRowBinding mBinding;
    private ArrayList<Product> mList = new ArrayList<>();
    private ProductClickListener mListener;

    public ProductAdapter(Context mContext, ProductListViewModel mViewModel, ProductClickListener listener) {
        this.mContext = mContext;
        this.mViewModel = mViewModel;
        this.mListener = listener;

        subscribeProductList();
    }

    private void subscribeProductList() {
        mViewModel.listProduct().observe((LifecycleOwner) mContext, this::updateList);
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
        mBinding = ItemProductRowBinding.inflate(inflater, parent, false);

        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setVm(mViewModel);
        holder.binding.setListener(mListener);
        holder.binding.setProduct(mList.get(position));
        holder.binding.setLifecycleOwner((LifecycleOwner) mContext);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemProductRowBinding binding;

        public ViewHolder(ItemProductRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);

    }

    public interface ProductClickListener {
        void onProductClick(long id);
    }
}
