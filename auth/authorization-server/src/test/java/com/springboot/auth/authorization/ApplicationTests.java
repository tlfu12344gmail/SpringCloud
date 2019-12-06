package com.springboot.auth.authorization;

import com.springboot.auth.authorization.exception.AuthErrorType;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class ApplicationTests {

    @Test
    public void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String result = passwordEncoder.encode("test_secret");
        System.out.println(result);

        System.out.println(passwordEncoder.matches("test_secret","$2a$10$2szDKjvKHJCWE6YQNznogOeQF3USZHmCYj1fG7YbfK.vnTgNKLzri"));
        System.out.println(passwordEncoder.matches("test_secret",result));
    }
}
