package org.suhacan.justdoit.service.impl;

import org.suhacan.justdoit.dto.model.CategoryDto;
import org.suhacan.justdoit.entity.Category;

public class CategoryServiceImplTestData {
    static Category generateCategory(String categoryId, String categoryName, String userId) {
        Category savedCategory = new Category();
        savedCategory.setId(categoryId);
        savedCategory.setName(categoryName);
        return savedCategory;
    }

    static CategoryDto generateCategoryDto(String categoryName, String id, String userId) {
        return CategoryDto.builder()
                .name(categoryName)
                .id(id)
                .userId(userId)
                .build();
    }
}