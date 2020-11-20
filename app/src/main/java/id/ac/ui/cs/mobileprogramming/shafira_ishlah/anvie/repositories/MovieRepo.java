package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.MovieItem;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.Product;

public class MovieRepo {

    private MutableLiveData<List<MovieItem>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();

    public LiveData<List<MovieItem>> getCart() {
        if (mutableCart.getValue() == null) {
            initMovie();
        }
        return mutableCart;
    }

    public void initMovie() {
        mutableCart.setValue(new ArrayList<MovieItem>());
    }

    public boolean addItemToMyMovie(Product product) {
        if (mutableCart.getValue() == null) {
            initMovie();
        }
        List<MovieItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        for (MovieItem cartItem: cartItemList) {
            if (cartItem.getProduct().getId().equals(product.getId())) {
                if (cartItem.getQuantity() == 5) {
                    return false;
                }

                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemList.set(index, cartItem);

                mutableCart.setValue(cartItemList);

                return true;
            }
        }
        MovieItem cartItem = new MovieItem(product, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);

        return true;
    }

}

