package vietnamese.com.PlantNest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vietnamese.com.PlantNest.dto.CategoryDTO;
import vietnamese.com.PlantNest.dto.UserDTO;
import vietnamese.com.PlantNest.exception.UserNotFoundException;
import vietnamese.com.PlantNest.service.CategoryService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDTO>> getAll(){
        List<CategoryDTO> getCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(getCategories, OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO categoryDTO1 = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(CREATED).body(categoryDTO1);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
            return ResponseEntity.ok(updatedCategory);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("name/{name}")
    public ResponseEntity<CategoryDTO> findCategoryByName(@PathVariable("name") String name) {
        try {
            CategoryDTO categoryDTO = categoryService.findCategoryByName(name);
            return new ResponseEntity<>(categoryDTO, OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(OK);
    }
}
