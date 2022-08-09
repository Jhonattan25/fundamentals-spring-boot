package com.fundamentals.springboot.fundamentals.caseuse;

import com.fundamentals.springboot.fundamentals.entity.User;

import java.util.List;

public interface GetUserPageable {
    List<User> getAll(int page, int size);
}
