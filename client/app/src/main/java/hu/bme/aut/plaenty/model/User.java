package hu.bme.aut.plaenty.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class User {
    private String name;
    private String password;
}
