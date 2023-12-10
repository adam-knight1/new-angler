package com.springapps.newangler.service;

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

    public User findByUserId(String userId) {
        System.out.println("Searching for userId: " + userId);
        Optional<UserRecord> userRecord = userRepository.findById(userId);

        if (userRecord.isPresent()) {
            System.out.println("User found: " + userRecord.get());
            return transformToUser(userRecord.get());
        } else {
            System.out.println("User with userId: " + userId + " not found.");
            return null;
        }
    }

    public User createNewUser(User user) {
        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(user.getUserId());
        userRecord.setEmail(user.getEmail());
        userRecord.setPassword(user.getPassword());
        userRecord.setUsername(user.getUsername());

        try {
            userRepository.save(userRecord);
            return user;
        } catch (Exception e) {
            System.out.println("Unable to save user: " + e.getMessage());
            return null;
        }
    }

    public Optional<User> updateUser(String userId, User updatedUserInfo) {
        Optional<UserRecord> optionalExistingUser = userRepository.findById(userId);

        if (optionalExistingUser.isPresent()) {
            UserRecord existingUser = optionalExistingUser.get();
            existingUser.setEmail(updatedUserInfo.getEmail());
            existingUser.setPassword(updatedUserInfo.getPassword());
            existingUser.setUsername(updatedUserInfo.getUsername());
            userRepository.save(existingUser);
            return Optional.of(transformToUser(existingUser));
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

    private User transformToUser(UserRecord userRecord) {
        return new User(userRecord.getUserId(), userRecord.getUsername(), userRecord.getPassword(), userRecord.getEmail());
    }
}


