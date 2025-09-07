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

public class App {
    public static void main(String[] args) {

        ProductBasket basket = new ProductBasket();

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

        System.out.printf("\nПродукт \"%s\": %s ", products.getFirst().getTitle(), basket.hasProductWithName(products.getFirst().getTitle()));
        System.out.printf("\nПродукт \"Чай\": %s ", basket.hasProductWithName("Чай"));

        System.out.println("\nОчистка корзины");
        basket.cleanBasket();

        System.out.println("Печать содержимого пустой корзины");
        basket.printProducts();

        System.out.println("\nПолучение стоимости пустой корзины");
        System.out.printf("%s Руб.", basket.basketAmountTotal());

        System.out.println("\nПоиск товара по имени в пустой корзине");

        SimpleProduct someProduct = (SimpleProduct) basket.findByName(products.getFirst().getTitle());

        if (someProduct != null) {
            System.out.println(someProduct.getTitle());
        } else {
            System.out.println("Товар не найден");
        }

        // Part 3
        System.out.println("\nPART 3\n");

        SearchEngine searchEngine = new SearchEngine(5);

        Article article = new Article(
                "Lorem Ipsum",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry"
        );

        searchEngine.add(moonshine);
        searchEngine.add(article);

        Searchable[] searchProductResult = searchEngine.search("Спирт");
        Searchable[] searchArticleResult = searchEngine.search("Lorem Ipsum");
        Searchable[] emptyProductResult = searchEngine.search("Blah blah blah");

        Arrays.stream(searchProductResult).map(
                Searchable::getStringRepresentation
        ).forEach(System.out::println);

        Arrays.stream(searchArticleResult).map(
                Searchable::getStringRepresentation
        ).forEach(System.out::println);

        System.out.println("\nПоиск несуществующей позиции\n");
        System.out.println(Arrays.toString(emptyProductResult));

        System.out.println("\nДобавим более 5 позиций для поиска\n");

        for (int i = 0; i < 10; i++) {
            searchEngine.add(new SimpleProduct("Хлеб", 50));
        }

        Searchable[] searchBreadResult = searchEngine.search("Хлеб");

        Arrays.stream(searchBreadResult).map(
                Searchable::getStringRepresentation
        ).forEach(System.out::println);


    }
}
