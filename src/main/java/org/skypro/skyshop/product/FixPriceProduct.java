package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {

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
}
