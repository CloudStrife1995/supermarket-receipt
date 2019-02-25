package fr.esiea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productsInCart = new HashMap<>();


    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItem(product, 1.0);
    }

    Map<Product, Double> getProductsInCart() {
        return productsInCart;
    }


    public void addItem(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productsInCart.containsKey(product)) {
            productsInCart.put(product, productsInCart.get(product) + quantity);
        } else {
            productsInCart.put(product, quantity);
        }
    }
}
