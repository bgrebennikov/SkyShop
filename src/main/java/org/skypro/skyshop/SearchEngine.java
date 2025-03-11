package org.skypro.skyshop;

import java.util.Arrays;

public class SearchEngine {

    private final Searchable[] searchItems;
    private int size;

    SearchEngine(int count) {
        this.searchItems = new Searchable[count];
        this.size = 0;
    }

    public void add(Searchable searchItem) {
        if (size == searchItems.length) {
            System.arraycopy(searchItems, 0, searchItems, 1, searchItems.length - 1);
            searchItems[0] = searchItem;
        } else {
            System.arraycopy(searchItems, 0, searchItems, 1, size);
            searchItems[0] = searchItem;
            size++;
        }
    }

    public Searchable[] search(String query) {
        return Arrays.stream(searchItems)
                .filter(t -> t != null && t.getSearchTerm().contains(query))
                .limit(5)
                .toArray(Searchable[]::new);
    }
}
