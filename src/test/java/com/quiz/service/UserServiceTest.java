package com.quiz.service;

import com.quiz.entity.User;
import com.quiz.web.utils.Pageable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    UserService userService;
    private final static int pageSize = 5;

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

    @Test
    public void getAllUsersWithPagination() throws Exception {
        Pageable pageable = new Pageable.Builder()
                .page(1)
                .size(pageSize)
                .sort("create_date")
                .ASC()
                .build();
        List<User> users = userService.getAllUsers(1, pageable);
        int expectedSize = pageSize;
        int actual = users.size();
        assertTrue(actual <= expectedSize);
    }
}