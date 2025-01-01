package com.ecommerce.ecommerce.service;
import com.ecommerce.ecommerce.model.Category;
import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getAllCategory(String nama, String icon);
    Category getCategoryById(long id);
    Category updateCategory(Category category,long id);
    void deleteCategory(long id);
}