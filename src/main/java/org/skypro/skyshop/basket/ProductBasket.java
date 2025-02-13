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

    public int basketAmountTotal() {
        return Arrays.stream(productsStore)
                .filter(Objects::nonNull)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public void addProduct(Product product) throws BasketFullException {
        if (product == null) throw new NullPointerException();

        Integer basketSlot = findFreeBasketSlot();
        if (basketSlot == null) {
            throw new BasketFullException("Невозможно добавить продукт");
        }

        productsStore[basketSlot] = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBasket that = (ProductBasket) o;
        return Objects.deepEquals(productsStore, that.productsStore);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(productsStore);
    }

    @Override
    public String toString() {
        return "ProductBasket{" +
                "productsStore=" + Arrays.toString(productsStore) +
                '}';
    }
}
