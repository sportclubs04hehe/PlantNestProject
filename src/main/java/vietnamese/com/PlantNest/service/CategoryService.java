package vietnamese.com.PlantNest.service;

import vietnamese.com.PlantNest.dto.CategoryDTO;
import vietnamese.com.PlantNest.dto.UserDTO;
import vietnamese.com.PlantNest.entity.Category;
import vietnamese.com.PlantNest.entity.User;

import java.util.List;

public interface CategoryService {
    Category convertToEntity(CategoryDTO categoryDTO);
    CategoryDTO convertToDTO(Category category);
    List<CategoryDTO> getAllCategories();
    CategoryDTO findCategoryByName(String name);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
