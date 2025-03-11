package org.skypro.skyshop;

public interface Searchable {

    String getSearchTerm();

    String getContentType();

    default String getStringRepresentation() {
        return "Имя Searchable объекта: %s\nТип: %s".formatted(getSearchTerm(), getContentType());
    }
}
