package vietnamese.com.PlantNest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vietnamese.com.PlantNest.entity.Category;
import vietnamese.com.PlantNest.entity.Plant;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Long> {
    List<Plant> findByCategory(Category category);
    List<Plant> findByPriceLessThanEqual(BigDecimal price);
    List<Plant> findByCategoryAndPriceLessThanEqual(Category category,BigDecimal price);
    List<Plant> findByCategoryAndNameContaining(Category category, String name);
    List<Plant> findByCategoryAndPriceLessThanEqualAndNameContaining(
            Category category, BigDecimal price, String name
    );
}
