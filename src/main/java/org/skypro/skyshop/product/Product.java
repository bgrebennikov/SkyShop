package org.skypro.skyshop.product;

public class Product {

    private final String title;
    private final Integer price;

    Product(String title, Integer price) {
        this.title = title;
        this.price = price;
    }


    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }
}
