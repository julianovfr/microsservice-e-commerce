package com.ms.user.controllers;

import ch.qos.logback.core.model.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.user.dtos.AuthDto;
import com.ms.user.dtos.TokenDto;
import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;
import com.ms.user.services.TokenService;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity saveUser(@RequestBody @Valid AuthDto authDto) throws JsonProcessingException {

        var emailPassword = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());
        var auth = authenticationManager.authenticate(emailPassword);

        /*var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);

        System.out.println("Estou perdendo o role: "+userRecordDto.role());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));*/

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRecordDto userRecordDto) throws JsonProcessingException {
        if(this.userRepository.findByEmail(userRecordDto.email())!= null){
            return ResponseEntity.badRequest().build();
        }

        String encriptedPassword = new BCryptPasswordEncoder().encode(userRecordDto.password());

        var userModel = new UserModel();
        userModel.setEmail(userRecordDto.email());
        userModel.setName(userRecordDto.name());
        userModel.setRole(userRecordDto.role());
        userModel.setPassword(encriptedPassword);

        //BeanUtils.copyProperties(userRecordDto, userModel);

        userRepository.save(userModel);

        return ResponseEntity.ok().build();
    }

}
