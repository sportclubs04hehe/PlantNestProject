package vietnamese.com.PlantNest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
public class PlantDTO {
    private Long plantId;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private CategoryDTO category;

    public PlantDTO(String name, String description, BigDecimal price, String imageUrl, CategoryDTO category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
    }
}
