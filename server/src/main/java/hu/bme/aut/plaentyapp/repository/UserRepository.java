package hu.bme.aut.plaentyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hu.bme.aut.plaentyapp.security.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    User findByName(String name);

    boolean existsByName(String name);
}
