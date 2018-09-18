package ru.nikitakhrenov.shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nikitakhrenov.shop.domain.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String>, CategoryRepositoryCustom {

    String CATEGORY_PROJECTION = "{name : 1, slug: 1, ancestors.slug: 1, ancestors.name: 1, _id: 0}";

    //@Query(fields = CATEGORY_PROJECTION)
    Category findBySlug(String slug);

    List<Category> findAll();
}
