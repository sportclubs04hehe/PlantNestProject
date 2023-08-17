package vietnamese.com.PlantNest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vietnamese.com.PlantNest.dto.CategoryDTO;
import vietnamese.com.PlantNest.dto.PlantDTO;
import vietnamese.com.PlantNest.exception.UserNotFoundException;
import vietnamese.com.PlantNest.service.PlantService;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "/api/v1/plant")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class PlantController {
    @Autowired
    private final PlantService plantService;

    @GetMapping("/list")
    public ResponseEntity<List<PlantDTO>> getAll() {
        List<PlantDTO> getPlants = plantService.getAllPlants();
        if (getPlants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(getPlants, OK);
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<PlantDTO> findPlantById(@PathVariable("id") Long id) {
        try {
            PlantDTO plantDTO = plantService.findPlantById(id);
            return new ResponseEntity<>(plantDTO, OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PlantDTO> createPlant(@Valid @RequestBody PlantDTO plantDTO) {

        PlantDTO plantDTO1 = plantService.createPlant(plantDTO);
        return ResponseEntity.status(CREATED).body(plantDTO1);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlantDTO> updatePlant(
            @Valid @PathVariable("id") Long id,
            @RequestBody PlantDTO plantDTO) {
        try {
            PlantDTO plantDTO1 = plantService.updatePlant(id, plantDTO);
            return ResponseEntity.ok(plantDTO1);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlant(@PathVariable("id") Long id) {
        plantService.deletePlant(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PlantDTO>> getPlantsByCategory(
            @PathVariable Long categoryId)
    { // tim id truyen vao
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId); //set id bang voi id tren fc
        List<PlantDTO> plants = plantService
                .getPlantsByCategory(categoryDTO); //gan
        if (plants.isEmpty()) { //neu trong
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(plants, OK);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<PlantDTO>> getPlantsByPriceLessThanEqual(@PathVariable BigDecimal price)
    {
        List<PlantDTO> plantDTOS = plantService
                .getPlantsByPriceLessThanEqual(price);
        if (plantDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(plantDTOS);
    }

    @GetMapping("/category-price/{categoryId}/{price}")
    public ResponseEntity<List<PlantDTO>> getPlantsByCategoryAndPriceLessThanEqual(
            @PathVariable Long categoryId,
            @PathVariable BigDecimal price) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId);
        List<PlantDTO> plantDTOS = plantService
                .getPlantsByCategoryAndPriceLessThanEqual(categoryDTO, price);
        if (plantDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(plantDTOS, OK);
    }

    @GetMapping("/category-name/{categoryId}/{name}")
    public ResponseEntity<List<PlantDTO>> getPlantsByCategoryAndNameContaining(
            @PathVariable Long categoryId,
            @PathVariable String name) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId);
        List<PlantDTO> plants = plantService
                .getPlantsByCategoryAndNameContaining(categoryDTO, name);
        if (plants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(plants);
    }

    @GetMapping("/category-price-name/{categoryId}/{price}/{name}")
    public ResponseEntity<List<PlantDTO>> getPlantsByCategoryAndPriceLessThanEqualAndNameContaining(
            @PathVariable Long categoryId,
            @PathVariable BigDecimal price,
            @PathVariable String name) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId);
        List<PlantDTO> plants = plantService
                .getPlantsByCategoryAndPriceLessThanEqualAndNameContaining(categoryDTO, price, name);
        if (plants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(plants);
    }
}
