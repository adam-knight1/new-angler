package com.springapps.newangler.service;

import com.springapps.newangler.dto.UserDto;
import com.springapps.newangler.mapper.UserMapper;
import com.springapps.newangler.repository.UserRepository;
import com.springapps.newangler.repository.model.UserRecord;
import com.springapps.newangler.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto findByUserId(String userId) {
        System.out.println("Searching for userId: " + userId);
        return userRepository.findById(userId)
                .map(UserMapper::toDto)
                .orElse(null);
    }

    public UserDto createNewUser(UserDto userDto) {
        UserRecord userRecord = UserMapper.toEntity(userDto);
        try {
            userRepository.save(userRecord);
            return userDto; // Optionally, return a new DTO instance
        } catch (Exception e) {
            System.out.println("Unable to save user: " + e.getMessage());
            return null;
        }
    }

    public Optional<UserDto> updateUser(String userId, UserDto updatedUserInfo) {
        Optional<UserRecord> optionalExistingUser = userRepository.findById(userId);

        if (optionalExistingUser.isPresent()) {
            UserRecord existingUser = optionalExistingUser.get();
            existingUser.setEmail(updatedUserInfo.getEmail());
            existingUser.setPassword(updatedUserInfo.getPassword());
            existingUser.setUsername(updatedUserInfo.getUsername());
            userRepository.save(existingUser);
            return Optional.of(UserMapper.toDto(existingUser));
        }
        return Optional.empty();
    }

    public boolean deleteUser(String userId) {
        Optional<UserRecord> optionalUserRecord = userRepository.findById(userId);
        if (optionalUserRecord.isPresent()) {
            userRepository.delete(optionalUserRecord.get());
            return true;
        } else {
            System.out.println("User not found for deletion.");
            return false;
        }
    }
}





