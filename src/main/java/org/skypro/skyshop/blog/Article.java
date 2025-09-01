package org.skypro.skyshop.blog;

import org.skypro.skyshop.search.Searchable;

public class Article implements Searchable {

    private final String title;
    private final String body;

    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "%s\n%s".formatted(title, body);
    }

    @Override
    public String getSearchTerm() {
        return this.title;
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }
}
