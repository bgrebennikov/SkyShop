package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.blog.Article;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;

public class App {
    public static void main(String[] args) {

        ProductBasket basket = new ProductBasket();

        SimpleProduct bread = new SimpleProduct("Хлеб", 50);
        SimpleProduct water = new SimpleProduct("Вода", 65);
        SimpleProduct milk = new SimpleProduct("Молоко", 100);
        SimpleProduct butter = new SimpleProduct("Масло", 150);

        basket.addProduct(bread);
        basket.addProduct(water);
        basket.addProduct(milk);
        basket.addProduct(butter);

        List<Product> deletedItems = basket.deleteByName("Вода");
        System.out.println("Удалено: " + deletedItems);

        basket.printProducts();

        List<Product> nonExistingProduct = basket.deleteByName("123");
        if (nonExistingProduct.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println(nonExistingProduct);
        }

        SearchEngine searchEngine = new SearchEngine();

        Article article = new Article(
                "Lorem Ipsum",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry"
        );

        searchEngine.add(article);
        searchEngine.add(bread);
        searchEngine.add(water);
        searchEngine.add(milk);
        searchEngine.add(butter);

        List<Searchable> milkQuery = searchEngine.search("Молоко");
        System.out.println("Результат поиска по запросу \"Молоко\":  " + milkQuery);

        List<Searchable> randomQuery = searchEngine.search("abcdef123");
        System.out.println("Результат поиска по запросу \"abcdef123\":  " + randomQuery);


    }
}
