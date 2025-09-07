package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ProductBasket {

    private final List<Product> productsStore;


    public ProductBasket() {
        this.productsStore = new LinkedList<Product>();
    }

    public int basketAmountTotal() {
        return productsStore.stream()
                .filter(Objects::nonNull)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public int specialItemsCount() {
        return (int) productsStore.stream()
                .filter(Objects::nonNull)
                .filter(Product::isSpecial)
                .count();
    }

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Некорректное значение");
            return;
        }
        productsStore.add(product);
    }

    public Product findByName(String productName) {
        return productsStore.stream()
                .filter(Objects::nonNull)
                .filter(product -> product.getTitle().equals(productName))
                .findFirst()
                .orElse(null);
    }

    public boolean hasProductWithName(String name) {
        return findByName(name) != null;
    }

    public List<Product> deleteByName(String productName) {
        List<Product> itemsToRemove = productsStore.stream()
                .filter(p -> p.getTitle().equals(productName))
                .toList();

        productsStore.removeAll(itemsToRemove);
        return itemsToRemove;
    }

    public void cleanBasket() {
        productsStore.clear();
    }

    public void printProducts() {
        if (productsStore.stream().noneMatch(Objects::nonNull)) {
            System.out.println("Корзина пуста");
            return;
        }

        StringBuilder consoleOutput = new StringBuilder();
        for (Product product : productsStore) {
            if (product != null) {
                consoleOutput.append(product).append("\n");
            }
        }
        consoleOutput.append("\nИтого: ").append(basketAmountTotal()).append(" Руб \n");
        consoleOutput.append("Специальных товаров: %s".formatted(specialItemsCount()));
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
        return productsStore.hashCode();
    }

    @Override
    public String toString() {
        return "ProductBasket{" +
                "productsStore=" + productsStore.toString() +
                '}';
    }
}
