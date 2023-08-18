package vietnamese.com.PlantNest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vietnamese.com.PlantNest.dto.OrderDTO;
import vietnamese.com.PlantNest.dto.PlantDTO;
import vietnamese.com.PlantNest.dto.UserDTO;
import vietnamese.com.PlantNest.entity.Order;
import vietnamese.com.PlantNest.entity.OrderStatus;
import vietnamese.com.PlantNest.repo.OrderRepository;
import vietnamese.com.PlantNest.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return null;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order createdOrder = orderRepository.save(order);
        return convertToDTO(createdOrder);
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }

    @Override
    public List<OrderDTO> getOrdersByUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByPlant(PlantDTO plantDTO) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByUserAndPlant(UserDTO userDTO, PlantDTO plantDTO) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByUserAndTotalAmountGreaterThanOrEqual(UserDTO userDTO, BigDecimal totalAmount) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByUserAndPlantAndTotalAmountGreaterThanOrEqual(UserDTO userDTO, PlantDTO plantDTO, BigDecimal totalAmount) {
        return null;
    }

    @Override
    public BigDecimal getTotalOrderAmountByUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public int getOrderCountByUser(UserDTO userDTO) {
        return 0;
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(OrderStatus status) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }

    @Override
    public OrderDTO convertToDTO(Order order) {
        return modelMapper.map(order,OrderDTO.class);
    }

    @Override
    public Order convertToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO,Order.class);
    }
}
