package vietnamese.com.PlantNest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vietnamese.com.PlantNest.dto.UserDTO;
import vietnamese.com.PlantNest.entity.Role;
import vietnamese.com.PlantNest.entity.User;
import vietnamese.com.PlantNest.exception.UserNotFoundException;
import vietnamese.com.PlantNest.repo.UserRepository;
import vietnamese.com.PlantNest.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static vietnamese.com.PlantNest.entity.Role.USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User newUser = convertToEntity(userDTO);
        newUser = userRepository.save(newUser);
        return convertToDTO(newUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findUserByUserId(id).orElseThrow(() -> new UserNotFoundException("Id not found exception"));
        updateUserFromDTO(user, userDTO);
        return convertToDTO(userRepository.save(user));
    }

    private void updateUserFromDTO(User user, UserDTO userDTO) {
        user.setUsername(userDTO.getUsername());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User convertToEntity(UserDTO userDTO) {
//        return new User(userDTO.getUsername(), userDTO.getFullName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getRoles());
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO convertToDTO(User user) {
//        return new UserDTO(user.getUsername(), user.getFullName(), user.getEmail(), user.getPassword(), user.getRoles());
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserById(Long userId) {
        return convertToDTO(userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("id not found")));
    }


    @Override
    public UserDTO findUserByUsername(String username) {
        if (!username.isEmpty()) {
            User user = userRepository.findUserByUsername(username);
            if (user != null) {
                return convertToDTO(user);
            }
        }
        throw new UserNotFoundException("Username not found");
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        if (!email.isEmpty()) {
            User user = userRepository.findUserByEmail(email);
            if (user != null) {
                return convertToDTO(user);
            }
        }
        throw new UserNotFoundException("Email not found");
    }

}
