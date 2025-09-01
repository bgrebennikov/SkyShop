package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public class DiscountedProduct extends Product implements Searchable {

    private final Integer discount;
    private final Integer basePrice;

    public DiscountedProduct(String title, Integer basePrice, Integer discount) {
        super(title);
        this.basePrice = basePrice;
        this.discount = discount;
    }

    public Integer getDiscount() {
        return discount;
    }

    @Override
    public Integer getPrice() {
        return basePrice - (basePrice * discount / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "%s: %s Руб (Скидка %s%%)".formatted(getTitle(), getPrice(), getDiscount());
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
