package com.ecommerce.ecommerce.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ecommerce.ecommerce.model.Alamat;

public class AlamatSpecification {

    public static Specification<Alamat> hasUserId(String userId) {
        return (root, query, criteriaBuilder) -> userId == null ? null
                : criteriaBuilder.equal(root.get("idUser"),Long.parseLong(userId));

    }
}
