package com.ecommerce.ecommerce.specification;

import org.springframework.data.jpa.domain.Specification;
import com.ecommerce.ecommerce.model.Category;

public class CategorySpecification {


    public static Specification<Category> hasNama(String nama) {
        return (root, query, criteriaBuilder) -> 
            nama == null ? null : criteriaBuilder.like(root.get("nama"), "%" + nama + "%");
    }

    public static Specification<Category> hasIcon(String icon) {
        return (root, query, criteriaBuilder) -> 
            icon == null ? null : criteriaBuilder.like(root.get("icon"), "%" + icon + "%");
    }
}
