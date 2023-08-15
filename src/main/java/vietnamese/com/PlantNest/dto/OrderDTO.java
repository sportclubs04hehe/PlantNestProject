package vietnamese.com.PlantNest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public OrderDTO(UserDTO user, PlantDTO plant, int quantity, LocalDate orderDate, BigDecimal totalAmount) {
        this.user = user;
        this.plant = plant;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
}
