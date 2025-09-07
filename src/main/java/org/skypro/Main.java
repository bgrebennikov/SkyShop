package org.skypro;

import org.skypro.skyshop.exception.BestResultNotFound;
import org.skypro.skyshop.exception.InvalidProductTitleException;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class Main {



    public static void main(String[] args) {

        try {
            new SimpleProduct("    ", 100);
        } catch (InvalidProductTitleException e) {
            System.out.println(e.getMessage());
        }

        try {
            new SimpleProduct("Хлеб", 0);
        } catch (InvalidProductTitleException e) {
            System.out.println(e.getMessage());
        }

        try {
            new DiscountedProduct("Торт", 500, 1000);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        SimpleProduct p1 = new SimpleProduct("Лимоны", 23);
        SimpleProduct p2 = new SimpleProduct("Груши", 30);
        SimpleProduct p3 = new SimpleProduct("Бананы", 25);
        SimpleProduct p4 = new SimpleProduct("Апельсины", 40);

        FixPriceProduct fp1 = new FixPriceProduct("Гречка");
        FixPriceProduct fp2 = new FixPriceProduct("Рис");
        FixPriceProduct fp3 = new FixPriceProduct("Овсянка");
        FixPriceProduct fp4 = new FixPriceProduct("Горох");



        SearchEngine searchEngine = new SearchEngine();

        searchEngine.add(p1);
        searchEngine.add(p2);
        searchEngine.add(p3);
        searchEngine.add(p4);

        searchEngine.add(fp1);
        searchEngine.add(fp2);
        searchEngine.add(fp3);
        searchEngine.add(fp4);

        System.out.println("\n\nрезультат когда нужный объект существует\n");

        try{
            Searchable sr1 = searchEngine.findBestMatch("апел");
            System.out.println(sr1.toString());

            Searchable sr2 = searchEngine.findBestMatch("бан");
            System.out.println(sr2.toString());


        } catch (BestResultNotFound e){
            System.out.println(e.getMessage());
        }

        System.out.println("\n\nкогда метод выбрасывает исключение\n");

        try{
            Searchable fsr = searchEngine.findBestMatch("абвгд123");
            System.out.println(fsr.toString());


        } catch (BestResultNotFound e){
            System.out.println(e.getMessage());
        }


    }
}