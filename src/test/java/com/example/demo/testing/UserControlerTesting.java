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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        // Arrange
        User user = new User();
        user.setId(1);
        user.setFirstname("test User");

        // Act
        userss.createUser(user);

        // Assert
        Mockito.verify(repo, Mockito.times(1)).save(user);
    }

    @Test
    public void deleteUserTest() {
        // Arrange: mock the repository
        User userToDelete = new User();
        userToDelete.setId(103);
        Mockito.when(repo.findById(103)).thenReturn(Optional.of(userToDelete));

        // Act: delete user
        boolean result = userss.removeUser(103);
        // Assert: check that the user was deleted and the result is true
        assertTrue(result);
        Mockito.verify(repo, Mockito.times(1)).delete(userToDelete);  // Verify that the delete method was called once
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
    @Test
    public void updateUserPartiallyTest() {
        // Arrange
        int userId = 1;
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setFirstname("Old Name");
        existingUser.setEmail("oldemail@example.com");

        Mockito.when(repo.findById(userId)).thenReturn(Optional.of(existingUser));
        Mockito.when(repo.save(Mockito.any(User.class))).thenReturn(existingUser);

        // Act
        User updatedUser = userss.updateUserPartially(userId);

        // Assert

        assertNotNull(updatedUser);
        assertEquals("ponam", updatedUser.getFirstname());
        assertEquals("harbahajn@gamil.com", updatedUser.getEmail());
        Mockito.verify(repo, Mockito.times(1)).findById(userId);
        Mockito.verify(repo, Mockito.times(1)).save(existingUser);
    }
}

