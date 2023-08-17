package vietnamese.com.PlantNest.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vietnamese.com.PlantNest.dto.UserDTO;
import vietnamese.com.PlantNest.entity.Role;
import vietnamese.com.PlantNest.entity.User;
import vietnamese.com.PlantNest.exception.UserNotFoundException;
import vietnamese.com.PlantNest.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;
import static vietnamese.com.PlantNest.entity.Role.USER;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> getUsers = userService.getAllUsers();
        if(getUsers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(getUsers, OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        Set<Role> roles = userDTO.getRoles();
        if (roles == null || roles.isEmpty()) {
            roles = Collections.singleton(USER);
        }
        userDTO.setRoles(roles);
        UserDTO userDTO1 = userService.createUser(userDTO);
        return ResponseEntity.status(CREATED).body(userDTO1);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") Long id) {
        try {
            UserDTO userDTO = userService.findUserById(id);
            return new ResponseEntity<>(userDTO, OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("username/{username}")
    public ResponseEntity<UserDTO> findUserByUsername(@PathVariable("username") String username) {
        try {
            UserDTO userDTO = userService.findUserByUsername(username);
            return new ResponseEntity<>(userDTO, OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserDTO> findUserByEmail(@PathVariable("email") String email) {
        try {
            UserDTO userDTO = userService.findUserByEmail(email);
            return new ResponseEntity<>(userDTO, OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(OK);
    }
}
