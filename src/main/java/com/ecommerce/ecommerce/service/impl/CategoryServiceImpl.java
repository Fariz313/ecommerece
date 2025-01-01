package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import com.ecommerce.ecommerce.service.CategoryService;
import com.ecommerce.ecommerce.specification.CategorySpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository CategoryRepository;

    // save Category in database
    @Override
    public Category saveCategory(Category Category) {
        return CategoryRepository.save(Category);
    }

    // get all Category form database
    @Override
    public List<Category> getAllCategory(String nama, String icon) {
        Specification<Category> spec = Specification.where(CategorySpecification.hasNama(nama))
            .and(CategorySpecification.hasIcon(icon));

        return CategoryRepository.findAll(spec);
    }

    // get Category using id
    @Override
    public Category getCategoryById(long id) {
        Optional<Category> Category = CategoryRepository.findById(id);
        if (Category.isPresent()) {
            return Category.get();
        } else {
            throw new RuntimeException();
        }
    }

    // update Category
    @Override
    public Category updateCategory(Category Category, long id) {
        Category existingCategory = CategoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException());
        existingCategory.setNama(Category.getNama());
        existingCategory.setIcon(Category.getIcon());
        // save
        CategoryRepository.save(existingCategory);
        return existingCategory;
    }

    // delete Category
    @Override
    public void deleteCategory(long id) {
        // check
        CategoryRepository.findById(id).orElseThrow(() -> new RuntimeException());
        // delete
        CategoryRepository.deleteById(id);
    }

}