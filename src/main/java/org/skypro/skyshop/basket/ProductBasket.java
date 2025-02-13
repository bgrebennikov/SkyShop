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

    public boolean hasProductWithName(String name) {
        return Arrays.stream(productsStore)
                .filter(Objects::nonNull)
                .anyMatch(product -> product.getTitle().equals(name));
    }

    public void printProducts() {
        if (productsStore.length == 0) {
            System.out.println("Корзина пуста");
            return;
        }

        StringBuilder consoleOutput = new StringBuilder();
        for (Product product : productsStore) {
            consoleOutput.append("%s: %sРуб".formatted(product.getTitle(), product.getPrice()));
        }
        consoleOutput.append("Итого: %s".formatted(basketAmountTotal()));
        System.out.println(consoleOutput);
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
