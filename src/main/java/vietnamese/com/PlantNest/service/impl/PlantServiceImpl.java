package vietnamese.com.PlantNest.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vietnamese.com.PlantNest.dto.CategoryDTO;
import vietnamese.com.PlantNest.dto.PlantDTO;
import vietnamese.com.PlantNest.entity.Category;
import vietnamese.com.PlantNest.entity.Plant;
import vietnamese.com.PlantNest.exception.UserNotFoundException;
import vietnamese.com.PlantNest.repo.PlantRepository;
import vietnamese.com.PlantNest.service.PlantService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {
    @Autowired
    private final PlantRepository plantRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public List<PlantDTO> getAllPlants() {
        List<Plant> plants = plantRepository.findAll();
        return plants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlantDTO findPlantById(Long id) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("Plant not found with id: " + id)
                );
        return convertToDTO(plant);
    }

    @Override
    public PlantDTO createPlant(PlantDTO plantDTO) {
        Plant plant = convertToEntity(plantDTO);
        Plant createdPlant = plantRepository.save(plant);
        return convertToDTO(createdPlant);
    }

    @Override
    public PlantDTO updatePlant(Long id, PlantDTO plantDTO) {
        Plant existingPlant = plantRepository.findPlantByPlantId(id)
                .orElseThrow(
                        () -> new UserNotFoundException("Plant not found with id: " + id)
                );
        updatedPlant(existingPlant, plantDTO);
        return convertToDTO(plantRepository.save(existingPlant));
    }

    public void updatedPlant(Plant plant, PlantDTO plantDTO) {
        plant.setName(plantDTO.getName());
        plant.setDescription(plantDTO.getDescription());
        plant.setPrice(plantDTO.getPrice());
        plant.setImageUrl(plantDTO.getImageUrl());
        plant.setCategory(modelMapper.map(plantDTO.getCategory(), Category.class));
    }

    @Override
    public void deletePlant(Long id) {
        Plant plant = plantRepository.findPlantByPlantId(id)
                .orElseThrow(
                        () -> new UserNotFoundException("Plant not found with id: " + id)
                );
        plantRepository.delete(plant);
    }

    /**
     * search categories in plant
     */
    public List<PlantDTO> getPlantsByCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper
                .map(categoryDTO, Category.class); // khoi tao doi tuong category moi duoc lay tu getCategoryDto
        List<Plant> plants = plantRepository
                .findByCategory(category); // gan
        return plants.stream()
                .map(this::convertToDTO) // mapping sang dto
                .collect(Collectors.toList());
    }

    /**
     * lấy những sản phầm giá thấp hơn giá đề xuất
     */
    @Override
    public List<PlantDTO> getPlantsByPriceLessThanEqual(BigDecimal price) {
        List<Plant> plants = plantRepository
                .findByPriceLessThanEqual(price);
        return plants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * tìm kiếm theo categoryId và prices
     */
    @Override
    public List<PlantDTO> getPlantsByCategoryAndPriceLessThanEqual(
            CategoryDTO categoryDTO,
            BigDecimal price) {
        Category category = modelMapper
                .map(categoryDTO, Category.class);
        List<Plant> plants = plantRepository
                .findByCategoryAndPriceLessThanEqual(category, price);
        return plants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // tìm kiếm theo categoryId và tên sản phẩm
    @Override
    public List<PlantDTO> getPlantsByCategoryAndNameContaining(
            CategoryDTO categoryDTO,
            String name) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        List<Plant> plants = plantRepository
                .findByCategoryAndNameContaining(category, name);
        return plants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * tìm theo categoryId, price and tên sản phẩm
     */
    @Override
    public List<PlantDTO> getPlantsByCategoryAndPriceLessThanEqualAndNameContaining(
            CategoryDTO categoryDTO,
            BigDecimal price, String name) {
        Category category = modelMapper.map(categoryDTO, Category.class); // mapping
        List<Plant> plants = plantRepository
                .findByCategoryAndPriceLessThanEqualAndNameContaining(
                        category, price, name
                ); // add properties dong bo voi cau truy van
        return plants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlantDTO convertToDTO(Plant plant) {
        return modelMapper.map(plant, PlantDTO.class);
    }

    @Override
    public Plant convertToEntity(PlantDTO plantDTO) {
        return modelMapper.map(plantDTO, Plant.class);
    }
}
