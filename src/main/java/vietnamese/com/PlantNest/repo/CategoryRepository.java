package vietnamese.com.PlantNest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vietnamese.com.PlantNest.entity.Category;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByName (String name);
}
