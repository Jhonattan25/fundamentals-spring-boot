package com.fundamentals.springboot.fundamentals.caseuse;

import com.fundamentals.springboot.fundamentals.entity.User;
import com.fundamentals.springboot.fundamentals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GetUserPageableImplement implements GetUserPageable{
    private UserService userService;

    @Autowired
    public GetUserPageableImplement(UserService userService){
        this.userService = userService;
    }

    @Override
    public List<User> getAll(int page, int size) {
        return userService.getAll(page, size);
    }
}
