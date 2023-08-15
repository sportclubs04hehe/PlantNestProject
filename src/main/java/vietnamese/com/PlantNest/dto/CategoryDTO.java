package vietnamese.com.PlantNest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String name;

    public CategoryDTO(String name) {
        this.name = name;
    }
}
