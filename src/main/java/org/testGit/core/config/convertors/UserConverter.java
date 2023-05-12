package org.testGit.core.config.convertors;

import org.aeonbits.owner.Converter;
import org.testGit.core.config.data.User;

import java.lang.reflect.Method;

public class UserConverter implements Converter<User> {

    @Override
    public User convert(Method method, String input) {
        String[] split = input.split(":");
        String email = split[0];
        String password = split[1];
        return new User(email, password);
    }
}
