package vietnamese.com.PlantNest.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "categoryId")
@Getter @Setter
@NoArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String name;

    public CategoryDTO(String name) {
        this.name = name;
    }
}
