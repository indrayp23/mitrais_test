package com.mitraistest.registration.service;

import com.mitraistest.registration.domain.User;
import com.mitraistest.registration.domain.UserRegistrationDto;
import com.mitraistest.registration.util.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(UserRegistrationDto userRegistrationDto) throws Exception {

        User userEmail = userRepository.findByEmail(userRegistrationDto.getEmail());
        User userPhone = userRepository.findByMobileNumber(userRegistrationDto.getMobileNumber());

        if (userEmail != null){
            throw new ApplicationException("There is already an account registered with that email");
        }

        if (userPhone != null){
            throw new ApplicationException("There is already an account registered with that phone");
        }

        User user = new User();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setDateOfBirth(userRegistrationDto.getDateOfBirth());
        user.setGender(userRegistrationDto.getGender());
        user.setMobileNumber(userRegistrationDto.getMobileNumber());
        userRepository.save(user);
    }

}
