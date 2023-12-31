package vietnamese.com.PlantNest.service;

import vietnamese.com.PlantNest.dto.OrderDTO;
import vietnamese.com.PlantNest.dto.PlantDTO;
import vietnamese.com.PlantNest.dto.UserDTO;
import vietnamese.com.PlantNest.entity.Order;
import vietnamese.com.PlantNest.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
    OrderDTO createOrder(OrderDTO orderDTO); // create
    OrderDTO updateOrder(Long id, OrderDTO orderDTO);
    void deleteOrder(Long id);

    List<OrderDTO> getOrdersByUser(UserDTO userDTO);
    List<OrderDTO> getOrdersByPlant(PlantDTO plantDTO);
    List<OrderDTO> getOrdersByUserAndPlant(UserDTO userDTO, PlantDTO plantDTO);
    List<OrderDTO> getOrdersByUserAndTotalAmountGreaterThanOrEqual(UserDTO userDTO, BigDecimal totalAmount);
    List<OrderDTO> getOrdersByUserAndPlantAndTotalAmountGreaterThanOrEqual(
            UserDTO userDTO, PlantDTO plantDTO, BigDecimal totalAmount
    );

    BigDecimal getTotalOrderAmountByUser(UserDTO userDTO);
    List<OrderDTO> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    int getOrderCountByUser(UserDTO userDTO);
    List<OrderDTO> getOrdersByStatus(OrderStatus status);
    void cancelOrder(Long orderId);

    //mapping
    OrderDTO convertToDTO(Order order);
    Order convertToEntity(OrderDTO orderDTO);

}
