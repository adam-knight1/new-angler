package com.springapps.newangler.repository;

import com.springapps.newangler.repository.model.UserRecord;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Optional;

public class UserRepository {

        private final DynamoDbTable<UserRecord> userTable;

        public UserRepository(DynamoDbEnhancedClient enhancedClient) {
            this.userTable = enhancedClient.table("User", TableSchema.fromBean(UserRecord.class));
        }

        public Optional<UserRecord> findById(String userId) {
            return Optional.ofNullable(userTable.getItem(Key.builder().partitionValue(userId).build()));
        }

        public void save(UserRecord user) {
            userTable.putItem(user);
        }

        public void delete(UserRecord user) {
            userTable.deleteItem(Key.builder().partitionValue(user.getUserId()).build());
        }


    }
