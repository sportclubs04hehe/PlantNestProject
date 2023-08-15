package vietnamese.com.PlantNest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vietnamese.com.PlantNest.entity.Role;

import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

public class UserDTO {
    private Long userId;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private Set<Role> roles;

    public UserDTO(String username, String fullName, String email,String password, Set<Role> roles) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
