package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.viewmodels;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.CartItem;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.Product;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.CartRepo;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.ShopRepo;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ShopViewModel extends ViewModel {

    CartRepo cartRepo = new CartRepo();
    ShopRepo shopRepo = new ShopRepo();

    MutableLiveData<Product> mutableProduct = new MutableLiveData<>();

    public LiveData<List<Product>> getProducts() {
        return shopRepo.getProducts();
    }

    public void setProduct(Product product) {
        mutableProduct.setValue(product);
    }

    public LiveData<Product> getProduct() {
        return mutableProduct;
    }

    public boolean addItemToCart(Product product) {
        return cartRepo.addItemToCart(product);
    }

    public void removeItemFromCart(CartItem cartItem) {
        cartRepo.removeItemFromCart(cartItem);
    }

    public void changeQuantity(CartItem cartItem, int quantity) {
        cartRepo.changeQuantity(cartItem, quantity);
    }

    public LiveData<Double> getTotalPrice() {
        return cartRepo.getTotalPrice();
    }

    public void resetCart() {
        cartRepo.initCart();
    }

    public LiveData<List<CartItem>> getCart() {
        return cartRepo.getCart();
    }
}

