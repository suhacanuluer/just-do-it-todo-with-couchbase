package org.suhacan.justdoit.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.suhacan.justdoit.dto.model.CategoryDto;
import org.suhacan.justdoit.dto.request.category.AddCategoryRequest;
import org.suhacan.justdoit.dto.request.category.UpdateCategoryRequest;
import org.suhacan.justdoit.dto.response.category.GetCategoryResponse;
import org.suhacan.justdoit.entity.Category;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {
    List<GetCategoryResponse> categoryDtoListToGetCategoryResponseList(List<CategoryDto> categoryDtoList);
    CategoryDto addCategoryRequestToCategoryDto(AddCategoryRequest addCategoryRequest);
    CategoryDto updateCategoryRequestToCategoryDto(UpdateCategoryRequest categoryRequest);
}
