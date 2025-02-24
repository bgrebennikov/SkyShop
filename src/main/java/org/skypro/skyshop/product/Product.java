package org.skypro.skyshop.product;

import java.util.Objects;

public abstract class Product {

    private final String title;

    public Product(String title) {
        this.title = title;
    }

    public abstract Integer getPrice();
    public abstract boolean isSpecial();

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
