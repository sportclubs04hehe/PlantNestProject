package vietnamese.com.PlantNest.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import vietnamese.com.PlantNest.entity.Role;
import java.util.Collections;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;
import static vietnamese.com.PlantNest.entity.Role.USER;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
@Getter @Setter
@NoArgsConstructor

public class UserDTO {

    private Long userId;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "username khong duoc de trong")
    private String username;
    @Length(max = 255)
    @NotEmpty(message = "full name khong duoc de trong")
    private String fullName;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "email khong duoc de trong")
    @Email(message = "email khong dung dinh dang")
    @Length(max = 255)
    private String email;
    @NotBlank(message = "password khong duoc de trong")
    @Length(max = 255)
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
