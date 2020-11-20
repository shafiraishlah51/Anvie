package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.adapters;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.databinding.ShopRowBinding;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.Product;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class ShopListAdapter extends ListAdapter<Product, ShopListAdapter.ShopViewHolder> {

    public ShopInterface shopInterface;
    public ShopListAdapter(ShopInterface shopInterface) {

        super(Product.itemCallback);
        this.shopInterface = shopInterface;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        ShopRowBinding shopRowBinding = ShopRowBinding.inflate(layoutInflater, parent, false);
        shopRowBinding.setShopInterface(shopInterface);
        return new ShopViewHolder(shopRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Product product = getItem(position);
        holder.shopRowBinding.setProduct(product);

        holder.shopRowBinding.executePendingBindings();
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {

        ShopRowBinding shopRowBinding;

        public ShopViewHolder(ShopRowBinding binding) {
            super(binding.getRoot());
            this.shopRowBinding = binding;


        }
    }

    public interface ShopInterface{
        void addItem(Product product);
        void onItemClick(Product product);
    }

}

