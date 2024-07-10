package org.suhacan.justdoit.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.suhacan.justdoit.dto.model.CategoryDto;
import org.suhacan.justdoit.entity.Category;
import org.suhacan.justdoit.mapper.CategoryMapper;
import org.suhacan.justdoit.repository.CategoryRepository;
import org.suhacan.justdoit.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto addCategory(String userId, CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategoryList(String userId) {
        List<Category> categoryList = categoryRepository.findAllByUserId(userId);
        return categoryMapper.toDto(categoryList);
    }

    @Override
    public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {
        Category category = getCategoryByIdOrThrow(categoryId);

        category.setName(categoryDto.getName());
        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    public String deleteCategory(String categoryId) {
        Category category = getCategoryByIdOrThrow(categoryId);
        categoryRepository.delete(category);
        return category.getId();
    }

    private Category getCategoryByIdOrThrow(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
}
