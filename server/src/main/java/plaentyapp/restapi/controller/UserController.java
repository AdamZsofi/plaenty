package plaentyapp.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plaentyapp.model.user.User;
import plaentyapp.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user.get());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> create(@Valid @RequestBody User user) {
        if(userRepository.existsByName(user.getName())) return ResponseEntity.badRequest().build();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        else {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }


}