package org.suhacan.justdoit.service;

import org.springframework.stereotype.Service;
import org.suhacan.justdoit.dto.model.CategoryDto;

import java.util.List;

@Service
public interface CategoryService {

    CategoryDto addCategory(String userId, CategoryDto categoryDto);
    List<CategoryDto> getCategoryList(String userId);
    CategoryDto updateCategory(String categoryId, CategoryDto categoryDto);
    String deleteCategory(String categoryId);
}
