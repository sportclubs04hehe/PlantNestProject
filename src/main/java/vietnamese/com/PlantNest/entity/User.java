package vietnamese.com.PlantNest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Length(max = 255)
    private String fullName;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Email
    @Length(max = 255)
    private String email;

    @NotBlank
    @Length(max = 255)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(STRING)
    private Set<Role> roles;

    public User(String username, String fullName, String email, String password, Set<Role> roles) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
