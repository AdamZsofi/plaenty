package plaentyapp.model.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public @Data class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20, message = "name length must be maximum 20 characters long")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "name may contain only letters and numbers")
    private String name;

    private String password;

}
