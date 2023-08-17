package vietnamese.com.PlantNest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vietnamese.com.PlantNest.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter @Setter
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private UserDTO user;
    private PlantDTO plant;
    private int quantity;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;

    public OrderDTO(UserDTO user, PlantDTO plant, int quantity, LocalDate orderDate, BigDecimal totalAmount,OrderStatus orderStatus) {
        this.user = user;
        this.plant = plant;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }
}
