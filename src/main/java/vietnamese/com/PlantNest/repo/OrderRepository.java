package vietnamese.com.PlantNest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vietnamese.com.PlantNest.entity.Order;
import vietnamese.com.PlantNest.entity.OrderStatus;
import vietnamese.com.PlantNest.entity.Plant;
import vietnamese.com.PlantNest.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUsers(User user);
    List<Order> findByPlants(Plant plant);
    List<Order> findByUsersAndPlants(User users, Plant plants);
    List<Order> findByUsersAndTotalAmountGreaterThanEqual(User users, BigDecimal totalAmount);
    List<Order> findByUsersAndPlantsAndTotalAmountGreaterThanEqual(
      User users, Plant plants, BigDecimal totalAmount
    );

    // tính tổng giá trị đơn hàng cho 1 người dùng:
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.users = :user")
    BigDecimal sumTotalAmountByUsers(User user);

    //Lấy danh sách đơn hàng theo khoảng thời gian
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
    int countByUsers (User user);
    @Query("SELECT o FROM Order o WHERE o.orderStatus = :status")
    List<Order> findByOrderStatusEnum(OrderStatus orderStatus);
    void deleteByOrderId(Long orderId);
}
