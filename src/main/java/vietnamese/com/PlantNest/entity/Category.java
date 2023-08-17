package vietnamese.com.PlantNest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "category")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long categoryId;
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "category")
    private Set<Plant> plants;

    public Category(String name) {
        this.name = name;
    }
}
