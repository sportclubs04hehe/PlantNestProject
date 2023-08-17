package vietnamese.com.PlantNest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vietnamese.com.PlantNest.dto.CategoryDTO;
import vietnamese.com.PlantNest.entity.Category;
import vietnamese.com.PlantNest.exception.UserNotFoundException;
import vietnamese.com.PlantNest.repo.CategoryRepository;
import vietnamese.com.PlantNest.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public Category convertToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    @Override
    public CategoryDTO convertToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findCategoryByName(String name) {
        if (!name.isEmpty()) {
            Category category = categoryRepository.findByName(name);
            if (category != null) {
                return convertToDTO(category);
            }
        }
        throw new UserNotFoundException("username not found");
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        category = categoryRepository.save(category);
        return convertToDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category exitingCategory = categoryRepository.findCategoriesByCategoryId(id)
                .orElseThrow(() -> new UserNotFoundException("category not found"));
        updateUserFromDTO(exitingCategory,categoryDTO);
        return convertToDTO(categoryRepository.save(exitingCategory));
    }

    private void updateUserFromDTO(Category category, CategoryDTO categoryDTO) {
        category.setName(categoryDTO.getName());
    }
    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findCategoriesByCategoryId(id)
                .orElseThrow(() -> new UserNotFoundException("id:" + id + " not found"));
        categoryRepository.delete(category);
    }
}
