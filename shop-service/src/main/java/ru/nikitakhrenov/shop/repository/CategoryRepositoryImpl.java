package ru.nikitakhrenov.shop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;
import ru.nikitakhrenov.shop.domain.Category;

import java.util.*;

import static ru.nikitakhrenov.shop.domain.Category.PATH_SEPARATOR;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Category find(String path) {
        Query query = new Query();
        query.addCriteria(Criteria.where("path").is(path));
        Category root = mongoTemplate.findOne(query, Category.class);
        if (root == null) {
            return null;
        }
        query = new Query();
        query.addCriteria(Criteria.where("path").regex("^" + path + "[.]"));
        List<Category> descendants = mongoTemplate.find(query, Category.class);
        return build(root, descendants);
    }

    @Override
    public String create(Category category, String parentPath) {
        mongoTemplate.insert(category);
        String id = category.getId();
        String path = StringUtils.isEmpty(parentPath)
                ? id
                : buildPath(parentPath, id);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = Update.update("path", path);
        mongoTemplate.updateFirst(query, update, Category.class);

        return path;
    }

    private Category build(Category root, List<Category> descendants) {
        Map<String, Category> pathToCategoryMap = new HashMap<>();
        pathToCategoryMap.put(root.getPath(), root);
        for (Category category : descendants) {
            pathToCategoryMap.put(category.getPath(), category);
        }
        for (Category category : descendants) {
            String parentPath = getParentPath(category.getPath());
            if (parentPath.equals(root.getPath())) {
                root.getChildren().add(category);
            } else {
                Category parent = pathToCategoryMap.get(parentPath);
                if (parent != null) {
                    parent.getChildren().add(category);
                }
            }
            List<String> ancestorsPath = new ArrayList<>();
            buildAncestors(parentPath, ancestorsPath);
            for (String ancestorPath : ancestorsPath) {
                category.getAncestors().add(pathToCategoryMap.get(ancestorPath));
            }
        }
        return root;
    }

    private void buildAncestors(String path, List<String> result) {
        if (path == null) {
            Collections.reverse(result);
            return;
        }
        result.add(path);
        buildAncestors(getParentPath(path), result);
    }

    private String getParentPath(String currentPath) {
        int endIndex = currentPath.lastIndexOf(PATH_SEPARATOR);
        return endIndex != -1
                ? currentPath.substring(0, endIndex)
                : null;
    }

    private String buildPath(String parentPath, String id) {
        return parentPath + PATH_SEPARATOR + id;
    }
}
