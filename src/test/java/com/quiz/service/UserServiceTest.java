package com.quiz.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService();
    }

    @Test
    public void deleteUserWithWrongIdAndGetZeroChanges() {
        assertFalse(userService.deleteUserById(2113));
    }

    @Test
    public void getTrueIfUserExistByEmail() {
        assertTrue(userService.isUserExist("admin@mail.com"));
    }

    @Test
    public void getTrueIfUserBlocked() {
        userService.blockUser(2, 1);
        int block = userService.getUserById(2).getBlock();
        assertEquals(1, block);
    }
}