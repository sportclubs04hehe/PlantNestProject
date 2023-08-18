package vietnamese.com.PlantNest.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "plantId")
@Getter
@Setter
@Entity
@Table(name = "plants")
@NoArgsConstructor
public class Plant {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long plantId;
    @NotBlank
    @Length(max = 100)
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category category;

    public Plant(String name, String description, BigDecimal price, String imageUrl, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
    }
}
