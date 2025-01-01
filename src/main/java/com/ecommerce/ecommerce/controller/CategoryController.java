package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import com.ecommerce.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }

    // GetAll Rest Api
    @GetMapping
    public List<Category> getAllCategory(@RequestParam(required = false) String nama,
            @RequestParam(required = false) String icon) {

        return categoryService.getAllCategory(nama, icon);
    }

    // Get by Id Rest Api
    @GetMapping("{id}")
    // localhost:8080/api/categorys/1
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") long categoryID) {
        return new ResponseEntity<Category>(categoryService.getCategoryById(categoryID), HttpStatus.OK);
    }

    // Update Rest Api
    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") long id,
            @RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.updateCategory(category, id), HttpStatus.OK);
    }

    // Delete Rest Api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long id) {
        // delete category from db
        categoryService.deleteCategory(id);
        return new ResponseEntity<String>("Category deleted Successfully.", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(
            @RequestParam("nama") String nama,
            @RequestParam("icon") String icon) {

        try {
            Category category = new Category();
            category.setNama(nama);
            category.setIcon(icon);

            return new ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}