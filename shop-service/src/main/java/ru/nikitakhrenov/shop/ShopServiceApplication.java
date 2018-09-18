package ru.nikitakhrenov.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.nikitakhrenov.shop.domain.Category;
import ru.nikitakhrenov.shop.repository.CategoryRepository;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ShopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceApplication.class, args);
    }

    @Autowired
    MongoTemplate template;

    @Bean
    CommandLineRunner init(CategoryRepository categoryRepository) {

        return args -> {
            template.dropCollection(Category.class);

            final Category parent = new Category("Parent 1", "parent-1");
            String path = categoryRepository.create(parent, null);

            final Category child1 = new Category( "Child 1.1", "child-1.1");
            String path1 = categoryRepository.create(child1, path);

            final Category child11 = new Category( "Child 1.1.1", "child-1.1.1");
            String path11  = categoryRepository.create(child11, path1);

            final Category child12 = new Category( "Child 1.1.2", "child-1.1.2");
            String path12 = categoryRepository.create(child12, path1);

            final Category child121 = new Category( "Child 1.1.2.1", "child-1.1.2.1");
            String path121 = categoryRepository.create(child121, path12);

            final Category child13 = new Category( "Child 1.1.3", "child-1.1.3");
            String path13 = categoryRepository.create(child13, path1);

            final Category child2 = new Category( "Child 1.2", "child-1.2");
            String path2 = categoryRepository.create(child2, path);

            String id = "1";
            Category root = categoryRepository.find(id);
            System.out.println(root);
        };

    }
}
