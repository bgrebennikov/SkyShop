package org.skypro.skyshop.product;

import org.skypro.skyshop.exception.InvalidProductTitleException;
import org.skypro.skyshop.search.Searchable;

public class SimpleProduct extends Product implements Searchable {

    private final String title;
    private final Integer price;

    public SimpleProduct(final String title, final Integer price) {
        super(title);
        this.title = title;

        if (price == null ||  price <= 0) {
            throw new InvalidProductTitleException("Цена продукта должна быть выше нуля.");
        }
        this.price = price;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "%s: %s Руб".formatted(title, price);
    }

    @Override
    public String getSearchTerm() {
        return this.title;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
}
