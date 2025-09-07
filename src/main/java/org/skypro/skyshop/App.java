package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.blog.Article;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {

        ProductBasket basket = new ProductBasket();

        basket.addProduct(new SimpleProduct("Хлеб", 100));
        basket.addProduct(new SimpleProduct("Вода", 100));
        basket.addProduct(new SimpleProduct("Вода", 50));
        basket.addProduct(new SimpleProduct("Молоко", 100));
        basket.addProduct(new SimpleProduct("Масло", 100));


        List<Product> deletedItems = basket.deleteByName("Вода");
        System.out.println("Удалено: " + deletedItems);

        basket.printProducts();

        List<Product> nonExistingProduct = basket.deleteByName("123");
        if (nonExistingProduct.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println(nonExistingProduct);
        }

        SearchEngine searchEngine = new SearchEngine(5);

        Article article = new Article(
                "Lorem Ipsum",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry"
        );

        searchEngine.add(article);



    }
}
