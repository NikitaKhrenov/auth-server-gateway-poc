package ru.nikitakhrenov.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString(exclude = {"ancestors", "children"})
@Document(collection = "categories")
public class Category {

    public static final String PATH_SEPARATOR = ".";
    @Id
    private String id;
    private String name;
    private String slug;
    @Indexed(unique = true)
    private String path;
    @Transient
    private List<Category> ancestors = new ArrayList<>();
    @Transient
    private List<Category> children = new ArrayList<>();

    public Category(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public Category(String id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.path = id;
    }

    public Category(String id, String name, String slug, Category parent) {
        this(id, name, slug);
        this.path = parent.getPath() + PATH_SEPARATOR + id;
    }
}
