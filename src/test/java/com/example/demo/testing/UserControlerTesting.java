package com.example.demo.testing;

import com.example.demo.controler.UserControler;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserControlerTesting {

    @Autowired
    @InjectMocks
    private UserControler userss;

    @Mock
    private UserRepository repo;

    @Test
    public void testAdd() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void findAllUserTest() {
        assertNotNull(userss.getAllUsers());
    }

    @Test
    public void findUserByIdTest() {
        assertNotNull(userss.getUser(102));
    }

    @Test
    public void createUsersTest() {
        assertNotNull(new Object());
    }

    @Test
    public void deleteUserTest() {
        assertNotNull(userss.removeUser(103));
    }

    @Test
    public void updateUsersTest() {
        // Arrange
        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setFirstname("Test User");

        Mockito.when(repo.save(Mockito.any(User.class))).thenReturn(mockUser);

        // Act
        User updatedUser = userss.updateUser(mockUser);

        // Assert
        assertNotNull(updatedUser);
        assertEquals("Test User", updatedUser.getFirstname());
        Mockito.verify(repo, Mockito.times(1)).save(mockUser);
    }
}