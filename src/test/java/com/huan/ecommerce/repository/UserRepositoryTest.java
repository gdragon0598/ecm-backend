package com.huan.ecommerce.repository;

import com.huan.ecommerce.entity.Product;
import com.huan.ecommerce.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    void testFindAll() {
        List<User> userList = userRepository.findAll();
        assertEquals(userList.size(),4);
    }

    @Test
    void testFindById() {
        Optional<User> testUser =  userRepository.findById(Integer.valueOf(101));
        User testUser2 = testUser.orElse(null);
        assertEquals(101, testUser2.getId());
    }
}
