package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        ProductBasket basket = new ProductBasket(5);

        ArrayList<Product> products = new ArrayList<>();
        products.add(new SimpleProduct("Спирт", 500));
        products.add(new SimpleProduct("Огурцы", 200));
        products.add(new SimpleProduct("Тормазок", 129));
        products.add(new FixPriceProduct("Аренда медведя"));
        products.add(new DiscountedProduct("Балалайка", 6000, 15));

        products.add(new SimpleProduct("Минеральная вода", 79));

        System.out.println("Добавление продукта в корзину");

        basket.addProduct(products.get(0));
        basket.addProduct(products.get(1));
        basket.addProduct(products.get(2));
        basket.addProduct(products.get(3));
        basket.addProduct(products.get(4));

        basket.printProducts();

        System.out.println("\nДобавление продукта в заполненную корзину, в которой нет свободного места");

        basket.addProduct(products.get(5));
        basket.addProduct(products.get(5));

        System.out.println("\nПоиск товара, который есть в корзине.");
        SimpleProduct moonshine = (SimpleProduct) basket.findByName("Спирт");

        if (moonshine != null) {
            System.out.println(moonshine);
        } else {
            System.out.println("Товар не найден");
        }

        System.out.println("\nПоиск товара, которого нет в корзине");
        SimpleProduct lostProduct = (SimpleProduct) basket.findByName("Дизельный корабельный двигатель Wärtsilä-Sulzer RTA96-C");

        if (lostProduct != null) {
            System.out.println(lostProduct.getTitle());
        } else {
            System.out.println("Товар не найден");
        }

        //  Метод, проверяющий продукт в корзине по имени:
        //  метод принимает в себя строку имени и возвращает boolean
        //  в зависимости от того, есть продукт в корзине или его нет.

        System.out.printf("\nПродукт \"%s\": %s ", products.get(0).getTitle(), basket.hasProductWithName(products.get(0).getTitle()));
        System.out.printf("\nПродукт \"Чай\": %s ", basket.hasProductWithName("Чай"));

        System.out.println("\nОчистка корзины");
        basket.cleanBasket();

        System.out.println("Печать содержимого пустой корзины");
        basket.printProducts();

        System.out.println("\nПолучение стоимости пустой корзины");
        System.out.printf("%s Руб.", basket.basketAmountTotal());

        System.out.println("\nПоиск товара по имени в пустой корзине");

        SimpleProduct someProduct = (SimpleProduct) basket.findByName(products.get(0).getTitle());

        if (someProduct != null) {
            System.out.println(someProduct.getTitle());
        } else {
            System.out.println("Товар не найден");
        }


    }
}
