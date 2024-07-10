package org.suhacan.justdoit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhacan.justdoit.dto.model.CategoryDto;
import org.suhacan.justdoit.dto.request.category.AddCategoryRequest;
import org.suhacan.justdoit.dto.request.category.UpdateCategoryRequest;
import org.suhacan.justdoit.dto.response.category.GetCategoryResponse;
import org.suhacan.justdoit.mapper.CategoryMapper;
import org.suhacan.justdoit.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody @Valid AddCategoryRequest request){
        CategoryDto categoryDto = categoryMapper.addCategoryRequestToCategoryDto(request);
        // todo duzelt
        categoryDto = categoryService.addCategory(request.getUserId(), categoryDto);
        return ResponseEntity.ok(categoryDto.getId());
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<GetCategoryResponse>> getCategoryList(@PathVariable("userId") String userId){
        List<CategoryDto> categoryDtoList = categoryService.getCategoryList(userId);
        return ResponseEntity.ok(categoryMapper.categoryDtoListToGetCategoryResponseList(categoryDtoList));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("categoryId") String categoryId, @RequestBody @Valid UpdateCategoryRequest request){
        CategoryDto categoryDto = categoryMapper.updateCategoryRequestToCategoryDto(request);
        categoryDto = categoryService.updateCategory(categoryId, categoryDto);
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") String categoryId){
        String deletedCategoryId = categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(deletedCategoryId);
    }
}
