package org.skypro.skyshop.product;

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

}
