package com.ms.user.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel) throws JsonProcessingException {
        //salva no banco de dados

        System.out.println("Model role: "+userModel.getRole());

        if(!userRepository.existsByEmail(userModel.getEmail()))userModel = userRepository.save(userModel);

        //userProducer.publishMessageEmail(userModel);

        return userModel;
    }
}
