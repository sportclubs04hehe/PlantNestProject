package vietnamese.com.PlantNest.service;

import vietnamese.com.PlantNest.dto.CategoryDTO;
import vietnamese.com.PlantNest.dto.PlantDTO;
import vietnamese.com.PlantNest.entity.Plant;

import java.math.BigDecimal;
import java.util.List;

public interface PlantService {
    List<PlantDTO> getAllPlants();
    PlantDTO findPlantById(Long id);
    PlantDTO createPlant(PlantDTO plantDTO);
    PlantDTO updatePlant(Long id, PlantDTO plantDTO);
    void deletePlant(Long id);

    List<PlantDTO> getPlantsByCategory(CategoryDTO categoryDTO);
    List<PlantDTO> getPlantsByPriceLessThanEqual(BigDecimal price);
    List<PlantDTO> getPlantsByCategoryAndPriceLessThanEqual(CategoryDTO categoryDTO, BigDecimal price);
    List<PlantDTO> getPlantsByCategoryAndNameContaining(CategoryDTO categoryDTO, String name);
    List<PlantDTO> getPlantsByCategoryAndPriceLessThanEqualAndNameContaining(
            CategoryDTO categoryDTO, BigDecimal price, String name
    );

    PlantDTO convertToDTO(Plant plant);
    Plant convertToEntity(PlantDTO plantDTO);
}
