package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {

    private final Product[] productsStore;


    public ProductBasket(Integer capacity) {
        if (capacity == null) capacity = 5;
        productsStore = new Product[capacity];
    }

}
