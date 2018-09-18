package ru.nikitakhrenov.shop.repository;

import ru.nikitakhrenov.shop.domain.Category;

public interface CategoryRepositoryCustom {

    Category find(String id);

    String create(Category category, String parentId);
    
}
