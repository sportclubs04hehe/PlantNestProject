package vietnamese.com.PlantNest.service;

import org.springframework.stereotype.Service;
import vietnamese.com.PlantNest.dto.UserDTO;
import vietnamese.com.PlantNest.entity.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    User convertToEntity(UserDTO userDTO);
    UserDTO convertToDTO(User user);

    UserDTO findUserById (Long userId);
    UserDTO findUserByUsername(String username);

    UserDTO findUserByEmail(String email);

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);

}
