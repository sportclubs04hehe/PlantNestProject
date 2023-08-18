package vietnamese.com.PlantNest.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user")
    private User users;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("plant")
    private Plant plants;

    @Min(1)
    private int quantity;
    @NotNull
    private LocalDateTime orderDate;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal totalAmount;
    @Enumerated(STRING)
    private OrderStatus orderStatus;

    public Order(User users, Plant plants, int quantity, LocalDateTime orderDate, BigDecimal totalAmount, OrderStatus orderStatus) {
        this.users = users;
        this.plants = plants;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }
}
