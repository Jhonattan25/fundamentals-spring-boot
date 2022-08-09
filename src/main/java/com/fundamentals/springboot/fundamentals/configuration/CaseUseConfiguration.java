package com.fundamentals.springboot.fundamentals.configuration;

import com.fundamentals.springboot.fundamentals.caseuse.GetUser;
import com.fundamentals.springboot.fundamentals.caseuse.GetUserImplement;
import com.fundamentals.springboot.fundamentals.caseuse.GetUserPageable;
import com.fundamentals.springboot.fundamentals.caseuse.GetUserPageableImplement;
import com.fundamentals.springboot.fundamentals.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    public GetUser getUser(UserService userService) {
        return new GetUserImplement(userService);
    }

    @Bean
    public GetUserPageable getUserPageable(UserService userService) {
        return new GetUserPageableImplement(userService);
    }
}
