package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchEngine {

    private final List<Searchable> searchItems;

    public SearchEngine() {
        this.searchItems = new LinkedList<Searchable>();
    }

    public void add(Searchable searchItem) {
        searchItems.add(searchItem);
    }

    public List<Searchable> search(String query) {
        return searchItems.stream()
                .filter(t -> t != null && t.getSearchTerm().contains(query))
                .collect(Collectors.toList());
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.trim().isEmpty()) {
            throw new IllegalArgumentException("Поисковый запрос не может быть пустым.");
        }

        String normalizedSearch = search.trim().toLowerCase();

        return searchItems.stream()
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
