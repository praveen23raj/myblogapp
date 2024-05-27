package com.blogapp1.controller;

import com.blogapp1.entity.User;
import com.blogapp1.payload.LoginDto;
import com.blogapp1.payload.SignUp;
import com.blogapp1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/sign-up")
   public ResponseEntity<String> createUser(@RequestBody SignUp signUp){
       if(userRepository.existsByEmail(signUp.getEmail())){
           return new ResponseEntity<>("Email already Registered", HttpStatus.INTERNAL_SERVER_ERROR);
       }
       if(userRepository.existsByUsername(signUp.getUsername())){
           return new ResponseEntity<>("Username already Registered", HttpStatus.INTERNAL_SERVER_ERROR);
       }
       User user=new User();
        user.setName(signUp.getName());
       user.setEmail(signUp.getEmail());
       user.setUsername(signUp.getUsername());
       user.setPassword(passwordEncoder.encode(signUp.getPassword()));

       userRepository.save(user);

       return new ResponseEntity<>("User Registered",HttpStatus.CREATED);
   }
  @PostMapping("/signin")
   public ResponseEntity<String> signin(@RequestBody LoginDto loginDto){
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
       loginDto.getUsername(),loginDto.getPassword());
      Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authenticate);
      return new ResponseEntity<>("Sign-in Sucessfull",HttpStatus.OK);
   }

}
