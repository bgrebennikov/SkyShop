package org.skypro.skyshop.product;

import org.skypro.skyshop.Searchable;

public class FixPriceProduct extends Product implements Searchable {

    private static final Integer FIXED_PRICE = 350;

    public FixPriceProduct(String title) {
        super(title);
    }

    @Override
    public Integer getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "%s: Фиксированная цена %s Руб".formatted(getTitle(), getPrice());
    }

    @Override
    public String getSearchTerm() {
        return getTitle();
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
}
