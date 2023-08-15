package vietnamese.com.PlantNest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plants;
    @Min(1)
    private int quantity;
    @NotNull
    private LocalDateTime orderDate;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal totalAmount;

    public Order(User users, Plant plants, int quantity, LocalDateTime orderDate, BigDecimal totalAmount) {
        this.users = users;
        this.plants = plants;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
}
