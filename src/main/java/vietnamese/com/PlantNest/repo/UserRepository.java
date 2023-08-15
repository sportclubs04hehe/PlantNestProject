package vietnamese.com.PlantNest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vietnamese.com.PlantNest.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
    User findUserByEmail (String email);
}
