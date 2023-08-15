package vietnamese.com.PlantNest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vietnamese.com.PlantNest.entity.Order;
import vietnamese.com.PlantNest.entity.Plant;
import vietnamese.com.PlantNest.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUsers(User user);
    List<Order> findByPlants(Plant plant);
    List<Order> findByUsersAndPlants(User users, Plant plants);
    List<Order> findByUsersAndTotalAmountGreaterThanEqual(User users, BigDecimal totalAmount);
    List<Order> findByUsersAndPlantsAndTotalAmountGreaterThanEqual(
      User users, Plant plants, BigDecimal totalAmount
    );
}
