package com.springapps.newangler.repository;

import com.springapps.newangler.repository.model.UserRecord;
import org.springframework.data.repository.CrudRepository;
//import org.socialsignin.spring.data.dynamodb.repository.EnableScan;


import java.util.List;

@EnableScan
public interface UserRepository extends CrudRepository<UserRecord, String> {

    //possible idea for basic authentication, would require hashing and a secondary index irl
    //List<UserRecord> findByUsernameAndPassword(String username, String password);

    String findUserByUserId(String userId);
    List<String> findUsersByUserId(String userId);


}

