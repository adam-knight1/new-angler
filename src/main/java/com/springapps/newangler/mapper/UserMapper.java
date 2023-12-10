package com.springapps.newangler.mapper;


import com.springapps.newangler.dto.UserDto;
import com.springapps.newangler.repository.model.UserRecord;

public class UserMapper {

        public static UserDto toDto(UserRecord userRecord) {
            UserDto dto = new UserDto();
            dto.setUserId(userRecord.getUserId());
            dto.setEmail(userRecord.getEmail());
            dto.setUsername(userRecord.getUsername());
            dto.setPassword(userRecord.getPassword()); // will change later on
            return dto;
        }

        public static UserRecord toEntity(UserDto userDto) {
            UserRecord userRecord = new UserRecord();
            userRecord.setUserId(userDto.getUserId());
            userRecord.setEmail(userDto.getEmail());
            userRecord.setUsername(userDto.getUsername());
            userRecord.setPassword(userDto.getPassword());
            return userRecord;
        }
    }

