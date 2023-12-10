package com.springapps.newangler.controller;

import com.springapps.newangler.dto.UserCreateRequest;
import com.springapps.newangler.dto.UserDto;
import com.springapps.newangler.dto.UserResponse;
import com.springapps.newangler.mapper.UserMapper;
import com.springapps.newangler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.findByUserId(userId);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserDto userDto) {
        UserDto createdUserDto = userService.createNewUser(userDto);
        if (createdUserDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdUserDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId, @RequestBody UserDto userDto) {
        Optional<UserDto> optionalUpdatedUser = userService.updateUser(userId, userDto);
        return optionalUpdatedUser
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}






