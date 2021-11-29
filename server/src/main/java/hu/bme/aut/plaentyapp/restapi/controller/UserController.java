package hu.bme.aut.plaentyapp.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import hu.bme.aut.plaentyapp.security.User;
import hu.bme.aut.plaentyapp.repository.UserRepository;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<?> create(@Valid @RequestBody User user) {
        if(userRepository.existsByName(user.getName())) return ResponseEntity.badRequest().build();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}