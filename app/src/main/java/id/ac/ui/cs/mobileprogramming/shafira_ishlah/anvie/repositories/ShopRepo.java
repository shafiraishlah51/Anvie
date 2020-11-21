package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.Product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {
    private MutableLiveData<List<Product>> mutableProductList;
    public LiveData<List<Product>> getProducts(){
        if(mutableProductList==null){
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;

    }
    private void loadProducts(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(), "The Little Mermaid", 99, true, "https://firebasestorage.googleapis.com/v0/b/tktpl-2aef8.appspot.com/o/The%20little%20Mermaid.jpg?alt=media&token=195138a2-a755-48cc-ac92-a1785eb5cb56" ));
        productList.add(new Product(UUID.randomUUID().toString(), "Aladdin", 32, false, "https://firebasestorage.googleapis.com/v0/b/tktpl-2aef8.appspot.com/o/Aladdin.jpg?alt=media&token=d2c10edf-a6a4-41fd-a0a4-15f1f36e7bc6"));
        productList.add(new Product(UUID.randomUUID().toString(), "Cinderella", 9, true, "https://firebasestorage.googleapis.com/v0/b/tktpl-2aef8.appspot.com/o/Cinderella.jpg?alt=media&token=c730ba51-7dce-43df-b72e-8e960374e7a7"));
        productList.add(new Product(UUID.randomUUID().toString(), "Coco", 19, false, "https://firebasestorage.googleapis.com/v0/b/tktpl-2aef8.appspot.com/o/Coco.jpg?alt=media&token=c38c0cbd-8b57-4d8b-a17c-733380d6b3b2"));
        productList.add(new Product(UUID.randomUUID().toString(), "Frozen", 91, true, "https://firebasestorage.googleapis.com/v0/b/tktpl-2aef8.appspot.com/o/Coco.jpg?alt=media&token=c38c0cbd-8b57-4d8b-a17c-733380d6b3b2"));
        productList.add(new Product(UUID.randomUUID().toString(), "Secret of The Wings", 10, true, "https://firebasestorage.googleapis.com/v0/b/tktpl-2aef8.appspot.com/o/Secret%20of%20the%20wings.jpg?alt=media&token=8f7cc091-e652-4821-9212-8edb345910cb"));
        productList.add(new Product(UUID.randomUUID().toString(), "Tangled", 39, false, "https://firebasestorage.googleapis.com/v0/b/tktpl-2aef8.appspot.com/o/Tangled.jpg?alt=media&token=d653cb2d-0f19-4e2c-b120-b0262ae0cb0b"));
        productList.add(new Product(UUID.randomUUID().toString(), "The Lion King", 23, true, "https://firebasestorage.googleapis.com/v0/b/tktpl-2aef8.appspot.com/o/The%20lion%20king.jpg?alt=media&token=c583c67d-8760-404b-9eb4-bd944fd4c843"));
        productList.add(new Product(UUID.randomUUID().toString(), "Inside Out", 41, true, "https://firebasestorage.googleapis.com/v0/b/tktpl-2aef8.appspot.com/o/inside%20out.jpg?alt=media&token=a70823a6-95e6-440f-9ffe-e9b39fecf0c9"));
        productList.add(new Product(UUID.randomUUID().toString(), "Moana", 55, true, "https://firebasestorage.googleapis.com/v0/b/tktpl-2aef8.appspot.com/o/moana.jpg?alt=media&token=c8844dc3-5288-406d-8772-c5fe82809621"));
        mutableProductList.setValue(productList);
    }
}
