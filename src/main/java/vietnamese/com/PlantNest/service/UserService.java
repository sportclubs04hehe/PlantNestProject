package vietnamese.com.PlantNest.service;

import org.springframework.stereotype.Service;
import vietnamese.com.PlantNest.dto.UserDTO;
import vietnamese.com.PlantNest.entity.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO convertToDTO(User user);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByEmail(String email);

    UserDTO createUser(UserDTO userDTO);
}
