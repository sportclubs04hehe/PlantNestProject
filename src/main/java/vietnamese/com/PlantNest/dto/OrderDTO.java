package vietnamese.com.PlantNest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vietnamese.com.PlantNest.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    @JsonProperty("user")
    private UserDTO users;
    @JsonProperty("plant")
    private PlantDTO plants;
    private int quantity;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;

    public OrderDTO(UserDTO users, PlantDTO plants, int quantity, LocalDateTime orderDate, BigDecimal totalAmount,OrderStatus orderStatus) {
        this.users = users;
        this.plants = plants;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }
}
