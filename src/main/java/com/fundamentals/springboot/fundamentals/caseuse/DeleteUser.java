package com.fundamentals.springboot.fundamentals.caseuse;

import com.fundamentals.springboot.fundamentals.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {
    private UserService userService;

    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    public void remove(Long id) {
        userService.delete(id);
    }
}
