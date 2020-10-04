package com.mitraistest.registration.service;

import com.mitraistest.registration.domain.UserRegistrationDto;

public interface UserService {
    public void saveUser(UserRegistrationDto userRegistrationDto) throws Exception;
}
