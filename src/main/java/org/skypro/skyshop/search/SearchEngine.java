package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;

import java.util.Arrays;
import java.util.Objects;

public class SearchEngine {

    private final Searchable[] searchItems;
    private int size;

    public SearchEngine(int count) {
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

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.trim().isEmpty()) {
            throw new IllegalArgumentException("Поисковый запрос не может быть пустым.");
        }

        String normalizedSearch = search.trim().toLowerCase();

        return Arrays.stream(searchItems, 0, size)
                .filter(Objects::nonNull)
                .max((a, b) -> {
                    int countA = countOccurrences(a.getSearchTerm().toLowerCase(), normalizedSearch);
                    int countB = countOccurrences(b.getSearchTerm().toLowerCase(), normalizedSearch);
                    return Integer.compare(countA, countB);
                })
                .filter(item -> countOccurrences(item.getSearchTerm().toLowerCase(), normalizedSearch) > 0)
                .orElseThrow(() -> new BestResultNotFound(search));
    }


    private int countOccurrences(String text, String substring) {
        if (text == null || substring == null || substring.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while (true) {
            int foundIndex = text.indexOf(substring, index);
            if (foundIndex == -1) {
                break;
            }
            count++;
            index = foundIndex + substring.length();
        }

        return count;
    }

}
