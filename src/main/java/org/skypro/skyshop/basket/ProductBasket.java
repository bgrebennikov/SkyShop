package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

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
            if (productsStore[i] == null) return i;
        }
        return null;
    }

    public int basketAmountTotal() {
        return Arrays.stream(productsStore)
                .filter(Objects::nonNull)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public int specialItemsCount() {
        return (int) Arrays.stream(productsStore)
                .filter(Objects::nonNull)
                .filter(Product::isSpecial)
                .count();
    }

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Некорректное значение");
            return;
        }

        Integer basketSlot = findFreeBasketSlot();
        if (basketSlot == null) {
            System.out.println("Невозможно добавить продукт");
            return;
        }

        productsStore[basketSlot] = product;
    }

    public Product findByName(String productName) {
        return Arrays.stream(productsStore)
                .filter(Objects::nonNull)
                .filter(product -> product.getTitle().equals(productName))
                .findFirst()
                .orElse(null);
    }

    public boolean hasProductWithName(String name) {
        return findByName(name) != null;
    }

    public void cleanBasket() {
        Arrays.fill(productsStore, null);
    }

    public void printProducts() {
        if (Arrays.stream(productsStore).noneMatch(Objects::nonNull)) {
            System.out.println("Корзина пуста");
            return;
        }

        StringBuilder consoleOutput = new StringBuilder();
        for (Product product : productsStore) {
            if (product == null) {
                break;
            }

            if (product instanceof SimpleProduct) {
                consoleOutput.append(String.format("%s: %s Руб\n", product.getTitle(), product.getPrice()));
            } else if (product instanceof DiscountedProduct) {
                consoleOutput.append(String.format("%s: %s Руб (Скидка %s%%)\n",
                        product.getTitle(), product.getPrice(),
                        ((DiscountedProduct) product).getDiscount()
                ));
            } else if (product instanceof FixPriceProduct) {
                consoleOutput.append(String.format("%s: Фиксированная цена %s Руб\n",
                        product.getTitle(), product.getPrice()
                ));
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
        return Arrays.hashCode(productsStore);
    }

    @Override
    public String toString() {
        return "ProductBasket{" +
                "productsStore=" + Arrays.toString(productsStore) +
                '}';
    }
}
