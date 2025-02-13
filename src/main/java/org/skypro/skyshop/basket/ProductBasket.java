package org.skypro.skyshop.basket;

import org.skypro.skyshop.exceptions.BasketFullException;
import org.skypro.skyshop.product.Product;

import java.util.Arrays;
import java.util.Objects;

public class ProductBasket {

    private final Product[] productsStore;


    public ProductBasket(Integer capacity) {
        if (capacity == null) capacity = 5;
        productsStore = new Product[capacity];
    }

    private Integer findFreeBasketSlot() {
        for (int i = 0; i < productsStore.length; i++) {
            if (productsStore[i] != null) return i;
        }
        return null;
    }


}
