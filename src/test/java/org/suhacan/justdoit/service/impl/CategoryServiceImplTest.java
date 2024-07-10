package org.suhacan.justdoit.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.suhacan.justdoit.dto.model.CategoryDto;
import org.suhacan.justdoit.entity.Category;
import org.suhacan.justdoit.mapper.CategoryMapper;
import org.suhacan.justdoit.repository.CategoryRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCategoryList_shouldReturnCategoryDtoList() {
        String userId = "user123";

        Category category1 = CategoryServiceImplTestData
                .generateCategory("category123", "Test Category", userId);
        Category category2 = CategoryServiceImplTestData
                .generateCategory("category456", "Test Category 2", userId);
        List<Category> categoryList = Arrays.asList(category1, category2);

        CategoryDto categoryDto1 = CategoryServiceImplTestData
                .generateCategoryDto("Test Category", "category123", null);
        CategoryDto categoryDto2 = CategoryServiceImplTestData
                .generateCategoryDto("Test Category 2", "category456", null);
        List<CategoryDto> categoryDtoList = Arrays.asList(categoryDto1, categoryDto2);

        when(categoryRepository.findAllByUserId(anyString())).thenReturn(categoryList);
        when(categoryMapper.toDto(categoryList)).thenReturn(categoryDtoList);

        var serviceResponse = categoryService.getCategoryList(userId);

        verify(categoryRepository).findAllByUserId(userId);
        verify(categoryMapper).toDto(categoryList);
        assertEquals(categoryDtoList, serviceResponse);
    }

    @Test
    void addCategory_ShouldReturnSavedCategoryDto() {
        String userId = "user123";
        CategoryDto inputDto = CategoryServiceImplTestData
                .generateCategoryDto("Test Category", null, null);

        Category savedCategory = CategoryServiceImplTestData
                .generateCategory("category123", "Test Category", userId);

        CategoryDto expectedDto = CategoryServiceImplTestData
                .generateCategoryDto("Test Category", "category123", null);

        when(categoryMapper.toEntity(inputDto))
                .thenReturn(
                        CategoryServiceImplTestData
                                .generateCategory("category123", "Test Category", userId)
                );

        when(categoryRepository.save(any(Category.class)))
                .thenReturn(savedCategory);

        when(categoryMapper.toDto(savedCategory))
                .thenReturn(expectedDto);

        var serviceResponse = categoryService.addCategory(userId, inputDto);

        assertEquals(expectedDto, serviceResponse);
    }

    @Test
    void updateCategory_shouldReturnUpdatedCategoryDto() {

        String categoryId = "category123";
        CategoryDto categoryDto = CategoryServiceImplTestData
                .generateCategoryDto("Updated Name", null, null);

        Category existingCategory = CategoryServiceImplTestData
                .generateCategory(categoryId, "Category Name", null);

        Category updatedCategory = CategoryServiceImplTestData
                .generateCategory(categoryId, "Updated Name", null);

        CategoryDto expectedCategoryDto = CategoryServiceImplTestData
                .generateCategoryDto("Updated Name", null, null);

        when(categoryRepository
                .findById(categoryId))
                .thenReturn(java.util.Optional.of(existingCategory));
        when(categoryRepository
                .save(any(Category.class)))
                .thenReturn(updatedCategory);
        when(categoryMapper
                .toDto(updatedCategory))
                .thenReturn(expectedCategoryDto);

        var serviceResponse = categoryService.updateCategory(categoryId, categoryDto);

        verify(categoryRepository).findById(categoryId);
        verify(categoryRepository).save(existingCategory);
        verify(categoryMapper).toDto(updatedCategory);

        assertEquals(expectedCategoryDto, serviceResponse);
    }

    @Test
    void deleteCategory_shouldReturnCategoryId() {
        String categoryId = "category123";
        Category existingCategory = CategoryServiceImplTestData
                .generateCategory(categoryId, "Category Name", null);

        when(categoryRepository
                .findById(categoryId))
                .thenReturn(java.util.Optional.of(existingCategory));

        var serviceResponse = categoryService.deleteCategory(categoryId);

        verify(categoryRepository).findById(categoryId);
        verify(categoryRepository).delete(existingCategory);
        assertEquals(categoryId, serviceResponse);
    }
}